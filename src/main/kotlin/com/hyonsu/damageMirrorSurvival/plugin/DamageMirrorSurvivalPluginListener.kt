package com.hyonsu.damageMirrorSurvival.plugin

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.GameMode
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class DamageMirrorSurvivalPluginListener : Listener {
    @EventHandler
    fun PlayerGiveDamage(e: EntityDamageByEntityEvent) {
        var causePlayer: Player? = null

        if(e.damager is Player) causePlayer = e.damager as Player
        if(e.damager is Arrow) {
            val causeArrow = e.damager as Arrow
            if(causeArrow.shooter is Player) causePlayer = causeArrow.shooter as Player
        }

        if(causePlayer == null) return

        causePlayer.sendMessage("${e.damage}")

        causePlayer.damage(e.damage)
    }

    @EventHandler
    fun PlayerJumpEvent(e: PlayerJumpEvent) {
        if(e.player.gameMode == GameMode.CREATIVE) return

        e.isCancelled = true
    }
}
