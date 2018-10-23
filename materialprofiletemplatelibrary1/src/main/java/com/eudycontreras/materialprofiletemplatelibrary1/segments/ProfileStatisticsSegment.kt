package com.eudycontreras.materialprofiletemplatelibrary1.segments

import android.view.View
import android.widget.TextView
import com.eudycontreras.materialprofiletemplatelibrary1.ProfileActivity
import com.eudycontreras.materialprofiletemplatelibrary1.ProfileUtility
import com.eudycontreras.materialprofiletemplatelibrary1.R

/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
class ProfileStatisticsSegment(private val activity: ProfileActivity){

    private lateinit var followersLabel: TextView
    private lateinit var followingLabel: TextView

    private lateinit var followersCount: TextView
    private lateinit var followingCount: TextView

    fun initViews(parentView: View) {
        this.followersLabel = parentView.findViewById(R.id.user_segment_followers_label)
        this.followingLabel = parentView.findViewById(R.id.user_segment_following_label)

        this.followersCount = parentView.findViewById(R.id.user_segment_follower_count)
        this.followingCount = parentView.findViewById(R.id.user_segment_following_count)
    }

    fun registerListeners() {
        ProfileUtility.addIconTouchFeedback(followersLabel, followersLabel, 1f, followersLabel.translationZ, 1f, 1f, 0.90f, 0.95f, 150)
        this.followersLabel.setOnClickListener { activity.getProfileController().handleSeeFollowers() }

        ProfileUtility.addIconTouchFeedback(followingLabel, followingLabel, 1f, followingLabel.translationZ, 1f, 1f, 0.90f, 0.95f, 150)
        this.followingLabel.setOnClickListener { activity.getProfileController().handleSeeFollowing() }

        ProfileUtility.addIconTouchFeedback(followersCount, followersCount, 1f, followersCount.translationZ, 1f, 1f, 0.90f, 0.95f, 150)
        this.followersCount.setOnClickListener{ activity.getProfileController().handleSeeFollowers() }

        ProfileUtility.addIconTouchFeedback(followingCount, followingCount, 1f, followingCount.translationZ, 1f, 1f, 0.90f, 0.95f, 150)
        this.followingCount.setOnClickListener{ activity.getProfileController().handleSeeFollowing() }
    }
}