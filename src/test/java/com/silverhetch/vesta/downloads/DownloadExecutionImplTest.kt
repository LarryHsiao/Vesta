package com.silverhetch.vesta.downloads

import com.silverhetch.vesta.database.H2DB
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.*

class DownloadExecutionImplTest {
    @Test
    fun downloadTest() {
        val countDownLatch = CountDownLatch(3)
        val targetDir = Files.createTempDirectory("downloadExecutionImplTest")
            .toFile()
            .apply { deleteOnExit() }
        DownloadExecutionImpl(
            DBDownloads(
                H2DB(Files.createTempDirectory("temp").toFile().absolutePath).connection()
            ).apply {
                init()
                new(URI("https://dummyimage.com/600x400/000/fff"))
                new(URI("https://dummyimage.com/600x400/111/eee"))
                new(URI("https://dummyimage.com/600x400/222/ddd"))
            }, targetDir
        ).start { doneUri ->
            Assert.assertTrue(File(targetDir, Paths.get(doneUri).fileName.toString()).exists())
            countDownLatch.countDown()
        }
        countDownLatch.await(10000, MILLISECONDS)
        Assert.assertEquals(0, countDownLatch.count)
    }
}