package co.za.kaylentravispillay.skelly.builder.stack

import androidx.annotation.ColorRes
import co.za.kaylentravispillay.skelly.bone.marrow.BoneMarrow
import co.za.kaylentravispillay.skelly.builder.bone.BoneBuilder
import co.za.kaylentravispillay.skelly.builder.annotation.SkellyDsl
import co.za.kaylentravispillay.skelly.builder.type.BoneConstructionType

@SkellyDsl
class VerticalBoneStackBuilder {
    private var width: Int = -1
    private var height: Int = -1
    private var marginStart: Int = 0
    private var marginTop: Int = 0

    @ColorRes private var backgroundColorRes: Int = -1

    private val mBoneCollection: MutableList<BoneConstructionType> = mutableListOf()

    fun bone(lambda: BoneBuilder.() -> Unit = {}) {
        val bone = BoneBuilder().apply {
            if (this@VerticalBoneStackBuilder.backgroundColorRes != -1) {
                backgroundColorRes { this@VerticalBoneStackBuilder.backgroundColorRes }
            }
        }.apply(lambda).build()
        mBoneCollection.add(bone)
    }

    fun horizontalBoneStack(lambda: HorizontalBoneStackBuilder.() -> Unit) {
        val stack = HorizontalBoneStackBuilder().apply {
            if (this@VerticalBoneStackBuilder.backgroundColorRes != -1) {
                backgroundColorRes { this@VerticalBoneStackBuilder.backgroundColorRes }
            }
        }.apply(lambda).build()
        mBoneCollection.add(stack)
    }

    fun width(lambda: () -> Int) { this.width = lambda() }
    fun height(lambda: () -> Int) { this.height = lambda() }
    fun marginStart(lambda: () -> Int) { this.marginStart = lambda() }
    fun marginTop(lambda: () -> Int) { this.marginTop = lambda() }
    fun backgroundColorRes(lambda: () -> Int) { this.backgroundColorRes = lambda() }

    fun build() = BoneConstructionType.VerticalBoneStack(
        marrow = BoneMarrow(
            width = width,
            height = height,
            marginStart = marginStart,
            marginTop = marginTop
        ),
        bones = mBoneCollection
    )
}