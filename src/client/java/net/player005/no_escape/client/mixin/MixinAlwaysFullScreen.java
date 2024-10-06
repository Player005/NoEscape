package net.player005.no_escape.client.mixin;

import com.mojang.blaze3d.platform.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.player005.no_escape.client.NoEscapeClient.blockActions;

@Mixin(value = Window.class)
public class MixinAlwaysFullScreen {

    @Shadow
    private boolean fullscreen;

    @Shadow
    @Final
    private long window;

    @Inject(method = "toggleFullScreen", at = @At("HEAD"), cancellable = true)
    public void noExitFullscreen(CallbackInfo ci) {
        if (blockActions()) {
            fullscreen = true;
            ci.cancel();
        }
    }

    @Inject(method = "onFocus", at = @At("HEAD"))
    public void noSwitchWindow(long window, boolean focused, CallbackInfo ci) {
        if (window != this.window) {
            return;
        }
        if (blockActions()) {
            GLFW.glfwFocusWindow(this.window);
        }
    }
}