package com.silverhetch.vesta.attached.tags

import com.silverhetch.vesta.database.H2DB
import com.silverhetch.vesta.tag.DBTags
import com.silverhetch.vesta.target.db.DBTargets
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.nio.file.Files

class DBAttachedTagsTest {
    @Test
    fun initialized() {
        val dbConn = H2DB(
            Files.createTempDirectory("temp").toFile().absolutePath
        ).connection()
        val tempFile = File.createTempFile("temp", "target")
        DBTags(dbConn).init()
        DBTargets(dbConn).apply {
            init()
            add(tempFile)
            assertEquals(
                0,
                DBAttachedTags(dbConn, all().getValue(tempFile.name).id()).let {
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
        val tempFile = File.createTempFile("temp", "target")
        DBTags(dbConn).apply {
            init()
            add("TagName")
            val tag = all().getValue("TagName")
            DBTargets(dbConn).apply {
                init()
                add(tempFile)
                DBAttachedTags(dbConn, all().getValue(tempFile.name).id()).apply {
                    init()
                    add(tag)
                    assertEquals(1, all().size)
                }
            }
        }
    }
}