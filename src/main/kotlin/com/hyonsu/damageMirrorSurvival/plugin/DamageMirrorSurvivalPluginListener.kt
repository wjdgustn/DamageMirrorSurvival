package com.hyonsu.damageMirrorSurvival.plugin

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageMirrorSurvivalPluginListener : Listener {
    @EventHandler
    fun PlayerGiveDamage(e: EntityDamageByEntityEvent) {
        if(e.damager !is Player) return

        val causePlayer = e.damager as Player
        causePlayer.damage(e.damage)
    }

    @EventHandler
    fun PlayerJumpEvent(e: PlayerJumpEvent) {
        if(e.player.gameMode == GameMode.CREATIVE) return

        e.isCancelled = true
    }
}
