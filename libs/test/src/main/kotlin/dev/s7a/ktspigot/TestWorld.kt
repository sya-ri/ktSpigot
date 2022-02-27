package dev.s7a.ktspigot

import org.bukkit.BlockChangeDelegate
import org.bukkit.Chunk
import org.bukkit.ChunkSnapshot
import org.bukkit.Difficulty
import org.bukkit.Effect
import org.bukkit.FluidCollisionMode
import org.bukkit.GameRule
import org.bukkit.HeightMap
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Raid
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.StructureType
import org.bukkit.TreeType
import org.bukkit.World
import org.bukkit.WorldBorder
import org.bukkit.WorldType
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.block.BlockState
import org.bukkit.block.data.BlockData
import org.bukkit.boss.DragonBattle
import org.bukkit.entity.AbstractArrow
import org.bukkit.entity.Arrow
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.FallingBlock
import org.bukkit.entity.Item
import org.bukkit.entity.LightningStrike
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.generator.BiomeProvider
import org.bukkit.generator.BlockPopulator
import org.bukkit.generator.ChunkGenerator
import org.bukkit.inventory.ItemStack
import org.bukkit.material.MaterialData
import org.bukkit.metadata.MetadataValue
import org.bukkit.plugin.Plugin
import org.bukkit.util.BoundingBox
import org.bukkit.util.Consumer
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import java.io.File
import java.util.Random
import java.util.UUID
import java.util.function.Predicate

class TestWorld(private val name: String) : World {
    private var spawnLocation = Location(this, 0.0, 64.0, 0.0)
    private val entities = mutableListOf<Entity>()

    @Suppress("UNCHECKED_CAST")
    private fun <T : Entity> createEntity(location: Location, clazz: Class<T>): T {
        if (LivingEntity::class.java.isAssignableFrom(clazz)) {
            if (Zombie::class.java.isAssignableFrom(clazz)) {
                return TestZombie(UUID.randomUUID(), "", location) as T
            }
        }
        throw IllegalArgumentException("Cannot spawn an entity for " + clazz.name)
    }

    override fun getBiome(x: Int, z: Int): Biome {
        TODO("Not yet implemented")
    }

    override fun getBiome(location: Location): Biome {
        TODO("Not yet implemented")
    }

    override fun getBiome(x: Int, y: Int, z: Int): Biome {
        TODO("Not yet implemented")
    }

    override fun setBiome(x: Int, z: Int, bio: Biome) {
        TODO("Not yet implemented")
    }

    override fun setBiome(location: Location, biome: Biome) {
        TODO("Not yet implemented")
    }

    override fun setBiome(x: Int, y: Int, z: Int, biome: Biome) {
        TODO("Not yet implemented")
    }

    override fun getBlockState(location: Location): BlockState {
        TODO("Not yet implemented")
    }

    override fun getBlockState(x: Int, y: Int, z: Int): BlockState {
        TODO("Not yet implemented")
    }

    override fun getBlockData(location: Location): BlockData {
        TODO("Not yet implemented")
    }

    override fun getBlockData(x: Int, y: Int, z: Int): BlockData {
        TODO("Not yet implemented")
    }

    override fun getType(location: Location): Material {
        TODO("Not yet implemented")
    }

    override fun getType(x: Int, y: Int, z: Int): Material {
        TODO("Not yet implemented")
    }

    override fun setBlockData(location: Location, blockData: BlockData) {
        TODO("Not yet implemented")
    }

    override fun setBlockData(x: Int, y: Int, z: Int, blockData: BlockData) {
        TODO("Not yet implemented")
    }

    override fun setType(location: Location, material: Material) {
        TODO("Not yet implemented")
    }

    override fun setType(x: Int, y: Int, z: Int, material: Material) {
        TODO("Not yet implemented")
    }

    override fun generateTree(location: Location, type: TreeType): Boolean {
        TODO("Not yet implemented")
    }

    override fun generateTree(loc: Location, type: TreeType, delegate: BlockChangeDelegate): Boolean {
        TODO("Not yet implemented")
    }

    override fun generateTree(location: Location, random: Random, type: TreeType): Boolean {
        TODO("Not yet implemented")
    }

    override fun generateTree(
        location: Location,
        random: Random,
        type: TreeType,
        stateConsumer: Consumer<BlockState>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun spawnEntity(location: Location, type: EntityType): Entity {
        TODO("Not yet implemented")
    }

    override fun spawnEntity(loc: Location, type: EntityType, randomizeData: Boolean): Entity {
        TODO("Not yet implemented")
    }

    override fun getEntities(): List<Entity> {
        return entities
    }

    override fun getLivingEntities(): List<LivingEntity> {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> getEntitiesByClass(vararg classes: Class<T>?): MutableCollection<T> {
        TODO("Not yet implemented")
    }

    override fun <T : Entity?> getEntitiesByClass(cls: Class<T>): MutableCollection<T> {
        TODO("Not yet implemented")
    }

    override fun getEntitiesByClasses(vararg classes: Class<*>?): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun <T : Entity> spawn(location: Location, clazz: Class<T>): T {
        return spawn(location, clazz, null)
    }

    override fun <T : Entity> spawn(location: Location, clazz: Class<T>, function: Consumer<T>?): T {
        return spawn(location, clazz, true, function)
    }

    override fun <T : Entity> spawn(
        location: Location,
        clazz: Class<T>,
        randomizeData: Boolean,
        function: Consumer<T>?
    ): T {
        return createEntity(location, clazz).apply(entities::add).apply {
            function?.accept(this)
        }
    }

    override fun getName(): String {
        return name
    }

    override fun getUID(): UUID {
        TODO("Not yet implemented")
    }

    override fun getEnvironment(): World.Environment {
        TODO("Not yet implemented")
    }

    override fun getSeed(): Long {
        TODO("Not yet implemented")
    }

    override fun getMinHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun getMaxHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun sendPluginMessage(source: Plugin, channel: String, message: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun getListeningPluginChannels(): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun setMetadata(metadataKey: String, newMetadataValue: MetadataValue) {
        TODO("Not yet implemented")
    }

    override fun getMetadata(metadataKey: String): MutableList<MetadataValue> {
        TODO("Not yet implemented")
    }

    override fun hasMetadata(metadataKey: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeMetadata(metadataKey: String, owningPlugin: Plugin) {
        TODO("Not yet implemented")
    }

    override fun getBlockAt(x: Int, y: Int, z: Int): Block {
        return TestBlock()
    }

    override fun getBlockAt(location: Location): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(x: Int, z: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(location: Location): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(x: Int, z: Int, heightMap: HeightMap): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockYAt(location: Location, heightMap: HeightMap): Int {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(x: Int, z: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(location: Location): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(x: Int, z: Int, heightMap: HeightMap): Block {
        TODO("Not yet implemented")
    }

    override fun getHighestBlockAt(location: Location, heightMap: HeightMap): Block {
        TODO("Not yet implemented")
    }

    override fun getChunkAt(x: Int, z: Int): Chunk {
        TODO("Not yet implemented")
    }

    override fun getChunkAt(location: Location): Chunk {
        TODO("Not yet implemented")
    }

    override fun getChunkAt(block: Block): Chunk {
        TODO("Not yet implemented")
    }

    override fun isChunkLoaded(chunk: Chunk): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkLoaded(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getLoadedChunks(): Array<Chunk> {
        TODO("Not yet implemented")
    }

    override fun loadChunk(chunk: Chunk) {
        TODO("Not yet implemented")
    }

    override fun loadChunk(x: Int, z: Int) {
        TODO("Not yet implemented")
    }

    override fun loadChunk(x: Int, z: Int, generate: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkGenerated(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkInUse(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunk(chunk: Chunk): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunk(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunk(x: Int, z: Int, save: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun unloadChunkRequest(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun regenerateChunk(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun refreshChunk(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun isChunkForceLoaded(x: Int, z: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun setChunkForceLoaded(x: Int, z: Int, forced: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getForceLoadedChunks(): MutableCollection<Chunk> {
        TODO("Not yet implemented")
    }

    override fun addPluginChunkTicket(x: Int, z: Int, plugin: Plugin): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePluginChunkTicket(x: Int, z: Int, plugin: Plugin): Boolean {
        TODO("Not yet implemented")
    }

    override fun removePluginChunkTickets(plugin: Plugin) {
        TODO("Not yet implemented")
    }

    override fun getPluginChunkTickets(x: Int, z: Int): MutableCollection<Plugin> {
        TODO("Not yet implemented")
    }

    override fun getPluginChunkTickets(): MutableMap<Plugin, MutableCollection<Chunk>> {
        TODO("Not yet implemented")
    }

    override fun dropItem(location: Location, item: ItemStack): Item {
        TODO("Not yet implemented")
    }

    override fun dropItem(location: Location, item: ItemStack, function: Consumer<Item>?): Item {
        TODO("Not yet implemented")
    }

    override fun dropItemNaturally(location: Location, item: ItemStack): Item {
        TODO("Not yet implemented")
    }

    override fun dropItemNaturally(location: Location, item: ItemStack, function: Consumer<Item>?): Item {
        TODO("Not yet implemented")
    }

    override fun spawnArrow(location: Location, direction: Vector, speed: Float, spread: Float): Arrow {
        TODO("Not yet implemented")
    }

    override fun <T : AbstractArrow?> spawnArrow(
        location: Location,
        direction: Vector,
        speed: Float,
        spread: Float,
        clazz: Class<T>
    ): T {
        TODO("Not yet implemented")
    }

    override fun strikeLightning(loc: Location): LightningStrike {
        TODO("Not yet implemented")
    }

    override fun strikeLightningEffect(loc: Location): LightningStrike {
        TODO("Not yet implemented")
    }

    override fun getPlayers(): MutableList<Player> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(location: Location, x: Double, y: Double, z: Double): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(
        location: Location,
        x: Double,
        y: Double,
        z: Double,
        filter: Predicate<Entity>?
    ): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(boundingBox: BoundingBox): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun getNearbyEntities(boundingBox: BoundingBox, filter: Predicate<Entity>?): MutableCollection<Entity> {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(start: Location, direction: Vector, maxDistance: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        raySize: Double
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        filter: Predicate<Entity>?
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceEntities(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        raySize: Double,
        filter: Predicate<Entity>?
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(start: Location, direction: Vector, maxDistance: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        fluidCollisionMode: FluidCollisionMode
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        fluidCollisionMode: FluidCollisionMode,
        ignorePassableBlocks: Boolean
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTrace(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        fluidCollisionMode: FluidCollisionMode,
        ignorePassableBlocks: Boolean,
        raySize: Double,
        filter: Predicate<Entity>?
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun getSpawnLocation(): Location {
        return spawnLocation
    }

    override fun setSpawnLocation(location: Location): Boolean {
        return if (location.world === this) {
            spawnLocation = location
            true
        } else {
            false
        }
    }

    override fun setSpawnLocation(x: Int, y: Int, z: Int, angle: Float): Boolean {
        return setSpawnLocation(Location(this, x.toDouble(), y.toDouble(), z.toDouble(), angle, 0.0F))
    }

    override fun setSpawnLocation(x: Int, y: Int, z: Int): Boolean {
        return setSpawnLocation(x, y, z, 0.0F)
    }

    override fun getTime(): Long {
        TODO("Not yet implemented")
    }

    override fun setTime(time: Long) {
        TODO("Not yet implemented")
    }

    override fun getFullTime(): Long {
        TODO("Not yet implemented")
    }

    override fun setFullTime(time: Long) {
        TODO("Not yet implemented")
    }

    override fun getGameTime(): Long {
        TODO("Not yet implemented")
    }

    override fun hasStorm(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setStorm(hasStorm: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getWeatherDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun setWeatherDuration(duration: Int) {
        TODO("Not yet implemented")
    }

    override fun isThundering(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setThundering(thundering: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getThunderDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun setThunderDuration(duration: Int) {
        TODO("Not yet implemented")
    }

    override fun isClearWeather(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setClearWeatherDuration(duration: Int) {
        TODO("Not yet implemented")
    }

    override fun getClearWeatherDuration(): Int {
        TODO("Not yet implemented")
    }

    override fun createExplosion(x: Double, y: Double, z: Double, power: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(x: Double, y: Double, z: Double, power: Float, setFire: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(
        x: Double,
        y: Double,
        z: Double,
        power: Float,
        setFire: Boolean,
        breakBlocks: Boolean
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(
        x: Double,
        y: Double,
        z: Double,
        power: Float,
        setFire: Boolean,
        breakBlocks: Boolean,
        source: Entity?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(loc: Location, power: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(loc: Location, power: Float, setFire: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(loc: Location, power: Float, setFire: Boolean, breakBlocks: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun createExplosion(
        loc: Location,
        power: Float,
        setFire: Boolean,
        breakBlocks: Boolean,
        source: Entity?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPVP(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setPVP(pvp: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getGenerator(): ChunkGenerator? {
        TODO("Not yet implemented")
    }

    override fun getBiomeProvider(): BiomeProvider? {
        TODO("Not yet implemented")
    }

    override fun save() {
        TODO("Not yet implemented")
    }

    override fun getPopulators(): MutableList<BlockPopulator> {
        TODO("Not yet implemented")
    }

    override fun spawnFallingBlock(location: Location, data: MaterialData): FallingBlock {
        TODO("Not yet implemented")
    }

    override fun spawnFallingBlock(location: Location, data: BlockData): FallingBlock {
        TODO("Not yet implemented")
    }

    override fun spawnFallingBlock(location: Location, material: Material, data: Byte): FallingBlock {
        TODO("Not yet implemented")
    }

    override fun playEffect(location: Location, effect: Effect, data: Int) {
        TODO("Not yet implemented")
    }

    override fun playEffect(location: Location, effect: Effect, data: Int, radius: Int) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> playEffect(location: Location, effect: Effect, data: T?) {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> playEffect(location: Location, effect: Effect, data: T?, radius: Int) {
        TODO("Not yet implemented")
    }

    override fun getEmptyChunkSnapshot(
        x: Int,
        z: Int,
        includeBiome: Boolean,
        includeBiomeTemp: Boolean
    ): ChunkSnapshot {
        TODO("Not yet implemented")
    }

    override fun setSpawnFlags(allowMonsters: Boolean, allowAnimals: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getAllowAnimals(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllowMonsters(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTemperature(x: Int, z: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getTemperature(x: Int, y: Int, z: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getHumidity(x: Int, z: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getHumidity(x: Int, y: Int, z: Int): Double {
        TODO("Not yet implemented")
    }

    override fun getLogicalHeight(): Int {
        TODO("Not yet implemented")
    }

    override fun isNatural(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBedWorks(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasSkyLight(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasCeiling(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isPiglinSafe(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isRespawnAnchorWorks(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasRaids(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isUltraWarm(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSeaLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun getKeepSpawnInMemory(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setKeepSpawnInMemory(keepLoaded: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isAutoSave(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAutoSave(value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setDifficulty(difficulty: Difficulty) {
        TODO("Not yet implemented")
    }

    override fun getDifficulty(): Difficulty {
        TODO("Not yet implemented")
    }

    override fun getWorldFolder(): File {
        TODO("Not yet implemented")
    }

    override fun getWorldType(): WorldType? {
        TODO("Not yet implemented")
    }

    override fun canGenerateStructures(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isHardcore(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setHardcore(hardcore: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAnimalSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerAnimalSpawns(ticksPerAnimalSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerMonsterSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerMonsterSpawns(ticksPerMonsterSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerWaterSpawns(ticksPerWaterSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterAmbientSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerWaterAmbientSpawns(ticksPerAmbientSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerWaterUndergroundCreatureSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerWaterUndergroundCreatureSpawns(ticksPerWaterUndergroundCreatureSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getTicksPerAmbientSpawns(): Long {
        TODO("Not yet implemented")
    }

    override fun setTicksPerAmbientSpawns(ticksPerAmbientSpawns: Int) {
        TODO("Not yet implemented")
    }

    override fun getMonsterSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setMonsterSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setAnimalSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getWaterAnimalSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setWaterAnimalSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getWaterUndergroundCreatureSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setWaterUndergroundCreatureSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getWaterAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setWaterAmbientSpawnLimit(limit: Int) {
        TODO("Not yet implemented")
    }

    override fun getAmbientSpawnLimit(): Int {
        TODO("Not yet implemented")
    }

    override fun setAmbientSpawnLimit(limit: Int) {
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

    override fun getGameRules(): Array<String> {
        TODO("Not yet implemented")
    }

    override fun getGameRuleValue(rule: String?): String? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getGameRuleValue(rule: GameRule<T>): T? {
        TODO("Not yet implemented")
    }

    override fun setGameRuleValue(rule: String, value: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun isGameRule(rule: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getGameRuleDefault(rule: GameRule<T>): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> setGameRule(rule: GameRule<T>, newValue: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWorldBorder(): WorldBorder {
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

    override fun <T : Any?> spawnParticle(
        particle: Particle,
        location: Location,
        count: Int,
        offsetX: Double,
        offsetY: Double,
        offsetZ: Double,
        extra: Double,
        data: T?,
        force: Boolean
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
        data: T?,
        force: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun locateNearestStructure(
        origin: Location,
        structureType: StructureType,
        radius: Int,
        findUnexplored: Boolean
    ): Location? {
        TODO("Not yet implemented")
    }

    override fun getViewDistance(): Int {
        TODO("Not yet implemented")
    }

    override fun spigot(): World.Spigot {
        TODO("Not yet implemented")
    }

    override fun locateNearestRaid(location: Location, radius: Int): Raid? {
        TODO("Not yet implemented")
    }

    override fun getRaids(): MutableList<Raid> {
        TODO("Not yet implemented")
    }

    override fun getEnderDragonBattle(): DragonBattle? {
        TODO("Not yet implemented")
    }
}
