package com.eudycontreras.materialprofiletemplatelibrary1.curstomViews

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
internal class SquareFrameLayout : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context,  attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (measuredWidth == 0 || measuredWidth == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(measuredHeight, measuredHeight) //Snap to height
        } else {
            setMeasuredDimension(measuredWidth, measuredWidth) //Snap to width
        }
    }
}