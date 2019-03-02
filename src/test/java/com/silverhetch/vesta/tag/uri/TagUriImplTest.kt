package com.silverhetch.vesta.tag.uri

import org.junit.Assert.*
import org.junit.Test
import java.net.URI

/**
 * Test object [TagUriImpl]
 */
class TagUriImplTest {
    /**
     * Check if uri is valid (valid).
     */
    @Test
    fun uri_valid() {
        val uri = TagUriImpl(URI.create("vesta://silverhetch.com/tag/"))
        assertTrue(uri.valid())
    }

    /**
     * Check if uri is valid (invalid).
     */
    @Test
    fun uri_invalid() {
        val uri = TagUriImpl(URI.create("vesta://invalid"))
        assertFalse(uri.valid())
    }
}