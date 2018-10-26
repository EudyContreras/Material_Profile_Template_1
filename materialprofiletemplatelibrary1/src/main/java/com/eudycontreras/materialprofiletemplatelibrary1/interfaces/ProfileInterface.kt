package com.eudycontreras.materialprofiletemplatelibrary1.interfaces

import com.eudycontreras.materialprofiletemplatelibrary1.models.UserMessage
import com.eudycontreras.materialprofiletemplatelibrary1.models.UserReport
import com.eudycontreras.materialprofiletemplatelibrary1.models.UserReview

/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
interface ProfileInterface {
    fun onSubscribe()
    fun onUnSubscribe()
    fun onEndorsed()
    fun onUnEndorsed()
    fun onReviewWritten(review: UserReview)
    fun onReportSubmitted(report: UserReport)
    fun onMessageSent(message: UserMessage)
}