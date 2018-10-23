package com.eudycontreras.materialprofiletemplatelibrary1

import android.widget.Toast
import com.eudycontreras.materialprofiletemplatelibrary1.interfaces.ProfileInterface

/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
class ProfileController(private val activity: ProfileActivity){


    private var profileInterface : ProfileInterface? = null

    public fun setProfileInterface(profileInterface: ProfileInterface?){
        this.profileInterface = profileInterface
    }

    fun handleEndorseUser() {
        Toast.makeText(activity, "Endorse user action", Toast.LENGTH_SHORT).show()
    }

    fun handleReportUser() {
        Toast.makeText(activity, "Report user action", Toast.LENGTH_SHORT).show()
    }

    fun handleReviewUser() {
        Toast.makeText(activity, "Review user action", Toast.LENGTH_SHORT).show()
    }

    fun handleSubscribeToUser() {
        Toast.makeText(activity, "Subscribe to user action", Toast.LENGTH_SHORT).show()
    }

    fun handleOpenChat() {
        Toast.makeText(activity, "Message user action", Toast.LENGTH_SHORT).show()
    }

    fun handleMoreAction() {
        Toast.makeText(activity, "Context action", Toast.LENGTH_SHORT).show()
    }

    fun handleSeeRankAction() {
        Toast.makeText(activity, "See ranks action", Toast.LENGTH_SHORT).show()
    }

    fun handleSeeFollowers() {
      /*  activity.getAppBarLayout().setExpanded(false, true)
        activity.getViewPager().currentItem = ProfileActivity.FOLLOWERS_TAB*/
    }

    fun handleSeeFollowing() {
        /*activity.getAppBarLayout().setExpanded(false, true)
        activity.getViewPager().currentItem = ProfileActivity.FOLLOWING_TAB*/
    }
}