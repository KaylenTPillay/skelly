package co.za.kaylentravispillay.skelly.builder.bone

import co.za.kaylentravispillay.skelly.bone.marrow.BoneMarrow
import co.za.kaylentravispillay.skelly.builder.annotation.SkellyDsl
import co.za.kaylentravispillay.skelly.builder.type.BoneConstructionType

@SkellyDsl
class BoneBuilder {
    private var width: Int = -1
    private var height: Int = -1
    private var marginStart: Int = 0
    private var marginTop: Int = 0

    fun width(lambda: () -> Int) { this.width = lambda() }
    fun height(lambda: () -> Int) { this.height = lambda() }
    fun marginStart(lambda: () -> Int) { this.marginStart = lambda() }
    fun marginTop(lambda: () -> Int) { this.marginTop = lambda() }

    fun build() = BoneConstructionType.Bone(
        marrow = BoneMarrow(
            width = width,
            height = height,
            marginStart = marginStart,
            marginTop = marginTop
        )
    )
}