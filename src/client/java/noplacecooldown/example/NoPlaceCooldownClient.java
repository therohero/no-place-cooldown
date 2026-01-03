package noplacecooldown.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class NoPlaceCooldownClient implements ClientModInitializer {

    private static KeyMapping toggleKey;

    @Override
    public void onInitializeClient() {
        // Registriere Hotkey R
        toggleKey = KeyBindingHelper.registerKeyBinding(
            new KeyMapping(
                "key.noplacecooldown.toggle",
                GLFW.GLFW_KEY_R,
                "category.noplacecooldown"
            )
        );

        // Jeden Tick prüfen
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleKey.consumeClick()) { // korrekt in 1.21.x
                // toggle
                NoPlaceCooldownState.enabled = !NoPlaceCooldownState.enabled;

                // Status in Actionbar
                if (client.player != null) {
                    client.player.displayClientMessage(
                        Component.literal(
                            NoPlaceCooldownState.enabled
                                ? "§aNo Place Cooldown: ON"
                                : "§cNo Place Cooldown: OFF"
                        ),
                        true
                    );
                }
            }
        });
    }
}