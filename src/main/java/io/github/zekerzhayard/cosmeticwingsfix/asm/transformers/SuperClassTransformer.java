package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import io.github.zekerzhayard.cosmeticwingsfix.asm.utils.LoggerUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class SuperClassTransformer extends AbstractTransformer {
    protected ImmutableMap<String, String> targets;

    public SuperClassTransformer() {
        this.targets = new ImmutableMap.Builder<String, String>()
            .put("net/minecraft/client/model/ModelBase", "io/github/zekerzhayard/cosmeticwingsfix/abstracts/AbstractModelBase")
            .put("net/minecraft/client/particle/EntityFX", "io/github/zekerzhayard/cosmeticwingsfix/abstracts/AbstractEntityFx")
            .put("net/minecraftforge/fml/client/config/GuiButtonExt", "io/github/zekerzhayard/cosmeticwingsfix/abstracts/AbstractGuiButtonExt")
            .put("net/minecraftforge/fml/client/config/GuiSlider", "io/github/zekerzhayard/cosmeticwingsfix/abstracts/AbstractGuiSlider")
            .build();
    }

    public SuperClassTransformer(ImmutableMap<String, String> targets) {
        this.targets = targets;
    }

    @Override
    public boolean isTarget(String className) {
        return className.startsWith("com.hyt.cosmeticWings.");
    }

    @Override
    public boolean isTarget(ClassNode cn) {
        return this.targets.containsKey(cn.superName);
    }

    @Override
    public void transform(ClassNode cn) {
        super.transform(cn);
        if (this.targets.containsKey(cn.superName)) {
            LoggerUtils.classInfo(this, cn);
            cn.superName = this.targets.get(cn.superName);
            cn.methods.stream()
                .flatMap(mn -> Stream.of(mn.instructions.toArray()))
                .filter(ain -> ain.getOpcode() == Opcodes.INVOKESPECIAL)
                .map(ain -> (MethodInsnNode) ain)
                .filter(min -> this.targets.containsKey(min.owner))
                .peek(min -> LoggerUtils.nodeInfo(this, "%s %s.%s:%s", min.getOpcode(), min.owner, min.name, min.desc))
                .peek(min -> min.owner = this.targets.get(min.owner))
                .filter(min -> !min.name.equals("<init>"))
                .forEach(min -> min.name = "super$" + min.name);
        }
    }

    @Override
    public int getClassWriterFlags() {
        return 0;
    }
}
