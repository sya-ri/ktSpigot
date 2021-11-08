import java.util.regex.Matcher

dependencies {
    implementation(project(":api:v1_13"))
    implementation("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}

task("updateCodeSnippet") {
    doLast {
        val snippetBeginLine = "// CODE-SNIPPET BEGIN"
        val snippetEndLine = "// CODE-SNIPPET END"
        val snippets = file("src/main/kotlin/showcase").listFiles().associate { file ->
            file.nameWithoutExtension to buildString {
                var isBegin = false
                var trimText = ""
                file.readLines().forEach { line ->
                    if (isBegin) {
                        if (line.endsWith(snippetEndLine)) {
                            isBegin = false
                        } else {
                            appendLine(line.removePrefix(trimText))
                        }
                    } else if (line.endsWith(snippetBeginLine)) {
                        isBegin = true
                        trimText = line.removeSuffix(snippetBeginLine)
                    }
                }
            }
        }
        mapOf(
            "ExampleDataClass" to listOf(
                file("../wiki/Config.md"),
            ),
            "ItemConfig" to listOf(
                file("../README.md"),
                file("../wiki/Config.md"),
            ),
            "ItemStackType" to listOf(
                file("../wiki/Config.md"),
            ),
        ).forEach { fileName, dests ->
            val header = "<!-- CODE-SNIPPET BEGIN $fileName -->"
            val footer = "<!-- CODE-SNIPPET END $fileName -->"
            val regex = "$header[\\s\\S]*$footer".toRegex()
            val content = snippets[fileName]!!
            dests.forEach {
                it.writeText(it.readText().replace(regex, Matcher.quoteReplacement("$header\n```kotlin\n$content```\n$footer")))
            }
        }
    }
}
