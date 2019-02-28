package com.silverhetch.vesta.attached.targets

import com.silverhetch.vesta.target.Target

/**
 * Represent target that attached to something.
 */
interface AttachedTarget {
    /**
     * The [Target] instance.
     */
    fun target(): Target

    /**
     * Remove this [Target] from current attached object.
     */
    fun remove()
}