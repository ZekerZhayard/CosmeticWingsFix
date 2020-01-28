package io.github.zekerzhayard.cosmeticwingsfix.abstracts;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public abstract class AbstractModelBase extends ModelBase {
    @Override
    public void render(Entity entityIn, float f1, float f2, float f3, float f4, float f5, float scale) {
        this.iIIIIiIiii(entityIn, f1, f2, f3, f4, f5, scale);
    }

    public void iIIIIiIiii(Entity entityIn, float f1, float f2, float f3, float f4, float f5, float scale) {
        super.render(entityIn, f1, f2, f3, f4, f5, scale);
    }

    public void super$func_78088_a(Entity entityIn, float f1, float f2, float f3, float f4, float f5, float scale) {
        super.render(entityIn, f1, f2, f3, f4, f5, scale);
    }
}
