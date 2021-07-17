package co.za.kaylentravispillay.skelly.bone

import android.graphics.Canvas
import android.graphics.Rect

internal interface Bone {
    fun measureBone(width: Int, height: Int): Rect

    fun layoutBone(left: Int, top: Int, right: Int, bottom: Int)

    fun growBone(canvas: Canvas?)
}