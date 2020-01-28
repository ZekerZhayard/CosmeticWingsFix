package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import io.github.zekerzhayard.cosmeticwingsfix.asm.utils.LoggerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ClientProxyTransformer extends AbstractTransformer {
    public ClientProxyTransformer() {
        super("com.hyt.cosmeticWings.IiIIiIIiii"); // com.hyt.cosmeticWings.proxies.ClientProxy
    }

    @Override
    public boolean isTargetMethod(MethodNode mn) {
        return mn.name.equals("iIIIiiIiii") && mn.desc.equals("()V"); // ClientProxy.init:()V
    }

    @Override
    public void transform(ClassNode cn, MethodNode mn) {
        LoggerUtils.methodInfo(this, cn, mn);
        InsnList il = new InsnList();
        il.add(new TypeInsnNode(Opcodes.NEW, "com/hyt/cosmeticWings/IIiIiIiIii")); // com.hyt.cosmeticWings.client.handler.KeyboardHandler
        il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "com/hyt/cosmeticWings/IIiIiIiIii", "<init>", "()V", false)); // KeyboardHandler.<init>:()V
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
        il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "com/hyt/cosmeticWings/IiIIiIIiii", "iIIiIiIIIi", "()V", false)); // ClientProxy.registerKeyBindings:()V
        mn.instructions.insert(il);
    }
}
