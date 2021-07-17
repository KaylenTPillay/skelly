package co.za.kaylentravispillay.skelly.bone.marrow

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import co.za.kaylentravispillay.skelly.R

data class BoneMarrow(
    private val width: Int = -1,
    private val height: Int = -1,
    val marginStart: Int = 0,
    val marginTop: Int = 0,

    @DimenRes val cornerRadius: Int = R.dimen.dimen_4,
    @ColorRes val backgroundColor: Int = R.color.default_bone_background
) {
    fun getWidth(parentWidth: Int): Int {
        return when {
            width <= 0 -> parentWidth - marginStart
            (width + marginStart) > parentWidth -> parentWidth - marginStart
            else -> width
        }
    }

    fun getHeight(parentHeight: Int): Int {
        return when {
            height <= 0 -> parentHeight - marginTop
            (height + marginTop) > parentHeight -> parentHeight - marginTop
            else -> height
        }
    }
}
