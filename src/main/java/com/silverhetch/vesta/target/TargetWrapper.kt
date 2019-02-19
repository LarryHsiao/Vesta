package com.silverhetch.vesta.target

/**
 * Target wrapper
 */
abstract class TargetWrapper(private val original: Target) : Target {
    override fun id(): Long {
        return original.id()
    }

    override fun name(): String {
        return original.name()
    }

    override fun delete() {
        original.delete()
    }
}