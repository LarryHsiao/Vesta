package com.silverhetch.vesta.target

import java.net.URI

/**
 * Represent a real world object.
 */
interface Target {
    fun id(): Long
    /**
     * The uri for the object
     */
    fun uri(): URI

    /**
     * delete this object.
     */
    fun delete()
}