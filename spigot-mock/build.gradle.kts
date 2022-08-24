dependencies {
    implementation(kotlin("test"))
    implementation("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("reflect"))
    testImplementation(project(":api:v1_19"))
    testImplementation(project(":showcase"))
}
