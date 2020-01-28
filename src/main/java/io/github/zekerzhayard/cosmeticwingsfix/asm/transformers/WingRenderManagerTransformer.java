package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import io.github.zekerzhayard.cosmeticwingsfix.asm.utils.LoggerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class WingRenderManagerTransformer extends AbstractTransformer {
    public WingRenderManagerTransformer() {
        super("com.hyt.cosmeticWings.iiiiIIIIII"); // com.hyt.cosmeticWings.client.render.WingRenderManager
    }

    @Override
    public boolean isTargetMethod(MethodNode mn) {
        return mn.name.equals("iIIIIiIiii") && mn.desc.equals("(Lnet/minecraftforge/client/event/RenderPlayerEvent$Pre;Lnet/minecraft/entity/player/EntityPlayer;)V"); // WingRenderManager.onRender:(Lnet/minecraftforge/client/event/RenderPlayerEvent$Pre;Lnet/minecraft/entity/player/EntityPlayer;)V
    }

    @Override
    public void transform(ClassNode cn, MethodNode mn) {
        LoggerUtils.methodInfo(this, cn, mn);
        boolean hasTransformed = false;
        for (AbstractInsnNode ain : mn.instructions.toArray()) {
            if (!hasTransformed && ain.getOpcode() == Opcodes.INVOKEVIRTUAL) {
                MethodInsnNode min = (MethodInsnNode) ain;
                if (min.owner.equals("java/util/HashMap") && min.name.equals("containsKey") && min.desc.equals("(Ljava/lang/Object;)Z")) {
                    LoggerUtils.nodeInfo(this, "%s %s.%s:%s", min.getOpcode(), min.owner, min.name, min.desc);
                    mn.instructions.set(min, new MethodInsnNode(Opcodes.INVOKESTATIC, "io/github/zekerzhayard/cosmeticwingsfix/utils/UUIDUtils", "compareSelfUUIDWithHashMap", "(Ljava/util/HashMap;Ljava/util/UUID;)Z", false));
                    hasTransformed = true;
                }
            } else if (ain.getOpcode() == Opcodes.INVOKESTATIC) {
                MethodInsnNode min = (MethodInsnNode) ain;
                if (min.owner.equals("org/lwjgl/opengl/GL11") && min.name.equals("glRotatef") && min.desc.equals("(FFFF)V")) {
                    LoggerUtils.nodeInfo(this, "%s %s.%s:%s", min.getOpcode(), min.owner, min.name, min.desc);
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.FCONST_0));
                    mn.instructions.insertBefore(min, new VarInsnNode(Opcodes.ALOAD, 3));
                    mn.instructions.insertBefore(min, new FieldInsnNode(Opcodes.GETFIELD, "com/hyt/cosmeticWings/IIIiIiiIII", "I", "F")); // com/hyt/cosmeticWings/common/wings/WingsData.heightOffset:F
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.FNEG));
                    mn.instructions.insertBefore(min, new LdcInsnNode(0.5F));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.FMUL));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.FCONST_0));
                    mn.instructions.insertBefore(min, new MethodInsnNode(Opcodes.INVOKESTATIC, "org/lwjgl/opengl/GL11", "glTranslatef", "(FFF)V", false));
                }
            }
        }
    }
}
