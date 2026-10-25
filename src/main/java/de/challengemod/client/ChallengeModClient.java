package de.challengemod.client;

import de.challengemod.BlindPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ChallengeModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(BlindPayload.ID,
                (payload, context) -> BlindClient.apply(payload.blind(), payload.radius()));

        // Beim Verlassen des Servers wieder normale Texturen laden
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> BlindClient.reset());
    }
}
