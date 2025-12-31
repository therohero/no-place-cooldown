package com.example.mixin.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    
    @Shadow 
    private int rightClickDelay; // Dies ist die interne Variable

    @Inject(at = @At("HEAD"), method = "tick()V") // Wird jeden Tick aufgerufen
    private void removePlaceDelay(CallbackInfo info) {
        // Setzt den Timer auf 0, damit sofort wieder platziert werden kann
        this.rightClickDelay = 0;
    }
}