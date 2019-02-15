package com.silverhetch.vesta.downloads

import java.io.File

/**
 * Download uris by uri.
 */
interface DownloadService {
    /**
     * Initialize threading and related resources
     */
    fun start(onDone: (downloadedFile: File) -> Unit)

    /**
     * Stop threading and release related resources
     */
    fun stop()
}