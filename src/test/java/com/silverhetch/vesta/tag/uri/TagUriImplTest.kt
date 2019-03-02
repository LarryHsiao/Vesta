package com.silverhetch.vesta.tag.uri

import org.junit.Assert.*
import org.junit.Test
import java.net.URI

class TagUriImplTest {
    @Test
    fun uri_valid() {
        val uri = TagUriImpl(URI.create("vesta://silverhetch.com/tag/"))
        assertTrue(uri.valid())
    }

    @Test
    fun uri_invalid() {
        val uri = TagUriImpl(URI.create("vesta://invalid"))
        assertFalse(uri.valid())
    }
}