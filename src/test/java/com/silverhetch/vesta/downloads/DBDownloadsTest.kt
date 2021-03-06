package com.silverhetch.vesta.downloads

import com.silverhetch.vesta.database.H2DB
import org.junit.Assert
import org.junit.Test
import java.net.URI
import java.nio.file.Files

/**
 * DBDownloads testing
 */
class DBDownloadsTest {
    /**
     * Empty database after first time initialization
     */
    @Test
    fun initial() {
        DBDownloads(
            H2DB(Files.createTempDirectory("temp").toFile().absolutePath).connection()
        ).apply {
            init()
            Assert.assertEquals(0, all().size)
        }
    }

    /**
     * Delete
     */
    @Test
    fun delete() {
        DBDownloads(
            H2DB(Files.createTempDirectory("temp").toFile().absolutePath).connection()
        ).apply {
            init()
            new(URI("https://localhost"))
            all().getValue("https://localhost").delete()
            Assert.assertEquals(0, all().size)
        }
    }
}