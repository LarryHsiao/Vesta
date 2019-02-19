package com.silverhetch.vesta.target

class VestaTarget(
    private val fileTarget: Target,
    dbTarget: Target
) : TargetWrapper(dbTarget) {
    override fun delete() {
        super.delete()
        fileTarget.delete()
    }
}