package dev.s7a.ktspigot

import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.SpawnCategory
import org.bukkit.entity.Villager
import org.bukkit.entity.Zombie
import org.bukkit.loot.LootTable
import java.util.UUID

class TestZombie(
    uniqueId: UUID,
    name: String,
    location: Location
) : TestLivingEntity(uniqueId, name, EntityType.ZOMBIE, location), Zombie {
    override fun getSpawnCategory(): SpawnCategory {
        TODO("Not yet implemented")
    }

    override fun canBreatheUnderwater(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setLootTable(table: LootTable?) {
        TODO("Not yet implemented")
    }

    override fun getLootTable(): LootTable? {
        TODO("Not yet implemented")
    }

    override fun setSeed(seed: Long) {
        TODO("Not yet implemented")
    }

    override fun getSeed(): Long {
        TODO("Not yet implemented")
    }

    override fun setTarget(target: LivingEntity?) {
        TODO("Not yet implemented")
    }

    override fun getTarget(): LivingEntity? {
        TODO("Not yet implemented")
    }

    override fun setAware(aware: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isAware(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAge(): Int {
        TODO("Not yet implemented")
    }

    override fun setAge(age: Int) {
        TODO("Not yet implemented")
    }

    override fun setAgeLock(lock: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getAgeLock(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setBaby(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setBaby() {
        TODO("Not yet implemented")
    }

    override fun setAdult() {
        TODO("Not yet implemented")
    }

    override fun isAdult(): Boolean {
        TODO("Not yet implemented")
    }

    override fun canBreed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setBreed(breed: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isBaby(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isVillager(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setVillager(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setVillagerProfession(profession: Villager.Profession?) {
        TODO("Not yet implemented")
    }

    override fun getVillagerProfession(): Villager.Profession? {
        TODO("Not yet implemented")
    }

    override fun isConverting(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getConversionTime(): Int {
        TODO("Not yet implemented")
    }

    override fun setConversionTime(time: Int) {
        TODO("Not yet implemented")
    }

    override fun canBreakDoors(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setCanBreakDoors(flag: Boolean) {
        TODO("Not yet implemented")
    }
}
