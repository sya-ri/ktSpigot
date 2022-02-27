package dev.s7a.ktspigot

import org.bukkit.Chunk
import org.bukkit.FluidCollisionMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.BlockState
import org.bukkit.block.PistonMoveReaction
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.MetadataValue
import org.bukkit.plugin.Plugin
import org.bukkit.util.BoundingBox
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import org.bukkit.util.VoxelShape

class TestBlock : Block {
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

    override fun getData(): Byte {
        TODO("Not yet implemented")
    }

    override fun getBlockData(): BlockData {
        TODO("Not yet implemented")
    }

    override fun getRelative(modX: Int, modY: Int, modZ: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getRelative(face: BlockFace): Block {
        TODO("Not yet implemented")
    }

    override fun getRelative(face: BlockFace, distance: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getType(): Material {
        TODO("Not yet implemented")
    }

    override fun getLightLevel(): Byte {
        TODO("Not yet implemented")
    }

    override fun getLightFromSky(): Byte {
        TODO("Not yet implemented")
    }

    override fun getLightFromBlocks(): Byte {
        TODO("Not yet implemented")
    }

    override fun getWorld(): World {
        TODO("Not yet implemented")
    }

    override fun getX(): Int {
        TODO("Not yet implemented")
    }

    override fun getY(): Int {
        TODO("Not yet implemented")
    }

    override fun getZ(): Int {
        TODO("Not yet implemented")
    }

    override fun getLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun getLocation(loc: Location?): Location? {
        TODO("Not yet implemented")
    }

    override fun getChunk(): Chunk {
        TODO("Not yet implemented")
    }

    override fun setBlockData(data: BlockData) {
        TODO("Not yet implemented")
    }

    override fun setBlockData(data: BlockData, applyPhysics: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setType(type: Material) {
        TODO("Not yet implemented")
    }

    override fun setType(type: Material, applyPhysics: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getFace(block: Block): BlockFace? {
        TODO("Not yet implemented")
    }

    override fun getState(): BlockState {
        TODO("Not yet implemented")
    }

    override fun getBiome(): Biome {
        TODO("Not yet implemented")
    }

    override fun setBiome(bio: Biome) {
        TODO("Not yet implemented")
    }

    override fun isBlockPowered(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBlockIndirectlyPowered(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBlockFacePowered(face: BlockFace): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBlockFaceIndirectlyPowered(face: BlockFace): Boolean {
        TODO("Not yet implemented")
    }

    override fun getBlockPower(face: BlockFace): Int {
        TODO("Not yet implemented")
    }

    override fun getBlockPower(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLiquid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTemperature(): Double {
        TODO("Not yet implemented")
    }

    override fun getHumidity(): Double {
        TODO("Not yet implemented")
    }

    override fun getPistonMoveReaction(): PistonMoveReaction {
        TODO("Not yet implemented")
    }

    override fun breakNaturally(): Boolean {
        TODO("Not yet implemented")
    }

    override fun breakNaturally(tool: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    override fun applyBoneMeal(face: BlockFace): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDrops(): MutableCollection<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun getDrops(tool: ItemStack?): MutableCollection<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun getDrops(tool: ItemStack, entity: Entity?): MutableCollection<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun isPreferredTool(tool: ItemStack): Boolean {
        TODO("Not yet implemented")
    }

    override fun getBreakSpeed(player: Player): Float {
        TODO("Not yet implemented")
    }

    override fun isPassable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun rayTrace(
        start: Location,
        direction: Vector,
        maxDistance: Double,
        fluidCollisionMode: FluidCollisionMode
    ): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun getBoundingBox(): BoundingBox {
        TODO("Not yet implemented")
    }

    override fun getCollisionShape(): VoxelShape {
        TODO("Not yet implemented")
    }
}
