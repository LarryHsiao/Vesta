package com.silverhetch.vesta.target

/**
 * Target object of vesta implementation.
 */
class VestaTarget(
    private val fileTarget: Target,
    dbTarget: Target
) : TargetWrapper(dbTarget) {
    override fun delete() {
        super.delete()
        fileTarget.delete()
    }
}