package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public interface ITransformer {
    void transform(ClassNode cn);

    default boolean isTarget(String className) {
        return true;
    }

    default boolean isTarget(ClassNode cn) {
        return true;
    }

    default int getClassWriterFlags() {
        return ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES;
    }
}
