package com.beyondomega.sound;

import com.beyondomega.BeyondOmega;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {


    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(
                    BuiltInRegistries.SOUND_EVENT,
                    BeyondOmega.MOD_ID
            );


    // Random growl / idle sound
    public static final Holder<SoundEvent> MINOTAUR_AMBIENT =
            SOUND_EVENTS.register(
                    "minotaur_ambient",
                    SoundEvent::createVariableRangeEvent
            );


    // Normal melee attack
    public static final Holder<SoundEvent> MINOTAUR_ATTACK =
            SOUND_EVENTS.register(
                    "minotaur_attack",
                    SoundEvent::createVariableRangeEvent
            );


    // Warning sound before the slam animation begins
    public static final Holder<SoundEvent> MINOTAUR_BEFORE_SLAM =
            SOUND_EVENTS.register(
                    "minotaur_before_slam",
                    SoundEvent::createVariableRangeEvent
            );


    // Death sound
    public static final Holder<SoundEvent> MINOTAUR_DEATH =
            SOUND_EVENTS.register(
                    "minotaur_death",
                    SoundEvent::createVariableRangeEvent
            );


    // Heavy walking / footstep sound
    public static final Holder<SoundEvent> MINOTAUR_STEP =
            SOUND_EVENTS.register(
                    "minotaur_step",
                    SoundEvent::createVariableRangeEvent
            );


    // Ground impact
    public static final Holder<SoundEvent> MINOTAUR_SLAM =
            SOUND_EVENTS.register(
                    "minotaur_slam",
                    SoundEvent::createVariableRangeEvent
            );

    public static final Holder<SoundEvent> MINOTAUR_HURT =
            SOUND_EVENTS.register(
                    "minotaur_hurt",
                    SoundEvent::createVariableRangeEvent
            );

    public static final Holder<SoundEvent> MINOTAUR_MUSIC =
            SOUND_EVENTS.register(
                    "minotaur_music",
                    SoundEvent::createVariableRangeEvent
            );
    public static void register(IEventBus eventBus) {

        SOUND_EVENTS.register(eventBus);
    }
}
