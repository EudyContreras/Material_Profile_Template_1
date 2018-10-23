package com.eudycontreras.materialprofiletemplatelibrary1.models

import android.graphics.Bitmap

/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
data class UserInformation(
        val userId : Int,
        val userName: String,
        val userBio: String? = null,
        val followerCount : Int = 0,
        val followingCount : Int = 0,
        val userImage: Bitmap? = null,
        val backdropImage: Bitmap? = null)