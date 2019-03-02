package com.silverhetch.vesta.attached.tags

import com.silverhetch.vesta.tag.Tag

/**
 * Represent a tag that attached to something.
 */
interface AttachedTag {
    /**
     * The original [Tag] intance.
     */
    fun tag(): Tag

    /**
     * Remove the [Tag] from attached object.s
     */
    fun remove()
}