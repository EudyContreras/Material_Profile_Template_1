package com.eudycontreras.materialprofiletemplatelibrary1.utilities

import android.util.Log
import android.view.MotionEvent
import com.eudycontreras.materialprofiletemplatelibrary1.OnStretchReleased
import com.eudycontreras.materialprofiletemplatelibrary1.StretchEvaluator
import com.eudycontreras.materialprofiletemplatelibrary1.clamp
import com.eudycontreras.materialprofiletemplatelibrary1.map

/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
class StretchHelper{

    private var startingPointY : Float = 0f
    private var lastPointY : Float = 0f
    private var dragY: Float = 0f

    private var scrollingUp : Boolean = false

    var height: Int = 0


    fun handleStretch(scrollOffsetY: Int, event: MotionEvent?, stretchEvaluator: StretchEvaluator, released: OnStretchReleased): Boolean {
        val action: Int = event!!.action

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                startingPointY = event.y
                return (dragY > 0)
            }
            MotionEvent.ACTION_MOVE -> {

                val y = event.y

                dragY += (y - startingPointY)

                dragY = clamp(dragY, 0f, height.toFloat())

                startingPointY = y

                if(scrollOffsetY == 0){
                    val rawScale = if(scrollingUp){
                        map(dragY, 0f, height.toFloat(), 1f, 2.5f)
                    }else{
                        map(dragY, 0f, height.toFloat(), 1f, 2.5f)
                    }

                    val tensionRangeY = if(scrollingUp){
                        map(dragY, 0f, height.toFloat(), 1f, 5f)
                    }else{
                        map(dragY, 0f, height.toFloat(), 1f, 5f)
                    }

                    val tensionScale = map(dragY, 0f, height.toFloat(), 0f, 0.5f)

                    val translationY = (dragY / tensionRangeY)

                    val scale = (rawScale - tensionScale)

                    stretchEvaluator(translationY, scale)

                }
                scrollingUp = lastPointY > startingPointY

                lastPointY = y

                return if( dragY <= 0f){
                    Log.d("DRAG", "ZERO $y")
                    false
                } else if(scrollingUp && dragY <= 0f){
                    Log.d("DRAG", "ZERO $y")
                    false
                } else{
                    true
                }
            }

            else -> {
                dragY = 0f

                startingPointY = event.y

                released()

                return false
            }
        }
    }
}