package com.eudycontreras.materialprofiletemplatelibrary1.segments

import android.view.View
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
internal class ProfileInteractionSegment(private val activity: ProfileActivity){

    private lateinit var reportUserContainer: View
    private lateinit var endorseUserContainer: View
    private lateinit var reviewUserContainer: View
    private lateinit var subscribeToUserContainer: View

    private lateinit var reportUserIcon: View
    private lateinit var endorseUserIcon: View
    private lateinit var reviewUserIcon: View
    private lateinit var subscribeToUserIcon: View

    private lateinit var parentView: View

    fun initViews(parentView: View) {
        this.parentView = parentView
        this.reportUserContainer = parentView.findViewById(R.id.report)
        this.endorseUserContainer = parentView.findViewById(R.id.endorse)
        this.reviewUserContainer = parentView.findViewById(R.id.write_review)
        this.subscribeToUserContainer = parentView.findViewById(R.id.subscribe)

        this.reportUserIcon = parentView.findViewById(R.id.report_icon)
        this.endorseUserIcon = parentView.findViewById(R.id.endorse_icon)
        this.reviewUserIcon = parentView.findViewById(R.id.write_review_icon)
        this.subscribeToUserIcon = parentView.findViewById(R.id.subscribe_icon)
    }

    fun registerListeners() {
        ProfileUtility.addIconTouchFeedback(reportUserContainer, reportUserContainer, 1f, reportUserContainer.translationZ, 1f, 1f, 0.90f, 0.95f, 150)
        this.reportUserContainer.setOnClickListener { if(!activity.blockInput)activity.getProfileController().handleReportUser() }

        ProfileUtility.addIconTouchFeedback(endorseUserContainer, endorseUserContainer, 1f, endorseUserContainer.translationZ, 1f, 1f, 0.90f, 0.95f, 150)
        this.endorseUserContainer.setOnClickListener { if(!activity.blockInput)activity.getProfileController().handleEndorseUser() }

        ProfileUtility.addIconTouchFeedback(reviewUserContainer, reviewUserContainer, 1f, reviewUserContainer.translationZ, 1f, 1f, 0.90f, 0.95f, 150)
        this.reviewUserContainer.setOnClickListener{ if(!activity.blockInput)activity.getProfileController().handleReviewUser() }

        ProfileUtility.addIconTouchFeedback(subscribeToUserContainer, subscribeToUserContainer, 1f, subscribeToUserContainer.translationZ, 1f, 1f, 0.90f, 0.95f, 150)
        this.subscribeToUserContainer.setOnClickListener{ if(!activity.blockInput)activity.getProfileController().handleSubscribeToUser() }
    }

    fun show(state: Boolean) {
        parentView.visibility = if(state) View.VISIBLE else View.GONE
    }
}