package com.silverhetch.vesta.attached.tags

import com.silverhetch.vesta.tag.Tag

/**
 * Represent a object that have tags.
 */
interface AttachedTags {
    /**
     * Used for initialize when application fire up.
     */
    fun init()

    /**
     * All the [AttachedTag] object
     */
    fun all(): Map<String, AttachedTag>

    /**
     * Attach new tag to this object
     */
    fun add(tag: Tag)
}