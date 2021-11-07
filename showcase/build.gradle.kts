dependencies {
    implementation(project(":api:v1_13"))
    implementation("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}

task("generateCodeSnippet") {
    val snippetBeginLine = "// CODE-SNIPPET BEGIN"
    val snippetEndLine = "// CODE-SNIPPET END"
    val snippets = file("src/main/kotlin/showcase").listFiles().associate { file ->
        file.name to buildString {
            var isBegin = false
            file.readLines().forEach { line ->
                if (isBegin) {
                    if (line == snippetEndLine) {
                        isBegin = false
                    } else {
                        appendLine(line)
                    }
                } else if (line == snippetBeginLine) {
                    isBegin = true
                }
            }
        }
    }
    val directory = buildDir.resolve("snippets").apply(File::mkdirs)
    snippets.forEach { (fileName, content) ->
        directory.resolve(fileName).writeText(content)
    }
}
