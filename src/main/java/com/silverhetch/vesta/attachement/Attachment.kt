package com.silverhetch.vesta.attachement

import com.silverhetch.vesta.tag.Tag

interface Attachment {
    fun tag(): Tag
    fun remove()
}