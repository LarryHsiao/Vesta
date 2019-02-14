package com.silverhetch.vesta.downloads

import java.io.File
import java.io.FileOutputStream
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Downloads all the given [Download] entry in thread pool.
 */
class DownloadExecutionImpl(private val downloads: Downloads,
                            private val targetDir: File) : DownloadExecution {
    private lateinit var executorService: ExecutorService
    override fun start(onDone: (uri: String) -> Unit) {
        if (!::executorService.isInitialized || executorService.isShutdown) {
            executorService = Executors.newFixedThreadPool(4)
        }
        downloads.all().forEach { uri: String, download: Download ->
            executorService.submit {
                FileOutputStream(
                    File(targetDir, Paths.get(uri).fileName.toString())
                ).channel.transferFrom(
                    Channels.newChannel(download.uri().toURL().openStream()),
                    0,
                    Long.MAX_VALUE
                )
                download.delete()
                onDone(uri)
            }
        }
    }

    override fun stop() {
        executorService.shutdown()
    }
}