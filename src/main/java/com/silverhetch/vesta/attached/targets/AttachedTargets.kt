package com.silverhetch.vesta.attached.targets

import com.silverhetch.vesta.target.Target

/**
 * Attached targets
 */
interface AttachedTargets {
    /**
     * Initial the resources
     */
    fun init()

    /**
     * Attach new [Target] to this object.
     */
    fun add(target: Target)

    /**
     * All the attached [Target] object.
     */
    fun all(): Map<String, AttachedTarget>
}