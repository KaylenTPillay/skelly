package co.za.kaylentravispillay.skelly.bone.impl

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import androidx.core.graphics.toRectF
import co.za.kaylentravispillay.skelly.bone.Bone
import co.za.kaylentravispillay.skelly.bone.marrow.BoneMarrow

internal class BasicBone(
    context: Context,
    private val marrow: BoneMarrow
) : Bone {
    private val mBasicBoneDefaultBackgroundColor = ContextCompat.getColor(
        context,
        marrow.backgroundColor
    )

    private val mBasicBonePaint = Paint().apply {
        color = mBasicBoneDefaultBackgroundColor
    }

    private val mBasicBoneCornerRadiusRect = context.resources.getDimension(marrow.cornerRadius)

    private val mBasicBoneSizeRect = Rect()

    override fun measureBone(width: Int, height: Int): Rect {
        val boneWidth = marrow.getWidth(width)
        val boneHeight = marrow.getHeight(height)

        mBasicBoneSizeRect.right = boneWidth
        mBasicBoneSizeRect.bottom = boneHeight

        return Rect(
            marrow.marginStart,
            marrow.marginTop,
            boneWidth,
            boneHeight
        )
    }

    override fun layoutBone(
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        mBasicBoneSizeRect.left = left
        mBasicBoneSizeRect.top = top
    }

    override fun growBone(canvas: Canvas?) {
        canvas?.save()
        canvas?.translate(
            mBasicBoneSizeRect.left.toFloat() + marrow.marginStart,
            mBasicBoneSizeRect.top.toFloat() + marrow.marginTop
        )
        canvas?.drawRoundRect(
            mBasicBoneSizeRect.toRectF(),
            mBasicBoneCornerRadiusRect,
            mBasicBoneCornerRadiusRect,
            mBasicBonePaint
        )
        canvas?.restore()
    }
}