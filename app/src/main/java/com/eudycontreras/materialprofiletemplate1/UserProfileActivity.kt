package com.eudycontreras.materialprofiletemplate1

import android.os.Bundle
import com.eudycontreras.materialprofiletemplatelibrary1.ProfileActivity
import com.eudycontreras.materialprofiletemplatelibrary1.models.UserInformation

class UserProfileActivity : ProfileActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isAllowHeaderStretch = true
        showProfileInteractionArea(true)
        showProfileStatisticsArea(true)
        setProfileInformation(UserInformation(-1, userName = "John Doe", userBio = "The man the legend!"))
        initFragments()
    }

    private fun initFragments() {
        val tabs = arrayOf("About","Resume", "Posts", "Reviews", "Achievements", "Followers", "Following", "Favorites", "Stats")

        for (tab in tabs) {
            addProfileTab(UserProfileFragment.newInstance(tab))
        }
    }

}
