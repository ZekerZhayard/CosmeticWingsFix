package io.github.zekerzhayard.cosmeticwingsfix.asm;

import java.util.Map;
import java.util.ServiceLoader;

import io.github.zekerzhayard.cosmeticwingsfix.asm.transformers.AbstractTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@IFMLLoadingPlugin.MCVersion("1.8.9")
@IFMLLoadingPlugin.Name("CosmeticWingsFix")
@IFMLLoadingPlugin.TransformerExclusions("io.github.zekerzhayard.cosmeticwingsfix.asm.")
public class FMLLoadingPlugin implements IFMLLoadingPlugin {
    public final static Logger LOGGER = LogManager.getLogger("CosmeticWingsFix");

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
            SimpleTransformer.class.getName()
        };
    }

    @Override
    public String getModContainerClass() {
        ServiceLoader.load(AbstractTransformer.class).forEach(SimpleTransformer.INSTANCE::registerAll);
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
