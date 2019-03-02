package com.silverhetch.vesta.attached.targets

import com.silverhetch.vesta.attached.tags.DBAttachedTags
import com.silverhetch.vesta.database.H2DB
import com.silverhetch.vesta.tag.DBTags
import com.silverhetch.vesta.target.db.DBTargets
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.nio.file.Files

/**
 * Unit test
 */
class DBAttachedTargetTest{
    /**
     * Test remove function
     */
    @Test
    fun remove() {
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
                }

                DBAttachedTargets(dbConn, 1).all().getValue(tempFile.name).remove()

                assertEquals(
                    0,
                    DBAttachedTargets(dbConn, 1).all().size
                )
            }
        }
    }
}