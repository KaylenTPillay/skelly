package co.za.kaylentravispillay.skelly

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import co.za.kaylentravispillay.skelly.delegate.SkellyDelegate
import co.za.kaylentravispillay.skelly.delegate.impl.SkellyDelegateImpl
import com.facebook.shimmer.ShimmerFrameLayout

class SkellyLayout : ShimmerFrameLayout {

    private val delegate: SkellyDelegate = SkellyDelegateImpl()

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        delegate.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        delegate.onDraw(canvas)
    }

    fun enableShimmer(enable: Boolean) {
        if (enable) {
            startShimmer()
        } else {
            stopShimmer()
        }
    }
}