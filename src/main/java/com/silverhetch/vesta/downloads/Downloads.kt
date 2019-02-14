package com.silverhetch.vesta.downloads

import java.net.URI

/**
 * Records downloads uri which not done yet.
 */
interface Downloads {
    /**
     * Initialize the database or related resources
     */
    fun init()

    /**
     * New download uri
     */
    fun new(uri: URI)

    /**
     * All the uri which not done yet.
     */
    fun all(): Map<String, URI>
}