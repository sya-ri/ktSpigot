package dev.s7a.ktspigot

import org.bukkit.BanList
import org.bukkit.GameMode
import org.bukkit.Keyed
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.OfflinePlayer
import org.bukkit.Server
import org.bukkit.StructureType
import org.bukkit.Tag
import org.bukkit.UnsafeValues
import org.bukkit.Warning
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.advancement.Advancement
import org.bukkit.block.data.BlockData
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarFlag
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.boss.KeyedBossBar
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.entity.SpawnCategory
import org.bukkit.event.inventory.InventoryType
import org.bukkit.generator.ChunkGenerator
import org.bukkit.help.HelpMap
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemFactory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Merchant
import org.bukkit.inventory.Recipe
import org.bukkit.loot.LootTable
import org.bukkit.map.MapView
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.ServicesManager
import org.bukkit.plugin.SimplePluginManager
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.Messenger
import org.bukkit.profile.PlayerProfile
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scoreboard.ScoreboardManager
import org.bukkit.structure.StructureManager
import org.bukkit.util.CachedServerIcon
import java.awt.image.BufferedImage
import java.io.File
import java.util.UUID
import java.util.function.Consumer
import java.util.logging.Logger

class TestServer : Server {
    private val commandMap = TestCommandMap(this)
    private val itemFactory = TestItemFactory()
    private val playerList = TestPlayerList()
    private val pluginManager = SimplePluginManager(this, commandMap)
    private val worldList = TestWorldList()

    fun addPlayer(uniqueId: UUID, name: String, location: Location): TestPlayer {
        return playerList.addPlayer(uniqueId, name, location)
    }

    fun setCommand(name: String, owner: JavaPlugin) {
        commandMap.register(name, owner.name, PluginCommand(name, owner))
    }

    override fun sendPluginMessage(source: Plugin, channel: String, message: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun getListeningPluginChannels(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun getName() = "MockServer"

    override fun getVersion() = "1.0.0"

    override fun getBukkitVersion() = "1.17.1-R0.1-SNAPSHOT"

    override fun getOnlinePlayers(): MutableCollection<out Player> {
        TODO("Not yet implemented")
    }

    override fun getMaxPlayers(): Int {
        TODO("Not yet implemented")
    }

    override fun getPort(): Int {
        TODO("Not yet implemented")
    }

    override fun getViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun getSimulationDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun getIp(): String {
        TODO("Not yet implemented")
    }

    override fun getWorldType(): String {
        TODO("Not yet implemented")
    }

    override fun getGenerateStructures(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getMaxWorldSize(): Int {
        TODO("Not yet implemented")
    }

    override fun getAllowEnd(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllowNether(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getResourcePack(): String {
        TODO("Not yet implemented")
    }

    override fun getResourcePackHash(): String {
        TODO("Not yet implemented")
    }

    override fun getResourcePackPrompt(): String {
        TODO("Not yet implemented")
    }

    override fun isResourcePackRequired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasWhitelist(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setWhitelist(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isWhitelistEnforced(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setWhitelistEnforced(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getWhitelistedPlayers(): MutableSet<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun reloadWhitelist() {
        TODO("Not yet implemented")
    }

    override fun broadcastMessage(message: String): Int {
        TODO("Not yet implemented")
    }

    override fun getUpdateFolder(): String {
        TODO("Not yet implemented")
    }

    override fun getUpdateFolderFile(): File {
        TODO("Not yet implemented")
    }

    override fun getConnectionThrottle(): Long {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAnimalSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerMonsterSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterAmbientSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterUndergroundCreatureSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAmbientSpawns(): Int {
        TODO("Not yet implemented")
    }

    override fun getTicksPerSpawns(spawnCategory: SpawnCategory): Int {
        TODO("Not yet implemented")
    }

    override fun getPlayer(name: String): Player? {
        TODO("Not yet implemented")
    }

    override fun getPlayer(id: UUID): Player? {
        return playerList.getPlayer(id)
    }

    override fun getPlayerExact(name: String): Player? {
        TODO("Not yet implemented")
    }

    override fun matchPlayer(name: String): MutableList<Player> {
        TODO("Not yet implemented")
    }

    override fun getPluginManager(): PluginManager {
        return pluginManager
    }

    override fun getScheduler(): BukkitScheduler {
        TODO("Not yet implemented")
    }

    override fun getServicesManager(): ServicesManager {
        TODO("Not yet implemented")
    }

    override fun getWorlds(): List<World> {
        return worldList.list
    }

    override fun createWorld(creator: WorldCreator): World {
        return TestWorld(creator.name()).apply(worldList::add)
    }

    override fun unloadWorld(name: String, save: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadWorld(world: World, save: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWorld(name: String) = worldList.get(name)

    override fun getWorld(uid: UUID): World? {
        TODO("Not yet implemented")
    }

    override fun getMap(id: Int): MapView? {
        TODO("Not yet implemented")
    }

    override fun createMap(world: World): MapView {
        TODO("Not yet implemented")
    }

    override fun createExplorerMap(world: World, location: Location, structureType: StructureType): ItemStack {
        TODO("Not yet implemented")
    }

    override fun createExplorerMap(
        world: World,
        location: Location,
        structureType: StructureType,
        radius: Int,
        findUnexplored: Boolean
    ): ItemStack {
        TODO("Not yet implemented")
    }

    override fun reload() {
        TODO("Not yet implemented")
    }

    override fun reloadData() {
        TODO("Not yet implemented")
    }

    override fun getLogger(): Logger = Logger.getLogger("MockServer")

    override fun getPluginCommand(name: String): PluginCommand? {
        return commandMap.getCommand(name) as? PluginCommand
    }

    override fun savePlayers() {
        TODO("Not yet implemented")
    }

    override fun dispatchCommand(sender: CommandSender, commandLine: String): Boolean {
        return commandMap.dispatch(sender, commandLine)
    }

    override fun addRecipe(recipe: Recipe?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRecipesFor(result: ItemStack): MutableList<Recipe> {
        TODO("Not yet implemented")
    }

    override fun getRecipe(recipeKey: NamespacedKey): Recipe? {
        TODO("Not yet implemented")
    }

    override fun getCraftingRecipe(craftingMatrix: Array<out ItemStack>, world: World): Recipe? {
        TODO("Not yet implemented")
    }

    override fun craftItem(craftingMatrix: Array<out ItemStack>, world: World, player: Player): ItemStack {
        TODO("Not yet implemented")
    }

    override fun recipeIterator(): MutableIterator<Recipe> {
        TODO("Not yet implemented")
    }

    override fun clearRecipes() {
        TODO("Not yet implemented")
    }

    override fun resetRecipes() {
        TODO("Not yet implemented")
    }

    override fun removeRecipe(key: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCommandAliases(): MutableMap<String, Array<String>> {
        TODO("Not yet implemented")
    }

    override fun getSpawnRadius(): Int {
        TODO("Not yet implemented")
    }

    override fun setSpawnRadius(value: Int) {
        TODO("Not yet implemented")
    }

    override fun getHideOnlinePlayers(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getOnlineMode(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllowFlight(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isHardcore(): Boolean {
        TODO("Not yet implemented")
    }

    override fun shutdown() {
        TODO("Not yet implemented")
    }

    override fun broadcast(message: String, permission: String): Int {
        TODO("Not yet implemented")
    }

    override fun getOfflinePlayer(name: String): OfflinePlayer {
        TODO("Not yet implemented")
    }

    override fun getOfflinePlayer(id: UUID): OfflinePlayer {
        return playerList.getOfflinePlayer(id)
    }

    override fun createPlayerProfile(uniqueId: UUID?, name: String?): PlayerProfile {
        TODO("Not yet implemented")
    }

    override fun createPlayerProfile(uniqueId: UUID): PlayerProfile {
        TODO("Not yet implemented")
    }

    override fun createPlayerProfile(name: String): PlayerProfile {
        TODO("Not yet implemented")
    }

    override fun getIPBans(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun banIP(address: String) {
        TODO("Not yet implemented")
    }

    override fun unbanIP(address: String) {
        TODO("Not yet implemented")
    }

    override fun getBannedPlayers(): MutableSet<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun getBanList(type: BanList.Type): BanList {
        TODO("Not yet implemented")
    }

    override fun getOperators(): MutableSet<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun getDefaultGameMode(): GameMode {
        TODO("Not yet implemented")
    }

    override fun setDefaultGameMode(mode: GameMode) {
        TODO("Not yet implemented")
    }

    override fun getConsoleSender(): ConsoleCommandSender {
        TODO("Not yet implemented")
    }

    override fun getWorldContainer(): File {
        TODO("Not yet implemented")
    }

    override fun getOfflinePlayers(): Array<OfflinePlayer> {
        TODO("Not yet implemented")
    }

    override fun getMessenger(): Messenger {
        TODO("Not yet implemented")
    }

    override fun getHelpMap(): HelpMap {
        TODO("Not yet implemented")
    }

    override fun createInventory(owner: InventoryHolder?, type: InventoryType): Inventory {
        return TestInventory(owner, type)
    }

    override fun createInventory(owner: InventoryHolder?, type: InventoryType, title: String): Inventory {
        return TestInventory(owner, type, title = title)
    }

    override fun createInventory(owner: InventoryHolder?, size: Int): Inventory {
        return TestInventory(owner, InventoryType.CHEST, size)
    }

    override fun createInventory(owner: InventoryHolder?, size: Int, title: String): Inventory {
        return TestInventory(owner, InventoryType.CHEST, size, title)
    }

    override fun createMerchant(title: String?): Merchant {
        TODO("Not yet implemented")
    }

    override fun getMonsterSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getWaterAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getWaterAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getWaterUndergroundCreatureSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun getSpawnLimit(spawnCategory: SpawnCategory): Int {
        TODO("Not yet implemented")
    }

    override fun isPrimaryThread(): Boolean {
        return true // TODO
    }

    override fun getMotd(): String {
        TODO("Not yet implemented")
    }

    override fun getShutdownMessage(): String? {
        TODO("Not yet implemented")
    }

    override fun getWarningState(): Warning.WarningState {
        TODO("Not yet implemented")
    }

    override fun getItemFactory(): ItemFactory {
        return itemFactory
    }

    override fun getScoreboardManager(): ScoreboardManager? {
        TODO("Not yet implemented")
    }

    override fun getServerIcon(): CachedServerIcon? {
        TODO("Not yet implemented")
    }

    override fun loadServerIcon(file: File): CachedServerIcon {
        TODO("Not yet implemented")
    }

    override fun loadServerIcon(image: BufferedImage): CachedServerIcon {
        TODO("Not yet implemented")
    }

    override fun setIdleTimeout(threshold: Int) {
        TODO("Not yet implemented")
    }

    override fun getIdleTimeout(): Int {
        TODO("Not yet implemented")
    }

    override fun createChunkData(world: World): ChunkGenerator.ChunkData {
        TODO("Not yet implemented")
    }

    override fun createBossBar(title: String?, color: BarColor, style: BarStyle, vararg flags: BarFlag?): BossBar {
        TODO("Not yet implemented")
    }

    override fun createBossBar(
        key: NamespacedKey,
        title: String?,
        color: BarColor,
        style: BarStyle,
        vararg flags: BarFlag?
    ): KeyedBossBar {
        TODO("Not yet implemented")
    }

    override fun getBossBars(): MutableIterator<KeyedBossBar> {
        TODO("Not yet implemented")
    }

    override fun getBossBar(key: NamespacedKey): KeyedBossBar? {
        TODO("Not yet implemented")
    }

    override fun removeBossBar(key: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEntity(uuid: UUID): Entity? {
        return worlds.firstNotNullOfOrNull { world ->
            world.entities.firstOrNull { it.uniqueId == uuid }
        }
    }

    override fun getAdvancement(key: NamespacedKey): Advancement? {
        TODO("Not yet implemented")
    }

    override fun advancementIterator(): MutableIterator<Advancement> {
        TODO("Not yet implemented")
    }

    override fun createBlockData(material: Material): BlockData {
        TODO("Not yet implemented")
    }

    override fun createBlockData(material: Material, consumer: Consumer<BlockData>?): BlockData {
        TODO("Not yet implemented")
    }

    override fun createBlockData(data: String): BlockData {
        TODO("Not yet implemented")
    }

    override fun createBlockData(material: Material?, data: String?): BlockData {
        TODO("Not yet implemented")
    }

    override fun <T : Keyed?> getTag(registry: String, tag: NamespacedKey, clazz: Class<T>): Tag<T>? {
        TODO("Not yet implemented")
    }

    override fun <T : Keyed?> getTags(registry: String, clazz: Class<T>): MutableIterable<Tag<T>> {
        TODO("Not yet implemented")
    }

    override fun getLootTable(key: NamespacedKey): LootTable? {
        TODO("Not yet implemented")
    }

    override fun selectEntities(sender: CommandSender, selector: String): MutableList<Entity> {
        TODO("Not yet implemented")
    }

    override fun getStructureManager(): StructureManager {
        TODO("Not yet implemented")
    }

    override fun getUnsafe(): UnsafeValues {
        TODO("Not yet implemented")
    }

    override fun spigot(): Server.Spigot {
        TODO("Not yet implemented")
    }
}
