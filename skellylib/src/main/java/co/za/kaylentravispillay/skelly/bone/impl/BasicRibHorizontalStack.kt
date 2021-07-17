package co.za.kaylentravispillay.skelly.bone.impl

import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import androidx.core.graphics.toRectF
import co.za.kaylentravispillay.skelly.bone.Bone
import co.za.kaylentravispillay.skelly.bone.marrow.BoneMarrow

internal class BasicRibHorizontalStack(
    private val marrow: BoneMarrow,
    private val bones: List<Bone>
) : Bone {

    private val mBasicBoneStackChildMeasurements: MutableMap<Int, RectF> = mutableMapOf()
    private val mBasicBoneStackChildDisplacement: PointF = PointF()

    override fun measureBone(width: Int, height: Int): Rect {
        val stackWidth = marrow.getWidth(width)
        val stackHeight = marrow.getHeight(height)

        var accumulativeParentStackWidth = stackWidth
        var accumulativeBoneChildWidth = 0
        var maxChildBoneHeight = 0

        bones.forEachIndexed { index, bone ->
            val measureResult = bone.measureBone(
                accumulativeParentStackWidth,
                stackHeight
            )

            mBasicBoneStackChildMeasurements[index] = measureResult.toRectF()

            val calculatedBoneWidth = (measureResult.left + measureResult.right)
            val calculatedBoneHeight = (measureResult.top + measureResult.bottom)

            accumulativeParentStackWidth -= calculatedBoneWidth
            accumulativeBoneChildWidth += calculatedBoneWidth
            maxChildBoneHeight = maxOf(maxChildBoneHeight, calculatedBoneHeight)
        }

        if (accumulativeBoneChildWidth > width) {
            accumulativeBoneChildWidth = width
        }

        return Rect(
            marrow.marginStart,
            marrow.marginTop,
            accumulativeBoneChildWidth,
            maxChildBoneHeight
        )
    }

    override fun layoutBone(left: Int, top: Int, right: Int, bottom: Int) {
        bones.forEach { bone ->
            bone.layoutBone(
                left,
                top,
                right,
                bottom
            )
        }
    }

    override fun growBone(canvas: Canvas?) {
        canvas?.save()
        canvas?.translate(
            marrow.marginStart.toFloat(),
            marrow.marginTop.toFloat()
        )
        bones.forEachIndexed { index, bone ->
            bone.growBone(canvas)

            setChildDisplacement(mBasicBoneStackChildMeasurements[index])
            canvas?.translate(mBasicBoneStackChildDisplacement.x, mBasicBoneStackChildDisplacement.y)
        }
        canvas?.restore()
    }

    private fun setChildDisplacement(rect: RectF?) {
        if (rect == null) {
            mBasicBoneStackChildDisplacement.set(0F, 0F)
        } else {
            mBasicBoneStackChildDisplacement.set(
                rect.left + rect.right,
                0F
            )
        }
    }
}