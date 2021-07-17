package co.za.kaylentravispillay.skelly.delegate.impl

import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.RectF
import androidx.core.graphics.toRectF
import co.za.kaylentravispillay.skelly.bone.Bone
import co.za.kaylentravispillay.skelly.delegate.SkellyDelegate

internal class SkellyDelegateImpl : SkellyDelegate {

    private val mBones: MutableList<Bone> = mutableListOf()

    private val mBoneMeasurementCollection: MutableMap<Int, RectF> = mutableMapOf()
    private val mBoneDisplacementPoint: PointF = PointF()

    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        val width = right - left
        val height = bottom - top

        var currentAvailableParentHeight = height

        mBones.forEachIndexed { index, bone ->
            val measureResult = bone.measureBone(
                width,
                currentAvailableParentHeight
            )
            bone.layoutBone(
                0,
                0,
                measureResult.right,
                measureResult.bottom
            )

            currentAvailableParentHeight -= (measureResult.bottom + measureResult.top)
            mBoneMeasurementCollection[index] = measureResult.toRectF()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.save()
        mBones.forEachIndexed { index, bone ->
            bone.growBone(canvas)

            setChildDisplacement(mBoneMeasurementCollection[index])
            canvas?.translate(mBoneDisplacementPoint.x, mBoneDisplacementPoint.y)
        }
        canvas?.restore()
    }

    private fun setChildDisplacement(rect: RectF?) {
        if (rect == null) {
            mBoneDisplacementPoint.set(0F, 0F)
        } else {
            mBoneDisplacementPoint.set(
                0F,
                rect.top + rect.bottom
            )
        }
    }
}