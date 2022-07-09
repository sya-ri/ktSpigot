package dev.s7a.ktspigot

import org.bukkit.FluidCollisionMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityCategory
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.entity.memory.MemoryKey
import org.bukkit.inventory.EntityEquipment
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.RayTraceResult
import org.bukkit.util.Vector
import java.util.UUID

abstract class TestLivingEntity(
    uniqueId: UUID,
    name: String,
    type: EntityType,
    location: Location
) : TestEntity(uniqueId, name, type, location), LivingEntity {
    private val equipment = TestEntityEquipment()

    override fun getAttribute(attribute: Attribute): AttributeInstance? {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double) {
        TODO("Not yet implemented")
    }

    override fun damage(amount: Double, source: Entity?) {
        TODO("Not yet implemented")
    }

    override fun getHealth(): Double {
        TODO("Not yet implemented")
    }

    override fun setHealth(health: Double) {
        TODO("Not yet implemented")
    }

    override fun getAbsorptionAmount(): Double {
        TODO("Not yet implemented")
    }

    override fun setAbsorptionAmount(amount: Double) {
        TODO("Not yet implemented")
    }

    override fun getMaxHealth(): Double {
        TODO("Not yet implemented")
    }

    override fun setMaxHealth(health: Double) {
        TODO("Not yet implemented")
    }

    override fun resetMaxHealth() {
        TODO("Not yet implemented")
    }

    override fun <T : Projectile> launchProjectile(projectile: Class<out T>): T {
        TODO("Not yet implemented")
    }

    override fun <T : Projectile> launchProjectile(projectile: Class<out T>, velocity: Vector?): T {
        TODO("Not yet implemented")
    }

    override fun getEyeHeight(): Double {
        TODO("Not yet implemented")
    }

    override fun getEyeHeight(ignorePose: Boolean): Double {
        TODO("Not yet implemented")
    }

    override fun getEyeLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun getLineOfSight(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
        TODO("Not yet implemented")
    }

    override fun getTargetBlock(transparent: MutableSet<Material>?, maxDistance: Int): Block {
        TODO("Not yet implemented")
    }

    override fun getLastTwoTargetBlocks(transparent: MutableSet<Material>?, maxDistance: Int): MutableList<Block> {
        TODO("Not yet implemented")
    }

    override fun getTargetBlockExact(maxDistance: Int): Block? {
        TODO("Not yet implemented")
    }

    override fun getTargetBlockExact(maxDistance: Int, fluidCollisionMode: FluidCollisionMode): Block? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(maxDistance: Double): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun rayTraceBlocks(maxDistance: Double, fluidCollisionMode: FluidCollisionMode): RayTraceResult? {
        TODO("Not yet implemented")
    }

    override fun getRemainingAir(): Int {
        TODO("Not yet implemented")
    }

    override fun setRemainingAir(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getMaximumAir(): Int {
        TODO("Not yet implemented")
    }

    override fun setMaximumAir(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getArrowCooldown(): Int {
        TODO("Not yet implemented")
    }

    override fun setArrowCooldown(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getArrowsInBody(): Int {
        TODO("Not yet implemented")
    }

    override fun setArrowsInBody(count: Int) {
        TODO("Not yet implemented")
    }

    override fun getMaximumNoDamageTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setMaximumNoDamageTicks(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getLastDamage(): Double {
        TODO("Not yet implemented")
    }

    override fun setLastDamage(damage: Double) {
        TODO("Not yet implemented")
    }

    override fun getNoDamageTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun setNoDamageTicks(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getKiller(): Player? {
        TODO("Not yet implemented")
    }

    override fun addPotionEffect(effect: PotionEffect): Boolean {
        TODO("Not yet implemented")
    }

    override fun addPotionEffect(effect: PotionEffect, force: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun addPotionEffects(effects: MutableCollection<PotionEffect>): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasPotionEffect(type: PotionEffectType): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPotionEffect(type: PotionEffectType): PotionEffect? {
        TODO("Not yet implemented")
    }

    override fun removePotionEffect(type: PotionEffectType) {
        TODO("Not yet implemented")
    }

    override fun getActivePotionEffects(): MutableCollection<PotionEffect> {
        TODO("Not yet implemented")
    }

    override fun hasLineOfSight(other: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRemoveWhenFarAway(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setRemoveWhenFarAway(remove: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getEquipment(): EntityEquipment? {
        return equipment
    }

    override fun setCanPickupItems(pickup: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getCanPickupItems(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLeashed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getLeashHolder(): Entity {
        TODO("Not yet implemented")
    }

    override fun setLeashHolder(holder: Entity?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isGliding(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setGliding(gliding: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isSwimming(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSwimming(swimming: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isRiptiding(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSleeping(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isClimbing(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAI(ai: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hasAI(): Boolean {
        TODO("Not yet implemented")
    }

    override fun attack(target: Entity) {
        TODO("Not yet implemented")
    }

    override fun swingMainHand() {
        TODO("Not yet implemented")
    }

    override fun swingOffHand() {
        TODO("Not yet implemented")
    }

    override fun setCollidable(collidable: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isCollidable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCollidableExemptions(): MutableSet<UUID> {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> getMemory(memoryKey: MemoryKey<T>): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> setMemory(memoryKey: MemoryKey<T>, memoryValue: T?) {
        TODO("Not yet implemented")
    }

    override fun getCategory(): EntityCategory {
        TODO("Not yet implemented")
    }

    override fun setInvisible(invisible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isInvisible(): Boolean {
        TODO("Not yet implemented")
    }
}
