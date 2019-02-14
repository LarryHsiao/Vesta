package com.silverhetch.vesta.downloads

import java.net.URI

/**
 * The download entry
 */
interface Download {
    /**
     * Id of this entry
     */
    fun id(): Long

    /**
     * Uri of this entry
     */
    fun uri(): URI

    /**
     * Delete this entry
     */
    fun delete()
}