package co.za.kaylentravispillay.skelly.bone.marrow

data class BoneMarrow(
    private val width: Int = -1,
    private val height: Int = -1,
    val marginStart: Int = 0,
    val marginTop: Int = 0
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
