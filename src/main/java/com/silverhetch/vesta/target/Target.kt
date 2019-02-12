package com.silverhetch.vesta.target

import java.net.URI

/**
 * Represent a real world object.
 */
interface Target {
    /**
     * The uri for the object
     */
    fun uri(): URI

    /**
     * delete this object.
     */
    fun delete()
}