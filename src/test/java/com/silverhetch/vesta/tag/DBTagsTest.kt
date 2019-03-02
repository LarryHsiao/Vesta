package com.silverhetch.vesta.tag

import com.silverhetch.vesta.database.H2DB
import com.silverhetch.vesta.tag.uri.TagUriImpl
import org.junit.Assert.*
import org.junit.Test
import java.net.URI
import java.nio.file.Files

/**
 * Test [DBTags]
 */
class DBTagsTest {
    /**
     * Initialization testing
     */
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

    /**
     * Adding tag to database
     */
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


    /**
     * Search by uri
     */
    @Test
    fun searchByUri() {
        DBTags(
            H2DB(
                Files.createTempDirectory("temp").toFile().absolutePath
            ).connection()
        ).apply {
            init()
            add("new tag name")
            assertEquals(
                "new tag name",
                byUri(TagUriImpl(URI("vesta://silverhetch.com/tag/1"))).name()
            )
        }
    }
}