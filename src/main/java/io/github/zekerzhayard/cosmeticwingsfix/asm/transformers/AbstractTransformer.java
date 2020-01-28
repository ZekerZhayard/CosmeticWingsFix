package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import java.util.List;

import com.google.common.collect.Lists;
import io.github.zekerzhayard.cosmeticwingsfix.asm.utils.LoggerUtils;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public abstract class AbstractTransformer implements ITransformer {
    private List<String> targets;

    public AbstractTransformer(String... targets) {
        this.targets = Lists.newArrayList(targets);
    }

    @Override
    public boolean isTarget(String className) {
        return this.targets.contains(className);
    }

    public boolean isTargetMethod(MethodNode mn) {
        return false;
    }

    @Override
    public void transform(ClassNode cn) {
        LoggerUtils.classInfo(this, cn);
        cn.methods.stream()
            .filter(this::isTargetMethod)
            .forEach(mn -> this.transform(cn, mn));
    }

    public void transform(ClassNode cn, MethodNode mn) {

    }
}
