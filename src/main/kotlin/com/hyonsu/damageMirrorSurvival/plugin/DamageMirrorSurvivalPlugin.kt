package com.hyonsu.damageMirrorSurvival.plugin

import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class DamageMirrorSurvivalPlugin: JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(DamageMirrorSurvivalPluginListener(), this)

        println("${ChatColor.GREEN}DamageMirrorSurvival plugin enabled")
    }
}