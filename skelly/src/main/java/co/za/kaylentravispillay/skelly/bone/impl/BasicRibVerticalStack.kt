package co.za.kaylentravispillay.skelly.bone.impl

import android.graphics.*
import androidx.core.graphics.toRectF
import co.za.kaylentravispillay.skelly.bone.Bone
import co.za.kaylentravispillay.skelly.bone.marrow.BoneMarrow

internal class BasicRibVerticalStack(
    private val marrow: BoneMarrow,
    private val bones: List<Bone>
) : Bone {

    private val mBasicBoneStackChildMeasurements: MutableMap<Int, RectF> = mutableMapOf()
    private val mBasicBoneStackChildDisplacement: PointF = PointF()

    override fun measureBone(width: Int, height: Int): Rect {
        val stackWidth = marrow.getWidth(width)
        val stackHeight = marrow.getHeight(height)

        var accumulativeParentStackHeight = stackHeight
        var accumulativeBoneStackHeight = 0

        bones.forEachIndexed { index, bone ->
            val measureResult = bone.measureBone(
                stackWidth,
                accumulativeParentStackHeight
            )

            mBasicBoneStackChildMeasurements[index] = measureResult.toRectF()

            val calculatedBoneHeight = (measureResult.bottom + measureResult.top)
            accumulativeParentStackHeight -= calculatedBoneHeight
            accumulativeBoneStackHeight += calculatedBoneHeight
        }

        if (accumulativeBoneStackHeight > height) {
            accumulativeBoneStackHeight = height
        }

        return Rect(
            marrow.marginStart,
            marrow.marginTop,
            stackWidth,
            accumulativeBoneStackHeight
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
                0F,
                rect.top + rect.bottom
            )
        }
    }
}