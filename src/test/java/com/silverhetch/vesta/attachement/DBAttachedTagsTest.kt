package com.silverhetch.vesta.attachement

import com.silverhetch.vesta.database.H2DB
import com.silverhetch.vesta.tag.DBTags
import com.silverhetch.vesta.target.db.DBTargets
import org.junit.Assert.*
import org.junit.Test
import java.net.URI
import java.nio.file.Files

class DBAttachedTagsTest {
    @Test
    fun initialized() {
        val dbConn = H2DB(
            Files.createTempDirectory("temp").toFile().absolutePath
        ).connection()
        DBTags(dbConn).init()
        DBTargets(dbConn).apply {
            init()
            add(URI("http://phantom.uri"))
            assertEquals(
                0,
                DBAttachedTags(dbConn, all()[0]).let {
                    it.init()
                    it.all().size
                }
            )
        }
    }


    @Test
    fun add() {
        val dbConn = H2DB(
            Files.createTempDirectory("temp").toFile().absolutePath
        ).connection()
        DBTags(dbConn).apply {
            init()
            add("TagName")
            val tag = all().getValue("TagName")
            DBTargets(dbConn).apply {
                init()
                add(URI("http://phantom.uri"))
                DBAttachedTags(dbConn, all()[0]).apply {
                    init()
                    add(tag)
                    assertEquals(1, all().size)
                }
            }
        }
    }
}