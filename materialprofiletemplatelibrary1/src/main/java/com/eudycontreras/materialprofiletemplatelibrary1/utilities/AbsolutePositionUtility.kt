package com.eudycontreras.materialprofiletemplatelibrary1.utilities

import android.view.View
import android.widget.RelativeLayout
import com.eudycontreras.materialbottomnavigationtemplatelibrary.models.Dimension
import com.eudycontreras.materialbottomnavigationtemplatelibrary.models.Margins
import java.util.*

/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Bottom Navigation Template Library project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
internal class AbsolutePositionUtility {
    internal enum class Position constructor(var x: Int = 0, var y: Int = 0) {

        BOTTOM,
        TOP,
        LEFT,
        RIGHT,
        CENTER,
        CENTER_HORIZONTAL,
        CENTER_VERTICAL,
        ABSOLUTE;


        companion object {

            fun ABSOLUTE(x: Int, y: Int): Position {
                val position = Position.ABSOLUTE
                position.x = x
                position.y = y
                return position
            }
        }
    }

    internal companion object {
        fun getDistanceToCenter(dimension: Dimension, view: View?): FloatArray {
            val location = IntArray(2)
            val translation = FloatArray(2)

            view!!.let {
                it.getLocationOnScreen(location)

                translation[0] = ((dimension.width - (dimension.width / 2) - (it.width / 2))) - location[0]
                translation[1] = ((dimension.height - (dimension.height / 2) - (it.height / 2))) - location[1]
            }
            return translation
        }

        internal fun setPosition(dimension: Dimension, view: View, position: EnumSet<Position>) {
            setPosition(dimension, view, null, position)
        }

        internal fun setPosition(dimension: Dimension, view: View, margins: Margins?, position: EnumSet<Position>) {

            val width = view.width
            val height = view.height

            val layoutParams = view.layoutParams as RelativeLayout.LayoutParams

            if (margins == null) {
                view.layoutParams = layoutParams
            } else {
                layoutParams.topMargin = margins.top
                layoutParams.leftMargin = margins.left
                layoutParams.bottomMargin = margins.bottom
                layoutParams.rightMargin = margins.right
                view.layoutParams = layoutParams
            }

            if (position.contains(Position.ABSOLUTE)) {
                val pos = position.iterator().next()
                layoutParams.topMargin = pos.y
                layoutParams.leftMargin = pos.x
                view.layoutParams = layoutParams
                view.requestLayout()
            } else if (position.contains(Position.BOTTOM)) {
                layoutParams.topMargin = (dimension.height - height - (layoutParams.topMargin + layoutParams.bottomMargin)).toInt()
                view.layoutParams = layoutParams
                view.requestLayout()
            } else if (position.contains(Position.TOP)) {
                view.layoutParams = layoutParams
                view.requestLayout()
            } else if (position.contains(Position.CENTER)) {
                layoutParams.topMargin = (dimension.height / 2 - height / 2).toInt()
                layoutParams.marginStart = (dimension.width / 2 - width / 2).toInt()
                view.layoutParams = layoutParams
                view.requestLayout()
            } else if (position.contains(Position.CENTER_HORIZONTAL)) {
                layoutParams.marginStart = (dimension.width / 2 - width / 2 + layoutParams.marginStart / 2).toInt()
                view.layoutParams = layoutParams
                view.requestLayout()
            } else if (position.contains(Position.CENTER_VERTICAL)) {
                layoutParams.topMargin = (dimension.height / 2 - height / 2).toInt()
                view.layoutParams = layoutParams
                view.requestLayout()
            } else if (position.contains(Position.CENTER_HORIZONTAL) && position.contains(Position.TOP)) {
                layoutParams.marginStart = (dimension.width / 2 - width / 2 + layoutParams.marginStart / 2).toInt()
                view.layoutParams = layoutParams
                view.requestLayout()
            } else if (position.contains(Position.CENTER_HORIZONTAL) && position.contains(Position.BOTTOM)) {
                layoutParams.topMargin = (dimension.height - height - (layoutParams.topMargin + layoutParams.bottomMargin)).toInt()
                layoutParams.marginStart = (dimension.width / 2 - width / 2 + layoutParams.marginStart / 2).toInt()
                view.layoutParams = layoutParams
                view.requestLayout()
            }
        }
    }
}