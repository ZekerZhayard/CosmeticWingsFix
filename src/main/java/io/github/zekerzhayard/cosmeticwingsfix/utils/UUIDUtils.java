package io.github.zekerzhayard.cosmeticwingsfix.utils;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.client.Minecraft;

public class UUIDUtils {
    public static UUID getSelfUUID() {
        return Minecraft.getMinecraft().thePlayer.getUniqueID();
    }

    public static boolean compareSelfUUID(UUID uuid) {
        return uuid != null && uuid.equals(getSelfUUID());
    }

    public static boolean compareSelfUUIDWithHashMap(HashMap map, UUID uuid) {
        //return compareSelfUUID(uuid) || map.containsKey(uuid);
        return true;
    }
}
