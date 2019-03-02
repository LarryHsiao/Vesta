package com.silverhetch.vesta.tag.uri

import com.silverhetch.vesta.tag.Tag
import java.net.URI

/**
 * The implementation of [TagUri]
 */
class TagUriImpl : TagUri {
    private val uri: URI

    constructor(uri: URI) {
        this.uri = uri
    }

    constructor(tagId: Long) {
        this.uri = URI.create("vesta://silverhetch.com/tag/$tagId")
    }

    constructor(tag: Tag) : this(tag.id())

    override fun value(): URI {
        return uri
    }

    override fun valid(): Boolean {
        return uri.toString().startsWith("vesta://silverhetch.com/tag/")
    }

    override fun id(): Long {
        return uri.path.substringAfterLast("/").toLong()
    }
}