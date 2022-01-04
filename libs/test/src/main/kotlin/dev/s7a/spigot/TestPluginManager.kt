package dev.s7a.spigot

import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.permissions.Permissible
import org.bukkit.permissions.Permission
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginLoader
import org.bukkit.plugin.PluginManager
import java.io.File

class TestPluginManager : PluginManager {
    override fun registerInterface(loader: Class<out PluginLoader>) {
        TODO("Not yet implemented")
    }

    override fun getPlugin(name: String): Plugin? {
        TODO("Not yet implemented")
    }

    override fun getPlugins(): Array<Plugin> {
        TODO("Not yet implemented")
    }

    override fun isPluginEnabled(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPluginEnabled(plugin: Plugin?): Boolean {
        TODO("Not yet implemented")
    }

    override fun loadPlugin(file: File): Plugin? {
        TODO("Not yet implemented")
    }

    override fun loadPlugins(directory: File): Array<Plugin> {
        TODO("Not yet implemented")
    }

    override fun disablePlugins() {
        TODO("Not yet implemented")
    }

    override fun clearPlugins() {
        TODO("Not yet implemented")
    }

    override fun callEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override fun registerEvents(listener: Listener, plugin: Plugin) {
        TODO("Not yet implemented")
    }

    override fun registerEvent(
        event: Class<out Event>,
        listener: Listener,
        priority: EventPriority,
        executor: EventExecutor,
        plugin: Plugin
    ) {
        TODO("Not yet implemented")
    }

    override fun registerEvent(
        event: Class<out Event>,
        listener: Listener,
        priority: EventPriority,
        executor: EventExecutor,
        plugin: Plugin,
        ignoreCancelled: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun enablePlugin(plugin: Plugin) {
        TODO("Not yet implemented")
    }

    override fun disablePlugin(plugin: Plugin) {
        TODO("Not yet implemented")
    }

    override fun getPermission(name: String): Permission? {
        TODO("Not yet implemented")
    }

    override fun addPermission(perm: Permission) {
        TODO("Not yet implemented")
    }

    override fun removePermission(perm: Permission) {
        TODO("Not yet implemented")
    }

    override fun removePermission(name: String) {
        TODO("Not yet implemented")
    }

    override fun getDefaultPermissions(op: Boolean): MutableSet<Permission> {
        TODO("Not yet implemented")
    }

    override fun recalculatePermissionDefaults(perm: Permission) {
        TODO("Not yet implemented")
    }

    override fun subscribeToPermission(permission: String, permissible: Permissible) {
        TODO("Not yet implemented")
    }

    override fun unsubscribeFromPermission(permission: String, permissible: Permissible) {
        TODO("Not yet implemented")
    }

    override fun getPermissionSubscriptions(permission: String): MutableSet<Permissible> {
        TODO("Not yet implemented")
    }

    override fun subscribeToDefaultPerms(op: Boolean, permissible: Permissible) {
        TODO("Not yet implemented")
    }

    override fun unsubscribeFromDefaultPerms(op: Boolean, permissible: Permissible) {
        TODO("Not yet implemented")
    }

    override fun getDefaultPermSubscriptions(op: Boolean): MutableSet<Permissible> {
        TODO("Not yet implemented")
    }

    override fun getPermissions(): MutableSet<Permission> {
        TODO("Not yet implemented")
    }

    override fun useTimings(): Boolean {
        TODO("Not yet implemented")
    }
}
