import dev.s7a.gradle.minecraft.server.tasks.LaunchMinecraftServerTask
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

apply(plugin = "net.minecrell.plugin-yml.bukkit")
apply(plugin = "dev.s7a.gradle.minecraft.server")

dependencies {
    shadowImplementation(project(":api:v1_17"))
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}

configure<BukkitPluginDescription> {
    name = project.name
    version = rootProject.version.toString()
    main = "dev.s7a.ktspigot.test.Main"
    apiVersion = "1.17"
    commands {
        register("test") {
            usage = "Usage: /test"
        }
    }
}

mapOf(
    "17" to "1.17.1",
).forEach { (name, version) ->
    task<LaunchMinecraftServerTask>("testMinecraftServer$name") {
        dependsOn("build")
        doFirst {
            copy {
                from(buildDir.resolve("libs/${project.name}.jar"))
                into(buildDir.resolve("MinecraftServer$name/plugins"))
            }
        }

        jarUrl.set(LaunchMinecraftServerTask.JarUrl.Paper(version))
        jarName.set("server.jar")
        serverDirectory.set(buildDir.resolve("MinecraftServer$name"))
        agreeEula.set(true)
    }
}
