package com.eudycontreras.materialprofiletemplatelibrary1.utilities

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

    private var scrollingUp : Boolean = false

    private var startingPointY : Float = 0f
    private var lastPointY : Float = 0f
    private var dragY: Float = 0f

    var height: Int = 0


    fun handleStretch(scrollOffsetY: Int, event: MotionEvent?, stretchEvaluator: StretchEvaluator, released: OnStretchReleased): Boolean {
        val action: Int = event!!.action

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                startingPointY = if(scrollOffsetY >= 0){
                    event.y
                }else{
                    Float.MIN_VALUE
                }
                dragY = 0f
                return (dragY > 0)
            }
            MotionEvent.ACTION_MOVE -> {

                val y = event.y

               if(scrollOffsetY < 0){
                   startingPointY = y
                   dragY = 0f
               }

                dragY += (y - startingPointY)

                startingPointY = y

                val rawScale =  map(dragY, 0f, height.toFloat(), 1f, 2.5f)

                val tensionRangeY = map(dragY, 0f, height.toFloat(), 1f, 5f)

                val translationY = clamp((dragY / tensionRangeY),0f,height.toFloat())

                if(scrollOffsetY >= 0){

                    stretchEvaluator(translationY, rawScale)
                }

                scrollingUp = lastPointY > startingPointY

                lastPointY = y

                return if( dragY <= 0f){
                    false
                } else
                    !(scrollingUp && dragY <= 0f)
            }

            else -> {
                dragY = 0f

                startingPointY = event.y
                lastPointY = event.y

                released()

                return false
            }
        }
    }
}