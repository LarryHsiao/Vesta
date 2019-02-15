package com.silverhetch.vesta.target

import com.silverhetch.vesta.database.H2DB
import com.silverhetch.vesta.target.db.DBTargets
import org.junit.Assert.*
import org.junit.Test
import java.net.URI
import java.nio.file.Files

class DBTargetTest {
    @Test
    fun all_empty() {
        DBTargets(
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
        val tempFile = Files.createTempFile("temp","temp").toFile()
        DBTargets(
            H2DB(
                Files.createTempDirectory("temp").toFile().absolutePath
            ).connection()
        ).apply {
            init()
            add(tempFile)
            assertEquals(1, all().size)
        }
    }

    @Test
    fun delete_size() {
        val tempFile = Files.createTempFile("temp","temp").toFile()
        DBTargets(
            H2DB(
                Files.createTempDirectory("temp").toFile().absolutePath
            ).connection()
        ).apply {
            init()
            add(tempFile)
            all().getValue(tempFile.name).delete()
            assertEquals(1, all().size)
        }
    }
}