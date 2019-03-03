package com.silverhetch.vesta.downloads

import java.io.File
import java.io.FileOutputStream
import java.nio.channels.Channels
import java.nio.file.Paths
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Downloads all the given [Download] entry in thread pool.
 *
 * This class is good for small files(<10MB) because we don`t have any progress monitoring
 * which may cause UI hangs too long.
 *
 * @todo #11 implement with singleton pattern
 *
 * @todo #12 observer pattern for updating current download list.
 */
class DownloadServiceImpl(private val downloads: Downloads,
                          private val targetDir: File) : DownloadService {
    private lateinit var executorService: ExecutorService
    override fun start(onDone: (uri: File) -> Unit) {
        if (!::executorService.isInitialized || executorService.isShutdown) {
            executorService = Executors.newFixedThreadPool(4)
        }
        downloads.all().forEach { uri: String, download: Download ->
            executorService.submit {
                val downloadedFile = File(targetDir, Paths.get(uri).fileName.toString())
                FileOutputStream(
                    downloadedFile
                ).channel.transferFrom(
                    Channels.newChannel(download.uri().toURL().openStream()),
                    0,
                    Long.MAX_VALUE
                )
                download.delete()
                onDone(downloadedFile)
            }
        }
    }

    override fun stop() {
        executorService.shutdown()
    }
}