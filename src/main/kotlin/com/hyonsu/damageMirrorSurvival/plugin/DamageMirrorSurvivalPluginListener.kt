package com.hyonsu.damageMirrorSurvival.plugin

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

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
        if(e.player.gameMode == GameMode.CREATIVE) return

        e.isCancelled = true
    }
}
