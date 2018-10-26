package com.eudycontreras.materialprofiletemplatelibrary1

import android.view.MotionEvent
import android.view.View
import android.view.animation.OvershootInterpolator
import com.facebook.rebound.*


/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
internal class ProfileUtility {


    companion object {


        private val springSystem: SpringSystem = SpringSystem.create()

        fun createSpringAnimation(start: Float, end: Float, time: Long, distance: Float, friction: Float, tension: Float, springValue: SpringValue, onEnd: Action?) {
            val spring = springSystem.createSpring()

            spring.addListener(object : SimpleSpringListener() {

                override fun onSpringUpdate(spring: Spring?) {
                    val mappedValue = SpringUtil.mapValueFromRangeToRange(spring!!.currentValue, 0.0, 1.0, start.toDouble(), end.toDouble()).toFloat()

                    springValue(mappedValue)

                    if (spring.isAtRest) {
                        spring.destroy()

                        onEnd?.invoke()
                    }
                }
            })

            val velocity = 12f

            spring.apply {
                this.endValue = 1.0
                this.velocity = velocity.toDouble()
                this.springConfig = SpringConfig(tension.toDouble(), friction.toDouble())
            }
        }

        fun addIconTouchFeedback(touchView: View?, effectView: View?, defaultAlpha: Float?, defaultDepth: Float?, minDepth: Float, scaleStart: Float, scaleEnd: Float, minAlpha: Float, duration: Long) {


            if (effectView == null || touchView == null)
                return

            touchView.isClickable = true
            touchView.isFocusable = true
            touchView.setOnTouchListener { _, motionEvent ->
                if (!touchView.isEnabled)
                    return@setOnTouchListener true

                when (motionEvent.action) {
                    MotionEvent.ACTION_UP -> {
                        effectView
                                .animate()
                                .alpha(defaultAlpha!!)
                                .scaleX(scaleStart)
                                .scaleY(scaleStart)
                                .translationZ(defaultDepth!!)
                                .setListener(null)
                                .setInterpolator(OvershootInterpolator(2f))
                                .setDuration(150)
                                .start()
                        touchView.performClick()
                        true
                    }
                    MotionEvent.ACTION_OUTSIDE ,MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_MOVE-> {
                        effectView
                                .animate()
                                .alpha(defaultAlpha!!)
                                .scaleX(scaleStart)
                                .scaleY(scaleStart)
                                .translationZ(defaultDepth!!)
                                .setListener(null)
                                .setInterpolator(OvershootInterpolator(2f))
                                .setDuration(150)
                                .start()
                        true
                    }
                    MotionEvent.ACTION_DOWN -> {
                        effectView.animate()
                                .alpha(if (minAlpha == -1f) effectView.alpha else minAlpha)
                                .scaleX(scaleEnd)
                                .scaleY(scaleEnd)
                                .translationZ(if (minDepth == defaultDepth) minDepth else if (minDepth == -1f) defaultDepth!! else if (minDepth != defaultDepth) minDepth else 1F)
                                .setListener(null)
                                .setInterpolator(null)
                                .setDuration(duration)
                                .start()

                        true
                    }
                    else -> false
                }
            }
        }
    }
}