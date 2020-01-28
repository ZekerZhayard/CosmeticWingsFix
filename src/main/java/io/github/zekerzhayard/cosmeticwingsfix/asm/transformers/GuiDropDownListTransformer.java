package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import io.github.zekerzhayard.cosmeticwingsfix.asm.utils.LoggerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 *  Fix the wrong width of the drop-down list.
 */
public class GuiDropDownListTransformer extends AbstractTransformer {
    public GuiDropDownListTransformer() {
        super("com.hyt.cosmeticWings.IiiIIiIIII"); // com.hyt.cosmeticWings.client.gui.controls.GuiDropDownList
    }

    @Override
    public boolean isTargetMethod(MethodNode mn) {
        return mn.name.equals("<init>") && mn.desc.equals("(IIIILjava/lang/String;Lcom/hyt/cosmeticWings/IIiIIIIiiI;)V"); // GuiDropDownList.<init>:(IIIILjava/lang/String;Lcom/hyt/cosmeticWings/client/gui/controls/GuiDropDownList$IDropDownListCallback;)V
    }

    @Override
    public void transform(ClassNode cn, MethodNode mn) {
        LoggerUtils.methodInfo(this, cn, mn);
        for (AbstractInsnNode ain : mn.instructions.toArray()) {
            if (ain.getOpcode() == Opcodes.BIPUSH && ((IntInsnNode) ain).operand == 14) {
                LoggerUtils.nodeInfo(this, "%s %s", ain.getOpcode(), ((IntInsnNode) ain).operand);
                mn.instructions.set(ain, new VarInsnNode(Opcodes.ILOAD, 4));
                break;
            }
        }
    }
}
