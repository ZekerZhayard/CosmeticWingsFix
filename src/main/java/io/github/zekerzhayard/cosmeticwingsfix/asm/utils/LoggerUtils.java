package io.github.zekerzhayard.cosmeticwingsfix.asm.utils;

import io.github.zekerzhayard.cosmeticwingsfix.asm.FMLLoadingPlugin;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class LoggerUtils {
    public static void classInfo(Object transformer, ClassNode cn) {
        FMLLoadingPlugin.LOGGER.info("[{}] Found the class: {}", transformer.getClass().getName(), cn.name);
    }

    public static void methodInfo(Object transformer, ClassNode cn, MethodNode mn) {
        FMLLoadingPlugin.LOGGER.info("[{}] Found the method: {}.{}:{}", transformer.getClass().getName(), cn.name, mn.name, mn.desc);
    }

    public static void nodeInfo(Object transformer, String format, Object... args) {
        FMLLoadingPlugin.LOGGER.info("[{}] Found the node: {}", transformer.getClass().getName(), String.format(format, args));
    }
}
