package com.silverhetch.vesta.target

import com.silverhetch.vesta.database.H2DB
import com.silverhetch.vesta.target.db.DBTargets
import org.junit.Assert.*
import org.junit.Test
import java.net.URI
import java.nio.file.Files

/**
 * Test for [DBTarget] and [DBTargets].
 */
class DBTargetTest {
    /**
     * Empty database after first initialization.
     */
    @Test
    fun initialized() {
        DBTargets(
            H2DB(
                Files.createTempDirectory("temp").toFile().absolutePath
            ).connection()
        ).apply {
            init()
            assertEquals(0, all().size)
        }
    }

    /**
     * Adding a file and check if data size is changed.
     */
    @Test
    fun add() {
        val tempFile = Files.createTempFile("temp", "temp").toFile()
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

    /**
     * Test delete function and check if size is correct.
     */
    @Test
    fun delete_size() {
        val tempFile = Files.createTempFile("temp", "temp").toFile()
        DBTargets(
            H2DB(
                Files.createTempDirectory("temp").toFile().absolutePath
            ).connection()
        ).apply {
            init()
            add(tempFile)
            all().getValue(tempFile.name).delete()
            assertEquals(0, all().size)
        }
    }
}