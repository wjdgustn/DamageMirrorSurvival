package com.hyonsu.damageMirrorSurvival.plugin

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.GameMode
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.HorseJumpEvent
import org.bukkit.event.player.PlayerMoveEvent

class DamageMirrorSurvivalPluginListener : Listener {
    @EventHandler
    fun PlayerGiveDamage(e: EntityDamageByEntityEvent) {
        var causePlayer: Player? = null

        if(e.damager is Player) causePlayer = e.damager as Player
        if(e.damager is Projectile) {
            val causeProjectile = e.damager as Projectile
            val shooter = causeProjectile.shooter
            if(shooter == null) return

            if(shooter is Player) causePlayer = causeProjectile.shooter as Player
        }

        if(causePlayer == null) return

        causePlayer.damage(e.damage)
    }

    @EventHandler
    fun PlayerJumpEvent(e: PlayerJumpEvent) {
        val player = e.player
        if (player.gameMode == GameMode.CREATIVE
            || player.isClimbing
            || e.player.world.getBlockAt(e.player.location).isLiquid
        ) return

        e.isCancelled = true
    }

    @EventHandler
    fun HorseJumpEvent(e: HorseJumpEvent) {
        val player = e.entity.passengers[0]
        if(player !is Player) return

        if(player.gameMode == GameMode.CREATIVE) return

        e.entity.jumpStrength = 0.0
    }

    @EventHandler
    fun PlayerMoveEvent(e: PlayerMoveEvent) {
        val player = e.player
        if(player.gameMode == GameMode.CREATIVE
            || player.vehicle !is AbstractHorse
            || e.to.y - e.from.y <= 0.5) return

        val horse = player.vehicle as AbstractHorse

        e.isCancelled = true
        horse.removePassenger(player)
    }
}
