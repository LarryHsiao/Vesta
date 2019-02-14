package com.silverhetch.vesta

import com.silverhetch.vesta.downloads.DownloadService
import com.silverhetch.vesta.downloads.Downloads
import com.silverhetch.vesta.tag.Tags
import com.silverhetch.vesta.target.Targets
import java.io.File
import java.sql.Connection

/**
 * Object that holds Root file, database connection, etc which used in entire app.
 */
interface Vesta {
    /**
     * Root file jdk object
     */
    fun root(): File

    /**
     * The database connection this application use.
     */
    fun dbConnection(): Connection

    /**
     * Shutdown this object and release related resources.
     */
    fun shutdown()
}