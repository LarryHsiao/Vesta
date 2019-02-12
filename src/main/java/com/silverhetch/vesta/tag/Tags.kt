package com.silverhetch.vesta.tag

/**
 * Object that builds [Tag] objects and create [Tag].
 */
interface Tags {
    /**
     * Used for initialize when application fire up.
     */
    fun init()

    /**
     * All the [Tag] object
     */
    fun all(): Array<Tag>

    /**
     * Add new [Tag]
     */
    fun add(name: String)
}