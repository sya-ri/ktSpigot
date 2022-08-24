package dev.s7a.ktspigot

class TestWorldList {
    private val _list = mutableListOf<TestWorld>()

    val list
        get() = _list.toList()

    fun add(world: TestWorld) {
        _list.add(world)
    }

    fun get(name: String) = _list.firstOrNull { it.name == name }
}
