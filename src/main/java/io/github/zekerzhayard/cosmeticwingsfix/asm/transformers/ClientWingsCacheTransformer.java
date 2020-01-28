package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import io.github.zekerzhayard.cosmeticwingsfix.asm.utils.LoggerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ClientWingsCacheTransformer extends AbstractTransformer {
    public ClientWingsCacheTransformer() {
        super("com.hyt.cosmeticWings.iiiIIIIIIi"); // com.hyt.cosmeticWings.client.wings.ClientWingsCache
    }

    @Override
    public boolean isTargetMethod(MethodNode mn) {
        return mn.name.equals("iIIIIiIiii") && mn.desc.equals("(Ljava/util/UUID;)Lcom/hyt/cosmeticWings/IIIiIiiIII;"); // ClientWingsCache.getPlayerWingsData:(Ljava/util/UUID;)Lcom/hyt/cosmeticWings/common/wings/WingsData;
    }

    @Override
    public void transform(ClassNode cn, MethodNode mn) {
        LoggerUtils.methodInfo(this, cn, mn);
        for (AbstractInsnNode ain : mn.instructions.toArray()) {
            if (ain.getOpcode() == Opcodes.ACONST_NULL) {
                LoggerUtils.nodeInfo(this, "%s", ain.getOpcode());
                LabelNode ln = new LabelNode();
                mn.instructions.insertBefore(ain, new VarInsnNode(Opcodes.ALOAD, 1));
                mn.instructions.insertBefore(ain, new MethodInsnNode(Opcodes.INVOKESTATIC, "io/github/zekerzhayard/cosmeticwingsfix/utils/UUIDUtils", "compareSelfUUID", "(Ljava/util/UUID;)Z", false));
                mn.instructions.insertBefore(ain, new JumpInsnNode(Opcodes.IFEQ, ln));
                mn.instructions.insertBefore(ain, new TypeInsnNode(Opcodes.NEW, "com/hyt/cosmeticWings/IIIiIiiIII")); // com/hyt/cosmeticWings/common/wings/WingsData
                mn.instructions.insertBefore(ain, new InsnNode(Opcodes.DUP));
                mn.instructions.insertBefore(ain, new InsnNode(Opcodes.DUP));
                mn.instructions.insertBefore(ain, new MethodInsnNode(Opcodes.INVOKESPECIAL, "com/hyt/cosmeticWings/IIIiIiiIII", "<init>", "()V", false)); // com/hyt/cosmeticWings/common/wings/WingsData.<init>:()V
                mn.instructions.insertBefore(ain, new MethodInsnNode(Opcodes.INVOKESTATIC, "io/github/zekerzhayard/cosmeticwingsfix/config/Config", "loadConfig", "()Lnet/minecraft/nbt/NBTTagCompound;", false));
                mn.instructions.insertBefore(ain, new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "com/hyt/cosmeticWings/IIIiIiiIII", "iIIIIiIiii", "(Lnet/minecraft/nbt/NBTTagCompound;)V", false)); // com/hyt/cosmeticWings/common/wings/WingsData.loadNBTData:(Lnet/minecraft/nbt/NBTTagCompound;)V
                mn.instructions.insertBefore(ain, new InsnNode(Opcodes.DUP));
                mn.instructions.insertBefore(ain, new VarInsnNode(Opcodes.ALOAD, 0));
                mn.instructions.insertBefore(ain, new InsnNode(Opcodes.SWAP));
                mn.instructions.insertBefore(ain, new VarInsnNode(Opcodes.ALOAD, 1));
                mn.instructions.insertBefore(ain, new InsnNode(Opcodes.SWAP));
                mn.instructions.insertBefore(ain, new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "com/hyt/cosmeticWings/iiiIIIIIIi", "iIIIIiIiii", "(Ljava/util/UUID;Lcom/hyt/cosmeticWings/IIIiIiiIII;)V", false)); // com/hyt/cosmeticWings/client/wings/ClientWingsCache.setWingsData:(Ljava/util/UUID;Lcom/hyt/cosmeticWings/common/wings/WingsData;)V
                mn.instructions.insertBefore(ain, new InsnNode(Opcodes.ARETURN));
                mn.instructions.insertBefore(ain, ln);
                mn.instructions.insertBefore(ain, new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
                mn.maxStack = 4;
            }
        }
    }

    @Override
    public int getClassWriterFlags() {
        return 0;
    }
}
