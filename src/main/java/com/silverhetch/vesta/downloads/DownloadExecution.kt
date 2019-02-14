package com.silverhetch.vesta.downloads

/**
 * Download uris by uri.
 */
interface DownloadExecution {
    /**
     * Initialize threading and related resources
     */
    fun start(onDone: (uri: String) -> Unit)

    /**
     * Stop threading and release related resources
     */
    fun stop()
}