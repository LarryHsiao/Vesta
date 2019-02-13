package com.silverhetch.vesta.tag

/**
 * Tag that contents information
 */
interface Tag {
    fun id(): Long
    /**
     * The name of this tag
     */
    fun name(): String
}