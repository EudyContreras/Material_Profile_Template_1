package com.eudycontreras.materialprofiletemplatelibrary1

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import com.eudycontreras.materialbottomnavigationtemplatelibrary.models.Dimension
import com.eudycontreras.materialprofiletemplatelibrary1.models.UserInformation
import com.eudycontreras.materialprofiletemplatelibrary1.segments.ProfileInteractionSegment
import com.eudycontreras.materialprofiletemplatelibrary1.segments.ProfileStatisticsSegment
import com.eudycontreras.materialprofiletemplatelibrary1.utilities.StretchHelper
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*

/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
abstract class ProfileActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener{

    companion object {
        private const val MIN_PERCENTAGE_TO_ANIMATE_AVATAR = 0f
        private const val MAX_PERCENTAGE_TO_ANIMATE_AVATAR = 60f
    }

    var isAvatarShown = true

    var isAllowHeaderStretch : Boolean  = true

    private var displayMetrics = DisplayMetrics()

    private val stretchHelper = StretchHelper()

    private lateinit var tabsAdapter: TabsAdapter<ProfileFragment>

    private lateinit var fragments: MutableList<ProfileFragment>

    private lateinit var controller: ProfileController
    private lateinit var userInformationSegment: ProfileStatisticsSegment
    private lateinit var userInteractionSegment: ProfileInteractionSegment

    private lateinit var userInformation : UserInformation

    protected var width: Int = 0
    protected var height: Int = 0

    private var scrollOffsetY : Int = 0
    private var maxScrollSize: Int = 0

    private var percentage: Float = 0f
    private var topAreaSize: Float = 0f
    private var bottomAreaSize: Float = 0f

    private var revealEnded = false

    internal var blockInput = true

    private var stretched = false

    internal val dimensions : Dimension by lazy {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        width = displayMetrics.widthPixels
        height = displayMetrics.heightPixels

        Dimension(width.toFloat(),height.toFloat())
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        windowManager.defaultDisplay.getMetrics(displayMetrics)

        height = displayMetrics.heightPixels
        width = displayMetrics.widthPixels

        initComponents()
        initSegments()
        setDefaultValues()
        registerListeners()
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        revealUserProfile(null)
    }

    override fun finish() {
        concealUserProfile {
            super.finish()
            overridePendingTransition(R.anim.nothing_animation, R.anim.nothing_animation)
        }
    }

    override fun onBackPressed() {
        if (revealEnded) {
            super.onBackPressed()
        }
    }

    private fun initComponents(){
        fragments = ArrayList()
        controller = ProfileController(this)

        userInformationSegment = ProfileStatisticsSegment(this)
        userInteractionSegment = ProfileInteractionSegment(this)

        tabsAdapter = TabsAdapter(supportFragmentManager)
        tabsAdapter.setFragments(fragments)
    }

    private fun initSegments(){
        userInformationSegment.initViews(findViewById(R.id.user_information))
        userInteractionSegment.initViews(findViewById(R.id.user_interact))
    }

    private fun setDefaultValues(){
        val translation = convertDpToPixel(this, 300f)

        stretchHelper.height = height

        backdrop_temp.translationY = -translation

        app_bar.apply {
            translationY = -translation
            alpha = 0f
            maxScrollSize = totalScrollRange
            addOnOffsetChangedListener(this@ProfileActivity)
        }

        pager.apply {
            translationY = translation
            alpha = 0f
            adapter = tabsAdapter
        }

        user_message.apply {
            scaleX = 0f
            scaleY = 0f
        }

        tab_layout.setupWithViewPager(pager)
    }

    private fun registerListeners() {
        ProfileUtility.addIconTouchFeedback(user_message, user_message, 1f, user_message.translationZ, 1f, 1f, 0.9f, 1f, 150)
        user_message.setOnClickListener { _ -> if(!blockInput)controller.handleOpenChat() }

        ProfileUtility.addIconTouchFeedback(user_rank, user_rank, 1f, user_rank.translationZ, user_rank.translationZ, 1f, 0.95f, 1f, 150)
        user_rank.setOnClickListener{ _ -> if(!blockInput)controller.handleSeeRankAction() }

        ProfileUtility.addIconTouchFeedback(more_action, more_action, 1f, 1f, 1f, 1f, 0.95f, 1f, 150)
        more_action.setOnClickListener{ _ -> if(!blockInput)controller.handleMoreAction() }

        userInformationSegment.registerListeners()
        userInteractionSegment.registerListeners()
    }


    private fun revealUserProfile(action: Action?){
        if (topAreaSize == 0f) {
            topAreaSize = app_bar.height.toFloat()
            bottomAreaSize = pager.height.toFloat()

            app_bar.apply {
                translationY = -topAreaSize * 0.7f
                alpha = 0f
            }

            pager.apply {
                translationY = bottomAreaSize * 0.7f
                alpha = 0f
            }
        }

        val duration = 350L

        backdrop_temp.animate()
                .translationY(0f)
                .setStartDelay(70)
                .setDuration(0)
                .start()

        app_bar.alpha = 0f

        var start = -convertDpToPixel(this, 300f)
        val friction = 12f
        val tension = 110f
        val distance = Math.abs(start)

        ProfileUtility.createSpringAnimation(start, 0f, duration, distance, friction, tension,
                { value ->
                    app_bar.animate()
                            .translationY(value)
                            .alpha(1f)
                            .setDuration(0)
                            .start()
                }, {
            app_bar.translationY = 0f
            backdrop_temp.visibility = View.INVISIBLE
            backdrop.visibility = View.VISIBLE
            revealEnded = true
            blockInput = false
            action?.invoke()
        })

        pager.alpha = 0f

        start = convertDpToPixel(this, 300f)
        ProfileUtility.createSpringAnimation(start, 0f, duration, distance, friction, tension,
                { value ->
                    pager.animate()
                            .translationY(value)
                            .alpha(1f)
                            .setDuration(0)
                            .start()

                }, null)

        user_message.animate()
                .scaleY(1f)
                .scaleX(1f)
                .setStartDelay(100)
                .setDuration(duration)
                .setInterpolator(OvershootInterpolator())
                .start()
    }

    private fun concealUserProfile(action: Action?) {
        blockInput = true
        if (percentage < 50) {
            backdrop_temp.visibility = View.VISIBLE
            backdrop.visibility = View.INVISIBLE
            backdrop_temp.animate()
                    .alpha(0f)
                    .setStartDelay(250)
                    .setDuration(0)
                    .start()
        }

        tab_layout.animate()
                .alpha(0f)
                .setDuration(120)
                .start()

        user_message.animate()
                .scaleY(0f)
                .scaleX(0f)
                .setDuration(250)
                .setInterpolator(AnticipateInterpolator())
                .start()

        app_bar.animate()
                .translationY(-topAreaSize)
                .setDuration(350)
                .setInterpolator(AnticipateInterpolator())
                .start()

        pager.animate()
                .translationY(bottomAreaSize)
                .setDuration(450)
                .setInterpolator(AnticipateInterpolator())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        action?.invoke()
                    }
                })
                .start()
    }

    fun showProfileInteractionArea(state: Boolean){
        userInteractionSegment.show(state)
    }

    fun showProfileStatisticsArea(state: Boolean){
        userInformationSegment.show(state)
    }

    fun addProfileTab(vararg fragment : ProfileFragment){
        fragments.addAll(fragment)
        tabsAdapter.notifyDataSetChanged()

        if (fragments.size <= 3) {
            tab_layout.tabMode = TabLayout.MODE_FIXED
            tab_layout.tabGravity = TabLayout.GRAVITY_FILL
        }else{
            tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
            tab_layout.tabGravity = TabLayout.GRAVITY_FILL
        }

        if (fragments.size <= 1) {
            tab_layout.visibility = View.GONE
        } else {
            tab_layout.visibility = View.VISIBLE
        }

    }

    fun getFab() : FloatingActionButton = user_message

    internal fun getProfileController() : ProfileController  = controller

    internal fun getViewPager() : ViewPager = pager

    internal fun getAppBarLayout() : AppBarLayout = app_bar

    fun setProfileInformation(userInformation : UserInformation){
        this.userInformation = userInformation

        userInformation.userImage?.let {
            (profile_image as ImageView).setImageBitmap(it)
        }

        userInformation.backdropImage?.let {
            (backdrop as ImageView).setImageBitmap(it)
            (backdrop_temp as ImageView).setImageBitmap(it)
        }

        user_name.text = userInformation.userName
        user_bio.text = userInformation.userBio
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if(!revealEnded || !isAllowHeaderStretch){
            return super.dispatchTouchEvent(event)
        }

        val stretch = stretchHelper.handleStretch(scrollOffsetY, event,
                { translationY, scale->

                    stretched = true

                    if(scale > 1.0f){
                        backdrop_temp.visibility = View.VISIBLE
                        backdrop.visibility = View.INVISIBLE
                    }else if(scale <= 1.0f){
                        backdrop_temp.visibility = View.INVISIBLE
                        backdrop.visibility = View.VISIBLE
                    }

                    app_bar.animate()
                            .translationY(translationY)
                            .setListener(null)
                            .setInterpolator(null)
                            .setDuration(0)
                            .start()

                    backdrop_temp.apply {
                        scaleX = scale
                        scaleY = scale
                    }

                }) {

                    val duration: Long = 150

                    app_bar.animate()
                            .translationY(0f)
                            .setDuration(duration*2)
                            .setInterpolator(OvershootInterpolator(1.2f))
                            .start()

                    backdrop_temp.animate()
                            .scaleY(1f)
                            .scaleX(1f)
                            .setDuration(duration - (duration/3))
                            .setInterpolator(DecelerateInterpolator())
                            .setListener(object : AnimatorListenerAdapter(){
                                override fun onAnimationEnd(animation: Animator?) {
                                    backdrop.visibility = View.VISIBLE
                                    backdrop_temp.visibility = View.INVISIBLE
                                    stretched = false
                                    super.onAnimationEnd(animation)
                                }
                            })
                            .start()
                }

        return if(!stretch){
            super.dispatchTouchEvent(event)
        }else if(scrollOffsetY < 0){
            super.dispatchTouchEvent(event)
        }else{
            true
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, offsetY: Int) {
        this.scrollOffsetY = offsetY

        if(offsetY != 0){
            if(backdrop_temp.visibility == View.VISIBLE){
                backdrop_temp.alpha = 0f
                backdrop.visibility = View.VISIBLE
            }
        }else{
            backdrop_temp.alpha = 1f
        }

        if (maxScrollSize == 0)
            maxScrollSize = appBarLayout.totalScrollRange

        val sum = (Math.abs(offsetY) * 100).toFloat()

        percentage = if (sum != 0f) sum / maxScrollSize else 0f

        val scale = map(percentage, MIN_PERCENTAGE_TO_ANIMATE_AVATAR, MAX_PERCENTAGE_TO_ANIMATE_AVATAR, 0f, 100f)

        val calc = 1f - scale * 0.01f

        if (calc in 0.0..1.0) {
            isAvatarShown = true
            profile_image_container.animate()
                    .scaleX(calc)
                    .scaleY(calc)
                    .alpha(calc)
                    .setDuration(0)
                    .start()

            if(!stretched){
                if (percentage == 100f) {
                    if (backdrop_temp.visibility == View.VISIBLE) {
                        backdrop_temp.visibility = View.INVISIBLE
                    }
                } else {
                    if (percentage > 0) {
                        if (backdrop_temp.visibility == View.VISIBLE) {
                            backdrop_temp.visibility = View.INVISIBLE
                        }
                        if (backdrop.visibility == View.INVISIBLE) {
                            backdrop.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private class TabsAdapter<T> internal constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) where T : ProfileFragment {

        private lateinit var fragments: List<T>

        fun setFragments(fragments: List<T>) {
            this.fragments = fragments
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(i: Int): Fragment {
            return fragments[i]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragments[position].name
        }
    }
}
