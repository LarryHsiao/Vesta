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
 * Unit test for DBAttachedTargeds
 */
class DBAttachedTargetsTest {
    /**
     * Empty table after first initialization
     */
    @Test
    fun initial() {
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
                DBAttachedTargets(dbConn, all().getValue(tempFile.name).id()).let {
                    it.init()
                    it.all().size
                }
            )
        }
    }

    /**
     * Adding tag to target and check if it is in database.
     */
    @Test
    fun add_byAddingTag() {
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

                assertEquals(
                    1,
                    DBAttachedTargets(dbConn, 1).all().size
                )
            }
        }
    }

    /**
     * Adding target to tag and check if it is in database.
     */
    @Test
    fun add_byAddingTarget() {
        val dbConn = H2DB(
            Files.createTempDirectory("temp").toFile().absolutePath
        ).connection()
        val tempFile = File.createTempFile("temp", "target")
        DBTags(dbConn).apply {
            init()
            add("TagName")
            DBTargets(dbConn).apply {
                init()
                add(tempFile)

                DBAttachedTargets(dbConn, 1).let { targets ->
                    targets.init()
                    targets.add(all().getValue(tempFile.name))
                }

                assertEquals(
                    1,
                    DBAttachedTargets(dbConn, 1).all().size
                )
            }
        }
    }
}