package com.silverhetch.vesta.target

import com.silverhetch.vesta.database.H2DB
import org.junit.Assert.*
import org.junit.Test
import java.io.File
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
        DBTargets(
            H2DB(
                Files.createTempDirectory("temp").toFile().absolutePath
            ).connection()
        ).apply {
            init()
            add(URI("http://phantom.uri"))
            assertEquals(1, all().size)
        }
    }

    @Test
    fun delete_size() {
        DBTargets(
            H2DB(
                Files.createTempDirectory("temp").toFile().absolutePath
            ).connection()
        ).apply {
            init()
            add(URI("http://phantom.uri"))
            all()[0].delete()
            assertEquals(1, all().size)
        }
    }
}