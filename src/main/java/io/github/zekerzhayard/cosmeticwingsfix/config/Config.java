package io.github.zekerzhayard.cosmeticwingsfix.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

public class Config {
    private final static Path CONFIG_PATH = Paths.get("CosmeticWings.json");
    private static boolean needUpdate = true;
    private static NBTTagCompound nbtCache = new NBTTagCompound();

    public static void saveConfig(NBTTagCompound nbt) {
        nbtCache = (NBTTagCompound) nbt.copy();
        try {
            Files.write(CONFIG_PATH, nbt.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            needUpdate = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NBTTagCompound loadConfig() {
        try {
            if (needUpdate && Files.exists(CONFIG_PATH)) {
                nbtCache = JsonToNBT.getTagFromJson(new String(Files.readAllBytes(CONFIG_PATH)));
                needUpdate = false;
            }
        } catch (IOException | NBTException e) {
            e.printStackTrace();
        }
        return (NBTTagCompound) nbtCache.copy();
    }
}
