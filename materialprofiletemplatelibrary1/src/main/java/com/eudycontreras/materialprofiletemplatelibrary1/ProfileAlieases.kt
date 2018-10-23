package com.eudycontreras.materialprofiletemplatelibrary1

import android.content.Context
import android.util.DisplayMetrics

/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
typealias SpringValue = (Float) -> Unit

typealias StretchEvaluator = (Float,Float) -> Unit

typealias OnStretchReleased =()-> Unit

typealias Action = ()-> Unit

fun clamp(value: Float, min: Float, max: Float) : Float =  if (value < min) min else if (value > max) max else value

fun map(
        value: Float,
        fromLow: Float,
        fromHigh: Float,
        toLow: Float,
        toHigh: Float): Float {
    val fromRangeSize = fromHigh - fromLow
    val toRangeSize = toHigh - toLow
    val valueScale = (value - fromLow) / fromRangeSize
    return toLow + valueScale * toRangeSize
}

fun convertDpToPixel(context: Context, dp: Float): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}


fun convertPixelsToDp(context: Context, px: Float): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}
