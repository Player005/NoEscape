package net.player005.no_escape.client.mixin;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.player005.no_escape.client.NoEscapeClient;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.player005.no_escape.client.NoEscapeClient.blockActions;

@Mixin(value = Minecraft.class)
public abstract class MixinNoPause {

    @Shadow public abstract Window getWindow();

    @Inject(method = "pauseGame", at = @At("HEAD"), cancellable = true)
    public void noPause(boolean bl, CallbackInfo ci) {
        if (blockActions()) {
            ci.cancel();
        }
    }

    @Inject(method = {"destroy", "stop"}, at = @At("HEAD"), cancellable = true)
    public void noClosingWindow(@NotNull CallbackInfo ci) {
        if (blockActions()) {
            ci.cancel();
        }
    }

    @Inject(method = "run", at = @At("HEAD"))
    public void startFullscreen(CallbackInfo ci) {
        if (!getWindow().isFullscreen()) {
            getWindow().toggleFullScreen();
        }
    }
}
