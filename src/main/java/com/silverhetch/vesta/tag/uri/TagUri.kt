package com.silverhetch.vesta.tag.uri

import java.net.URI

/**
 * Vesta`s tag uri
 */
interface TagUri {
    fun id(): Long
    fun value(): URI
    fun valid(): Boolean
}