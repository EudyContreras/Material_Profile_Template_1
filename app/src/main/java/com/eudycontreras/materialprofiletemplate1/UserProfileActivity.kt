package com.eudycontreras.materialprofiletemplate1

import android.os.Bundle
import com.eudycontreras.materialprofiletemplatelibrary1.ProfileActivity

class UserProfileActivity : ProfileActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragments()
    }

    private fun initFragments() {
        val tabs = arrayOf("About", "Resume", "Posts", "Reviews", "Achievements", "Followers", "Following", "Favorites", "Stats")

        for (tab in tabs) {
            addProfileTab(UserProfileFragment.newInstance(tab))
        }
    }

}
