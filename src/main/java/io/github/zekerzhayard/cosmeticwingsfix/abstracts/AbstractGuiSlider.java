package io.github.zekerzhayard.cosmeticwingsfix.abstracts;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.config.GuiSlider;

public abstract class AbstractGuiSlider extends GuiSlider {
    public AbstractGuiSlider(int id, int xPos, int yPos, String displayStr, double minVal, double maxVal, double currentVal, ISlider par) {
        super(id, xPos, yPos, displayStr, minVal, maxVal, currentVal, par);
    }

    public AbstractGuiSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr) {
        super(id, xPos, yPos, width, height, prefix, suf, minVal, maxVal, currentVal, showDec, drawStr);
    }

    public AbstractGuiSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr, ISlider par) {
        super(id, xPos, yPos, width, height, prefix, suf, minVal, maxVal, currentVal, showDec, drawStr, par);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.iIIIIiIiii(mc, mouseX, mouseY, 0.0F);
    }

    public void iIIIIiIiii(Minecraft mc, int mouseX, int mouseY, float partial) {
        super.drawButton(mc, mouseX, mouseY);
    }

    public void super$func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
    }

    public void super$func_146112_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY);
    }

    @Override
    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
        this.iIIIIiIiii(par1Minecraft, par2, par3);
    }

    protected void iIIIIiIiii(Minecraft par1Minecraft, int par2, int par3) {
        super.mouseDragged(par1Minecraft, par2, par3);
    }

    protected void super$func_146119_b(Minecraft par1Minecraft, int par2, int par3) {
        super.mouseDragged(par1Minecraft, par2, par3);
    }
}
