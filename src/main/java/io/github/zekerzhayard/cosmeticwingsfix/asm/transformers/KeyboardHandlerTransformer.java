package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import io.github.zekerzhayard.cosmeticwingsfix.asm.utils.LoggerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class KeyboardHandlerTransformer extends AbstractTransformer {
    public KeyboardHandlerTransformer() {
        super("com.hyt.cosmeticWings.IIiIiIiIii"); // com.hyt.cosmeticWings.client.handler.KeyboardHandler
    }

    @Override
    public boolean isTargetMethod(MethodNode mn) {
        return mn.name.equals("iIIIIiIiii") && mn.desc.equals("(Lnet/minecraftforge/fml/common/gameevent/InputEvent$KeyInputEvent;)V"); // KeyboardHandler.onKeyInputEvent:(Lnet/minecraftforge/fml/common/gameevent/InputEvent$KeyInputEvent;)V
    }

    @Override
    public void transform(ClassNode cn, MethodNode mn) {
        LoggerUtils.methodInfo(this, cn, mn);
        for (AbstractInsnNode ain : mn.instructions.toArray()) {
            if (ain.getOpcode() == Opcodes.INVOKESTATIC) {
                MethodInsnNode min = (MethodInsnNode) ain;
                if (min.owner.equals("net/minecraft/client/Minecraft") && min.name.equals("func_71410_x") && min.desc.equals("()Lnet/minecraft/client/Minecraft;")) {
                    LoggerUtils.nodeInfo(this, "%s %s.%s:%s", min.getOpcode(), min.owner, min.name, min.desc);
                    mn.instructions.insertBefore(min, new MethodInsnNode(Opcodes.INVOKESTATIC, "net/minecraft/client/Minecraft", "func_71410_x", "()Lnet/minecraft/client/Minecraft;", false));
                    mn.instructions.insertBefore(min, new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/client/Minecraft", "field_71439_g", "Lnet/minecraft/client/entity/EntityPlayerSP;"));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.DUP));
                    mn.instructions.insertBefore(min, new FieldInsnNode(Opcodes.GETSTATIC, "com/hyt/cosmeticwings/CosmeticWings", "I", "Lcom/hyt/cosmeticwings/CosmeticWings;")); // CosmeticWings.instance:Lcom/hyt/cosmeticwings/CosmeticWings;
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.SWAP));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.ICONST_0));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.SWAP));
                    mn.instructions.insertBefore(min, new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/entity/Entity", "field_70170_p", "Lnet/minecraft/world/World;"));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.ICONST_0));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.ICONST_0));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.ICONST_0));
                    mn.instructions.insertBefore(min, new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/entity/player/EntityPlayer", "openGui", "(Ljava/lang/Object;ILnet/minecraft/world/World;III)V", false));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.RETURN));
                    break;
                }
            }
        }
    }
}
