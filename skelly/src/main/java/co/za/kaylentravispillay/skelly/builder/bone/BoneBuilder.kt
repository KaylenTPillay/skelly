package co.za.kaylentravispillay.skelly.builder.bone

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import co.za.kaylentravispillay.skelly.R
import co.za.kaylentravispillay.skelly.bone.marrow.BoneMarrow
import co.za.kaylentravispillay.skelly.builder.annotation.SkellyDsl
import co.za.kaylentravispillay.skelly.builder.type.BoneConstructionType

@SkellyDsl
class BoneBuilder {
    private var width: Int = -1
    private var height: Int = -1
    private var marginStart: Int = 0
    private var marginTop: Int = 0

    @ColorRes private var backgroundColorRes: Int = R.color.default_bone_background
    @DimenRes private var cornerRadius: Int = R.dimen.dimen_4

    fun width(lambda: () -> Int) { this.width = lambda() }
    fun height(lambda: () -> Int) { this.height = lambda() }
    fun marginStart(lambda: () -> Int) { this.marginStart = lambda() }
    fun marginTop(lambda: () -> Int) { this.marginTop = lambda() }
    fun backgroundColorRes(lambda: () -> Int) { this.backgroundColorRes = lambda() }
    fun cornerRadius(lambda: () -> Int) { this.cornerRadius = lambda() }

    fun build() = BoneConstructionType.Bone(
        marrow = BoneMarrow(
            width = width,
            height = height,
            marginStart = marginStart,
            marginTop = marginTop,
            backgroundColor = backgroundColorRes,
            cornerRadius = cornerRadius
        )
    )
}