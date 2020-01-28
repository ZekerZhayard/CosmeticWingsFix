package io.github.zekerzhayard.cosmeticwingsfix.asm.transformers;

import com.google.common.collect.ImmutableMap;

public class GuiScreen_1Transformer extends SuperClassTransformer {
    public GuiScreen_1Transformer() {
        super(new ImmutableMap.Builder<String, String>().put("net/minecraft/client/gui/GuiScreen", "io/github/zekerzhayard/cosmeticwingsfix/abstracts/AbstractGuiScreen_1").build());
    }

    @Override
    public boolean isTarget(String className) {
        return className.startsWith("com.hyt.cosmeticWings.") && !className.equals("com.hyt.cosmeticWings.iiIiIIIiii");
    }
}
