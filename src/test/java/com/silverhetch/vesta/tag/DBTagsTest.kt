package com.silverhetch.vesta.tag

import com.silverhetch.vesta.database.H2DB
import org.junit.Assert.*
import org.junit.Test
import java.nio.file.Files

class DBTagsTest {
    @Test
    fun initialized() {
        DBTags(
            H2DB(
                Files.createTempDirectory("temp").toFile().absolutePath
            ).connection()
        ).apply {
            init()
            assertEquals(0, all().size)
        }
    }


    @Test
    fun add() {
        DBTags(
            H2DB(
                Files.createTempDirectory("temp").toFile().absolutePath
            ).connection()
        ).apply {
            init()
            add("new tag name")
            assertEquals(1, all().size)
        }
    }
}