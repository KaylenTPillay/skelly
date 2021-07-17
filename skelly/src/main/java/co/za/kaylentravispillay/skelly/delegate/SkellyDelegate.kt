package co.za.kaylentravispillay.skelly.delegate

import android.graphics.Canvas

internal interface SkellyDelegate {
    fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int)

    fun onDraw(canvas: Canvas?)
}