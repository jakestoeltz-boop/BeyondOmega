package com.beyondomega.client;

import com.beyondomega.BeyondOmega;
import com.beyondomega.entity.custom.MinotaurEntity;
import com.beyondomega.sound.ModSounds;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.Music;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.SelectMusicEvent;

public final class MinotaurMusicHandler {

    // How close the player must be to the Minotaur
    // for the boss music to play.
    private static final double MUSIC_RANGE = 32.0D;


    // Min delay = 0
    // Max delay = 0
    // Replace current music = true
    //
    // This means the boss track starts immediately
    // and can restart immediately if it finishes.
    private static final Music MINOTAUR_MUSIC =
            new Music(
                    ModSounds.MINOTAUR_MUSIC,
                    0,
                    0,
                    true
            );


    // Used so we can kill the boss music when
    // the fight ends.
    private static boolean wasInMinotaurFight = false;


    private MinotaurMusicHandler() {
    }


    @EventBusSubscriber(
            modid = BeyondOmega.MOD_ID,
            value = Dist.CLIENT
    )
    public static class Events {

        @SubscribeEvent
        public static void onSelectMusic(
                SelectMusicEvent event
        ) {

            Minecraft minecraft =
                    Minecraft.getInstance();


            // Make sure a world/player actually exists.
            if (minecraft.level == null
                    || minecraft.player == null) {

                wasInMinotaurFight = false;

                return;
            }


            double rangeSqr =
                    MUSIC_RANGE * MUSIC_RANGE;


            // Look for a living Minotaur near the player.
            boolean minotaurNearby =
                    !minecraft.level.getEntitiesOfClass(
                            MinotaurEntity.class,

                            minecraft.player
                                    .getBoundingBox()
                                    .inflate(MUSIC_RANGE),

                            minotaur ->
                                    minotaur.isAlive()

                                            && minotaur.distanceToSqr(
                                            minecraft.player
                                    ) <= rangeSqr
                    ).isEmpty();


            // =================================================
            // MINOTAUR FIGHT
            // =================================================

            if (minotaurNearby) {

                wasInMinotaurFight = true;

                // Immediately replace normal Minecraft music
                // with the Minotaur boss music.
                event.overrideMusic(
                        MINOTAUR_MUSIC
                );

                return;
            }


            // =================================================
            // FIGHT JUST ENDED
            // =================================================

            if (wasInMinotaurFight) {

                wasInMinotaurFight = false;

                /*
                 * Setting music to null stops whatever
                 * music is currently playing.
                 *
                 * On later music checks, normal Minecraft
                 * music is free to resume.
                 */
                event.overrideMusic(null);
            }
        }
    }
}