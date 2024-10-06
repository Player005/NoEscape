package net.player005.no_escape.client.mixin;

import com.mojang.blaze3d.platform.Window;
import net.player005.no_escape.client.NoEscapeClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Window.class)
public class MixinAlwaysFullScreen {

    @Shadow
    private boolean fullscreen;

    @Inject(method = "toggleFullScreen", at = @At("HEAD"), cancellable = true)
    public void noExitFullscreen(CallbackInfo ci) {
        if (NoEscapeClient.blockActions()) {
            fullscreen = true;
            ci.cancel();
        }
    }

}
