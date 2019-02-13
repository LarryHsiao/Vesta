package com.silverhetch.vesta.attachement

import com.silverhetch.vesta.tag.Tag

interface Attachments {
    /**
     * Used for initialize when application fire up.
     */
    fun init()

    /**
     * All the [Attachment] object
     */
    fun all(): Map<String, Attachment>

    /**
     * Attach new tag to this object
     */
    fun add(tag: Tag)
}