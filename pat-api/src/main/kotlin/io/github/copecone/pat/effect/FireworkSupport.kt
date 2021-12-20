package io.github.copecone.pat.effect

import org.bukkit.Color
import org.bukkit.FireworkEffect

@Suppress("UNUSED", "MemberVisibilityCanBePrivate")
object FireworkSupport {
    fun generateFireworkEffect(flicker: Boolean, trail: Boolean, type: FireworkEffect.Type, color: Color, fade: Color): FireworkEffect {
        val fireworkEffectBuilder: FireworkEffect.Builder = FireworkEffect.builder()
        fireworkEffectBuilder.flicker(flicker)
        fireworkEffectBuilder.trail(trail)
        fireworkEffectBuilder.with(type)
        fireworkEffectBuilder.withColor(color)
        fireworkEffectBuilder.withFade(fade)
        return fireworkEffectBuilder.build()
    }

    fun generateFireworkEffect(flicker: Boolean, type: FireworkEffect.Type, color: Color, fade: Color) =
        generateFireworkEffect(flicker, false, type, color, fade)

    fun generateFireworkEffect(type: FireworkEffect.Type, color: Color, fade: Color) =
        generateFireworkEffect(false, type, color, fade)

    fun generateFireworkEffect(type: FireworkEffect.Type, color: Color) =
        generateFireworkEffect(type, color, color)

    fun generateFireworkEffect(color: Color, fade: Color) =
        generateFireworkEffect(FireworkEffect.Type.BALL, color, fade)

    fun generateFireworkEffect(color: Color) =
        generateFireworkEffect(color, color)
}