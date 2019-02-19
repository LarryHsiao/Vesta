package com.silverhetch.vesta.target

/**
 * Represent a real world object.
 */
interface Target {
    fun id(): Long
    /**
     * The target name
     */
    fun name(): String

    /**
     * delete this object.
     */
    fun delete()
}