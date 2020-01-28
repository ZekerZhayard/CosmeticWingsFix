package io.github.zekerzhayard.cosmeticwingsfix.abstracts;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public abstract class AbstractGuiButtonExt extends GuiButtonExt {
    public AbstractGuiButtonExt(int id, int xPos, int yPos, String displayString) {
        super(id, xPos, yPos, displayString);
    }

    public AbstractGuiButtonExt(int id, int xPos, int yPos, int width, int height, String displayString) {
        super(id, xPos, yPos, width, height, displayString);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.iIIIIiIiii(mc, mouseX, mouseY, 0.0F);
    }

    public void iIIIIiIiii(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY);
    }

    public void super$func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
    }

    public void super$func_146112_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        super.drawButton(mc, mouseX, mouseY);
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        return this.iIIIIiIiii(mc, mouseX, mouseY);
    }

    public boolean iIIIIiIiii(Minecraft mc, int mouseX, int mouseY) {
        return super.mousePressed(mc, mouseX, mouseY);
    }

    public boolean super$func_146116_c(Minecraft mc, int mouseX, int mouseY) {
        return super.mousePressed(mc, mouseX, mouseY);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.iIIIIiIiii(mouseX, mouseY);
    }

    public void iIIIIiIiii(int mouseX, int mouseY) {
        super.mouseReleased(mouseX, mouseY);
    }

    public void super$func_146118_a(int mouseX, int mouseY) {
        super.mouseReleased(mouseX, mouseY);
    }
}
