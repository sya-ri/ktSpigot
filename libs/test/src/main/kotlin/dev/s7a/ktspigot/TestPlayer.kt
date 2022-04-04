package dev.s7a.ktspigot

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.Bukkit
import org.bukkit.DyeColor
import org.bukkit.Effect
import org.bukkit.GameMode
import org.bukkit.Instrument
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Note
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.Statistic
import org.bukkit.WeatherType
import org.bukkit.advancement.Advancement
import org.bukkit.advancement.AdvancementProgress
import org.bukkit.block.Block
import org.bukkit.block.Sign
import org.bukkit.block.data.BlockData
import org.bukkit.conversations.Conversation
import org.bukkit.conversations.ConversationAbandonedEvent
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.SpawnCategory
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.map.MapView
import org.bukkit.plugin.Plugin
import org.bukkit.profile.PlayerProfile
import org.bukkit.scoreboard.Scoreboard
import java.net.InetSocketAddress
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TestPlayer(
    uniqueId: UUID,
    name: String,
    location: Location
) : TestHumanEntity(uniqueId, name, EntityType.PLAYER, location), Player {
    private var actionbar: String? = null
    private var title: String? = null
    private var subtitle: String? = null

    fun assertActionBarIsEmpty() {
        assertNull(actionbar)
    }

    fun assertActionBar(message: String) {
        assertTrue(
            message == actionbar || "(§f)+$message".toRegex().matches(actionbar!!),
            "Expected <$message>, actual <$actionbar>."
        )
    }

    fun assertTitleIsEmpty() {
        assertTitle(null, null)
    }

    fun assertTitle(title: String? = null, subtitle: String? = null) {
        assertEquals(title, this.title)
        assertEquals(subtitle, this.subtitle)
    }

    override fun spigot(): Player.Spigot {
        return Spigot(this)
    }

    override fun playEffect(loc: Location, effect: Effect, data: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> playEffect(loc: Location, effect: Effect, data: T?) {
        TODO("Not yet implemented")
    }

    override fun getSpawnCategory(): SpawnCategory {
        TODO("Not yet implemented")
    }

    override fun isConversing(): Boolean {
        TODO("Not yet implemented")
    }

    override fun acceptConversationInput(input: String) {
        TODO("Not yet implemented")
    }

    override fun beginConversation(conversation: Conversation): Boolean {
        TODO("Not yet implemented")
    }

    override fun abandonConversation(conversation: Conversation) {
        TODO("Not yet implemented")
    }

    override fun abandonConversation(conversation: Conversation, details: ConversationAbandonedEvent) {
        TODO("Not yet implemented")
    }

    override fun sendRawMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun sendRawMessage(sender: UUID?, message: String) {
        TODO("Not yet implemented")
    }

    override fun serialize(): MutableMap<String, Any> {
        TODO("Not yet implemented")
    }

    override fun isOnline(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPlayerProfile(): PlayerProfile {
        TODO("Not yet implemented")
    }

    override fun isBanned(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isWhitelisted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setWhitelisted(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPlayer(): Player? {
        TODO("Not yet implemented")
    }

    override fun getFirstPlayed(): Long {
        TODO("Not yet implemented")
    }

    override fun getLastPlayed(): Long {
        TODO("Not yet implemented")
    }

    override fun hasPlayedBefore(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getBedSpawnLocation(): Location? {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, material: Material) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, material: Material, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, entityType: EntityType) {
        TODO("Not yet implemented")
    }

    override fun incrementStatistic(statistic: Statistic, entityType: EntityType, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, material: Material) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, material: Material, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, entityType: EntityType) {
        TODO("Not yet implemented")
    }

    override fun decrementStatistic(statistic: Statistic, entityType: EntityType, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun setStatistic(statistic: Statistic, newValue: Int) {
        TODO("Not yet implemented")
    }

    override fun setStatistic(statistic: Statistic, material: Material, newValue: Int) {
        TODO("Not yet implemented")
    }

    override fun setStatistic(statistic: Statistic, entityType: EntityType, newValue: Int) {
        TODO("Not yet implemented")
    }

    override fun getStatistic(statistic: Statistic): Int {
        TODO("Not yet implemented")
    }

    override fun getStatistic(statistic: Statistic, material: Material): Int {
        TODO("Not yet implemented")
    }

    override fun getStatistic(statistic: Statistic, entityType: EntityType): Int {
        TODO("Not yet implemented")
    }

    override fun sendPluginMessage(source: Plugin, channel: String, message: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun getListeningPluginChannels(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun getDisplayName(): String {
        TODO("Not yet implemented")
    }

    override fun setDisplayName(name: String?) {
        TODO("Not yet implemented")
    }

    override fun getPlayerListName(): String {
        TODO("Not yet implemented")
    }

    override fun setPlayerListName(name: String?) {
        TODO("Not yet implemented")
    }

    override fun getPlayerListHeader(): String? {
        TODO("Not yet implemented")
    }

    override fun getPlayerListFooter(): String? {
        TODO("Not yet implemented")
    }

    override fun setPlayerListHeader(header: String?) {
        TODO("Not yet implemented")
    }

    override fun setPlayerListFooter(footer: String?) {
        TODO("Not yet implemented")
    }

    override fun setPlayerListHeaderFooter(header: String?, footer: String?) {
        TODO("Not yet implemented")
    }

    override fun setCompassTarget(loc: Location) {
        TODO("Not yet implemented")
    }

    override fun getCompassTarget(): Location {
        TODO("Not yet implemented")
    }

    override fun getAddress(): InetSocketAddress? {
        TODO("Not yet implemented")
    }

    override fun kickPlayer(message: String?) {
        TODO("Not yet implemented")
    }

    override fun chat(msg: String) {
        TODO("Not yet implemented")
    }

    override fun performCommand(command: String): Boolean {
        return Bukkit.dispatchCommand(this, command)
    }

    override fun isSneaking(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSneaking(sneak: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isSprinting(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSprinting(sprinting: Boolean) {
        TODO("Not yet implemented")
    }

    override fun saveData() {
        TODO("Not yet implemented")
    }

    override fun loadData() {
        TODO("Not yet implemented")
    }

    override fun setSleepingIgnored(isSleeping: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isSleepingIgnored(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setBedSpawnLocation(location: Location?) {
        TODO("Not yet implemented")
    }

    override fun setBedSpawnLocation(location: Location?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun playNote(loc: Location, instrument: Byte, note: Byte) {
        TODO("Not yet implemented")
    }

    override fun playNote(loc: Location, instrument: Instrument, note: Note) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: Sound, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: String, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: Sound, category: SoundCategory, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(location: Location, sound: String, category: SoundCategory, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(entity: Entity, sound: Sound, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun playSound(entity: Entity, sound: Sound, category: SoundCategory, volume: Float, pitch: Float) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: Sound) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: String) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: Sound, category: SoundCategory?) {
        TODO("Not yet implemented")
    }

    override fun stopSound(sound: String, category: SoundCategory?) {
        TODO("Not yet implemented")
    }

    override fun stopAllSounds() {
        TODO("Not yet implemented")
    }

    override fun breakBlock(block: Block): Boolean {
        TODO("Not yet implemented")
    }

    override fun sendBlockChange(loc: Location, material: Material, data: Byte) {
        TODO("Not yet implemented")
    }

    override fun sendBlockChange(loc: Location, block: BlockData) {
        TODO("Not yet implemented")
    }

    override fun sendBlockDamage(loc: Location, progress: Float) {
        TODO("Not yet implemented")
    }

    override fun sendEquipmentChange(entity: LivingEntity, slot: EquipmentSlot, item: ItemStack) {
        TODO("Not yet implemented")
    }

    override fun sendSignChange(loc: Location, lines: Array<out String>?) {
        TODO("Not yet implemented")
    }

    override fun sendSignChange(loc: Location, lines: Array<out String>?, dyeColor: DyeColor) {
        TODO("Not yet implemented")
    }

    override fun sendSignChange(loc: Location, lines: Array<out String>?, dyeColor: DyeColor, hasGlowingText: Boolean) {
        TODO("Not yet implemented")
    }

    override fun sendMap(map: MapView) {
        TODO("Not yet implemented")
    }

    override fun updateInventory() {
        TODO("Not yet implemented")
    }

    override fun getPreviousGameMode(): GameMode? {
        TODO("Not yet implemented")
    }

    override fun setPlayerTime(time: Long, relative: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getPlayerTime(): Long {
        TODO("Not yet implemented")
    }

    override fun getPlayerTimeOffset(): Long {
        TODO("Not yet implemented")
    }

    override fun isPlayerTimeRelative(): Boolean {
        TODO("Not yet implemented")
    }

    override fun resetPlayerTime() {
        TODO("Not yet implemented")
    }

    override fun setPlayerWeather(type: WeatherType) {
        TODO("Not yet implemented")
    }

    override fun getPlayerWeather(): WeatherType? {
        TODO("Not yet implemented")
    }

    override fun resetPlayerWeather() {
        TODO("Not yet implemented")
    }

    override fun giveExp(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun giveExpLevels(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun getExp(): Float {
        TODO("Not yet implemented")
    }

    override fun setExp(exp: Float) {
        TODO("Not yet implemented")
    }

    override fun getLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun setLevel(level: Int) {
        TODO("Not yet implemented")
    }

    override fun getTotalExperience(): Int {
        TODO("Not yet implemented")
    }

    override fun setTotalExperience(exp: Int) {
        TODO("Not yet implemented")
    }

    override fun sendExperienceChange(progress: Float) {
        TODO("Not yet implemented")
    }

    override fun sendExperienceChange(progress: Float, level: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllowFlight(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAllowFlight(flight: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hidePlayer(player: Player) {
        TODO("Not yet implemented")
    }

    override fun hidePlayer(plugin: Plugin, player: Player) {
        TODO("Not yet implemented")
    }

    override fun showPlayer(player: Player) {
        TODO("Not yet implemented")
    }

    override fun showPlayer(plugin: Plugin, player: Player) {
        TODO("Not yet implemented")
    }

    override fun canSee(player: Player): Boolean {
        TODO("Not yet implemented")
    }

    override fun canSee(entity: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun hideEntity(plugin: Plugin, entity: Entity) {
        TODO("Not yet implemented")
    }

    override fun showEntity(plugin: Plugin, entity: Entity) {
        TODO("Not yet implemented")
    }

    override fun isFlying(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setFlying(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setFlySpeed(value: Float) {
        TODO("Not yet implemented")
    }

    override fun setWalkSpeed(value: Float) {
        TODO("Not yet implemented")
    }

    override fun getFlySpeed(): Float {
        TODO("Not yet implemented")
    }

    override fun getWalkSpeed(): Float {
        TODO("Not yet implemented")
    }

    override fun setTexturePack(url: String) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String, hash: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String, hash: ByteArray?, prompt: String?) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String, hash: ByteArray?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setResourcePack(url: String, hash: ByteArray?, prompt: String?, force: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getScoreboard(): Scoreboard {
        TODO("Not yet implemented")
    }

    override fun setScoreboard(scoreboard: Scoreboard) {
        TODO("Not yet implemented")
    }

    override fun isHealthScaled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setHealthScaled(scale: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setHealthScale(scale: Double) {
        TODO("Not yet implemented")
    }

    override fun getHealthScale(): Double {
        TODO("Not yet implemented")
    }

    override fun getSpectatorTarget(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setSpectatorTarget(entity: Entity?) {
        TODO("Not yet implemented")
    }

    override fun sendTitle(title: String?, subtitle: String?) {
        TODO("Not yet implemented")
    }

    override fun sendTitle(title: String?, subtitle: String?, fadeIn: Int, stay: Int, fadeOut: Int) {
        this.title = title
        this.subtitle = subtitle
    }

    override fun resetTitle() {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(particle: Particle, location: Location, count: Int) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(particle: Particle, x: Double, y: Double, z: Double, count: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(particle: Particle, location: Location, count: Int, data: T?) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(particle: Particle, x: Double, y: Double, z: Double, count: Int, data: T?) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        x: Double,
        y: Double,
        z: Double,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?
    ) {
        TODO("Not yet implemented")
    }

    override fun getAdvancementProgress(advancement: Advancement): AdvancementProgress {
        TODO("Not yet implemented")
    }

    override fun getClientViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun getPing(): Int {
        TODO("Not yet implemented")
    }

    override fun getLocale(): String {
        TODO("Not yet implemented")
    }

    override fun updateCommands() {
        TODO("Not yet implemented")
    }

    override fun openBook(book: ItemStack) {
        TODO("Not yet implemented")
    }

    override fun openSign(sign: Sign) {
        TODO("Not yet implemented")
    }

    override fun showDemoScreen() {
        TODO("Not yet implemented")
    }

    override fun isAllowingServerListings(): Boolean {
        TODO("Not yet implemented")
    }

    class Spigot(private val player: TestPlayer) : Player.Spigot() {
        override fun sendMessage(component: BaseComponent) {
            sendMessage(ChatMessageType.CHAT, component)
        }

        override fun sendMessage(vararg components: BaseComponent) {
            sendMessage(ChatMessageType.CHAT, *components)
        }

        override fun sendMessage(position: ChatMessageType, component: BaseComponent) {
            when (position) {
                ChatMessageType.CHAT -> player.sendMessage(component.toLegacyText())
                ChatMessageType.SYSTEM -> TODO()
                ChatMessageType.ACTION_BAR -> player.actionbar = component.toLegacyText()
            }
        }

        override fun sendMessage(position: ChatMessageType, vararg components: BaseComponent) {
            components.forEach {
                sendMessage(position, it)
            }
        }
    }
}
