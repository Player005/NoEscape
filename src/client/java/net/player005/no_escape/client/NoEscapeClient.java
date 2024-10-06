package net.player005.no_escape.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class NoEscapeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
    }

    public static boolean blockActions() {
        var window = Minecraft.getInstance().getWindow().getWindow();
        return !InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_SHIFT) || !InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_ALT);
    }
}
