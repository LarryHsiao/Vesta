package com.silverhetch.vesta.tag.uri

import java.net.URI

/**
 * Vesta`s tag uri
 */
interface TagUri {
    /**
     * The [Tag] id that this uri represents.
     */
    fun id(): Long

    /**
     * The real uri object
     */
    fun value(): URI

    /**
     * Check the uri is still valid.
     */
    fun valid(): Boolean
}