package command

import dev.s7a.ktspigot.command.Literals
import kotlin.test.Test
import kotlin.test.assertContentEquals

class LiteralsTest {
    @Suppress("unused")
    enum class TestEnum(val value: String) {
        One("1"),
        Two("2"),
        Three("3"),
    }

    @Test
    fun `enums can be converted`() {
        assertContentEquals(listOf("One", "Two", "Three"), Literals.EnumNames<TestEnum>())
        assertContentEquals(listOf("One", "Two"), Literals.EnumNames<TestEnum> { it != TestEnum.Three })
        assertContentEquals(listOf("1", "2", "3"), Literals.Enums(TestEnum::value))
        assertContentEquals(listOf("1", "2"), Literals.Enums(TestEnum::value) { it != TestEnum.Three })
    }
}
