package co.za.kaylentravispillay.skelly.builder.type

import co.za.kaylentravispillay.skelly.bone.marrow.BoneMarrow

sealed class BoneConstructionType {
    data class Bone(val marrow: BoneMarrow) : BoneConstructionType()
    data class VerticalBoneStack(
        val marrow: BoneMarrow,
        val bones: List<BoneConstructionType>
    ) : BoneConstructionType()

    data class HorizontalBoneStack(
        val marrow: BoneMarrow,
        val bones: List<BoneConstructionType>
    ) : BoneConstructionType()
}