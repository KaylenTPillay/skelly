package co.za.kaylentravispillay.skelly.builder

import android.content.Context
import co.za.kaylentravispillay.skelly.bone.Bone
import co.za.kaylentravispillay.skelly.bone.impl.BasicBone
import co.za.kaylentravispillay.skelly.bone.impl.BasicRibHorizontalStack
import co.za.kaylentravispillay.skelly.bone.impl.BasicRibVerticalStack
import co.za.kaylentravispillay.skelly.builder.annotation.SkellyDsl
import co.za.kaylentravispillay.skelly.builder.bone.BoneBuilder
import co.za.kaylentravispillay.skelly.builder.stack.HorizontalBoneStackBuilder
import co.za.kaylentravispillay.skelly.builder.stack.VerticalBoneStackBuilder
import co.za.kaylentravispillay.skelly.builder.type.BoneConstructionType

@SkellyDsl
class SkellyBuilder {
    private val mBoneCollection: MutableList<BoneConstructionType> = mutableListOf()

    fun bone(lambda: BoneBuilder.() -> Unit = {}) {
        mBoneCollection.add(BoneBuilder().apply(lambda).build())
    }

    fun verticalBoneStack(lambda: VerticalBoneStackBuilder.() -> Unit) {
        mBoneCollection.add(VerticalBoneStackBuilder().apply(lambda).build())
    }

    fun horizontalBoneStack(lambda: HorizontalBoneStackBuilder.() -> Unit) {
        mBoneCollection.add(HorizontalBoneStackBuilder().apply(lambda).build())
    }

    internal fun build(context: Context): List<Bone> {
        return mBoneCollection.map { boneConstructionType: BoneConstructionType ->
            boneConstructionType.createBone(context)
        }
    }

    private fun BoneConstructionType.createBone(context: Context): Bone {
        return when (this) {
            is BoneConstructionType.Bone -> BasicBone(context, marrow)
            is BoneConstructionType.VerticalBoneStack -> BasicRibVerticalStack(
                marrow,
                bones.map { it.createBone(context) }
            )
            is BoneConstructionType.HorizontalBoneStack -> BasicRibHorizontalStack(
                marrow,
                bones.map { it.createBone(context) }
            )
        }
    }
}