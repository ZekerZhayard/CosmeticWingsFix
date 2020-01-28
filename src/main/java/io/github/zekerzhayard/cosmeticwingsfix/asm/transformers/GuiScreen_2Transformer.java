package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import com.google.common.collect.ImmutableMap;
import io.github.zekerzhayard.cosmeticwingsfix.asm.utils.LoggerUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class GuiScreen_2Transformer extends SuperClassTransformer {
    public GuiScreen_2Transformer() {
        super(new ImmutableMap.Builder<String, String>().put("net/minecraft/client/gui/GuiScreen", "io/github/zekerzhayard/cosmeticwingsfix/abstracts/AbstractGuiScreen_2").build());
    }

    @Override
    public boolean isTarget(String className) {
        return className.equals("com.hyt.cosmeticWings.iiIiIIIiii"); // com.hyt.cosmeticWings.client.gui.GuiWings
    }

    @Override
    public boolean isTargetMethod(MethodNode mn) {
        return mn.name.equals("iIIIIiIiii") && mn.desc.equals("()V"); // GuiWings.sendWingDataToServer:()V
    }

    @Override
    public void transform(ClassNode cn, MethodNode mn) {
        LoggerUtils.methodInfo(this, cn, mn);
        for (AbstractInsnNode ain : mn.instructions.toArray()) {
            if (ain.getOpcode() == Opcodes.INVOKESPECIAL) {
                MethodInsnNode min = (MethodInsnNode) ain;
                if (min.owner.equals("com/hyt/cosmeticWings/iiIIIIIIiI") && min.name.equals("<init>") && min.desc.equals("(Lcom/hyt/cosmeticWings/IIIiIiiIII;)V")) { // com/hyt/cosmeticWings/common/network/message/MessageClientUpdateWingsData.<init>:(Lcom/hyt/cosmeticWings/common/wings/WingsData;)V
                    LoggerUtils.nodeInfo(this, "%s %s.%s:%s", min.getOpcode(), min.owner, min.name, min.desc);
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.DUP));
                    mn.instructions.insertBefore(min, new TypeInsnNode(Opcodes.NEW, "net/minecraft/nbt/NBTTagCompound"));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.DUP));
                    mn.instructions.insertBefore(min, new MethodInsnNode(Opcodes.INVOKESPECIAL, "net/minecraft/nbt/NBTTagCompound", "<init>", "()V", false));
                    mn.instructions.insertBefore(min, new InsnNode(Opcodes.DUP_X1));
                    mn.instructions.insertBefore(min, new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "com/hyt/cosmeticWings/IIIiIiiIII", "iIIIiiIiii", "(Lnet/minecraft/nbt/NBTTagCompound;)V", false)); // com/hyt/cosmeticWings/common/wings/WingsData.saveNBTData:(Lnet/minecraft/nbt/NBTTagCompound;)V
                    mn.instructions.insertBefore(min, new MethodInsnNode(Opcodes.INVOKESTATIC, "io/github/zekerzhayard/cosmeticwingsfix/config/Config", "saveConfig", "(Lnet/minecraft/nbt/NBTTagCompound;)V", false));
                }
            }
        }
    }

    @Override
    public int getClassWriterFlags() {
        return ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES;
    }
}
