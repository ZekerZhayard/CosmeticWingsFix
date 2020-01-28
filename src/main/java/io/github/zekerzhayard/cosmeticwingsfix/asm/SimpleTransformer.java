package io.github.zekerzhayard.cosmeticwingsfix.asm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.github.zekerzhayard.cosmeticwingsfix.asm.transformers.ITransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class SimpleTransformer implements IClassTransformer {
    public static SimpleTransformer INSTANCE;
    private final List<ITransformer> transformers = new ArrayList<>();

    public SimpleTransformer() {
        INSTANCE = this;
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null)
            return null;

        List<ITransformer> transformers = getTransformers(transformedName);

        if (!transformers.isEmpty()) {
            try {
                ClassNode cn = new ClassNode();
                ClassReader cr = new ClassReader(basicClass);
                cr.accept(cn, 0);

                transformers = transformers.stream().filter(transformer -> transformer.isTarget(cn)).collect(Collectors.toList());
                if (!transformers.isEmpty()) {
                    transformers.forEach(transformer -> transformer.transform(cn));

                    int flags = 0;
                    for (ITransformer transformer : transformers) {
                        flags |= transformer.getClassWriterFlags();
                    }
                    ClassWriter cw = new ClassWriter(flags);
                    cn.accept(cw);
                    return cw.toByteArray();
                }
            } catch (Exception e) {
                FMLLoadingPlugin.LOGGER.error(String.format("An exception occurred while transforming class: %s (Transformers: %s)" ,transformedName, transformers), e);
            }
        }

        return basicClass;
    }

    public final void registerAll(ITransformer... transformers) {
        this.transformers.addAll(Arrays.asList(transformers));
    }

    /**
     * Finds transformers that target the specified class. If a transformer doesn't
     * have any targets, it is assumed that it will accept any class.
     *
     * @param name Target class name
     * @return Valid transformers
     */
    private List<ITransformer> getTransformers(String name) {
        return transformers.stream()
                .filter(transformer -> transformer.isTarget(name))
                .collect(Collectors.toList());
    }
}
