package com.eudycontreras.materialprofiletemplatelibrary1

import android.support.design.widget.Snackbar
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
        Snackbar.make(activity.getFab(),"Endorse user action", Snackbar.LENGTH_SHORT).show()
    }

    fun handleReportUser() {
        Snackbar.make(activity.getFab(),"Report user action",Snackbar.LENGTH_SHORT).show()
    }

    fun handleReviewUser() {
        Snackbar.make(activity.getFab(),"Review user action",Snackbar.LENGTH_SHORT).show()
    }

    fun handleSubscribeToUser() {
        Snackbar.make(activity.getFab(),"Subscribe user action",Snackbar.LENGTH_SHORT).show()
    }

    fun handleOpenChat() {
        Snackbar.make(activity.getFab(),"Message user action",Snackbar.LENGTH_SHORT).show()
    }

    fun handleMoreAction() {
        Snackbar.make(activity.getFab(),"Context action",Snackbar.LENGTH_SHORT).show()
    }

    fun handleSeeRankAction() {
        Snackbar.make(activity.getFab(),"See rank action",Snackbar.LENGTH_SHORT).show()
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