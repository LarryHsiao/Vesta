package com.silverhetch.vesta.attached.tags

import com.silverhetch.vesta.tag.Tag

interface AttachedTag {
    fun tag(): Tag
    fun remove()
}