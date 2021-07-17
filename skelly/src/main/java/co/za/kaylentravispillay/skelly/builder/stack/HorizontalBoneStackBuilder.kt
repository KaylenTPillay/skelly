package co.za.kaylentravispillay.skelly.builder.stack

import co.za.kaylentravispillay.skelly.bone.marrow.BoneMarrow
import co.za.kaylentravispillay.skelly.builder.bone.BoneBuilder
import co.za.kaylentravispillay.skelly.builder.annotation.SkellyDsl
import co.za.kaylentravispillay.skelly.builder.type.BoneConstructionType

@SkellyDsl
class HorizontalBoneStackBuilder {

    private var width: Int = -1
    private var height: Int = -1
    private var marginStart: Int = 0
    private var marginTop: Int = 0

    private val mBoneCollection: MutableList<BoneConstructionType> = mutableListOf()

    fun bone(lambda: BoneBuilder.() -> Unit = {}) {
        mBoneCollection.add(BoneBuilder().apply(lambda).build())
    }

    fun verticalBoneStack(lambda: VerticalBoneStackBuilder.() -> Unit) {
        mBoneCollection.add(VerticalBoneStackBuilder().apply(lambda).build())
    }

    fun width(lambda: () -> Int) { this.width = lambda() }
    fun height(lambda: () -> Int) { this.height = lambda() }
    fun marginStart(lambda: () -> Int) { this.marginStart = lambda() }
    fun marginTop(lambda: () -> Int) { this.marginTop = lambda() }

    fun build() = BoneConstructionType.HorizontalBoneStack(
        marrow = BoneMarrow(
            width = width,
            height = height,
            marginStart = marginStart,
            marginTop = marginTop
        ),
        bones = mBoneCollection
    )
}