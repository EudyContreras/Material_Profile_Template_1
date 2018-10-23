package com.eudycontreras.materialprofiletemplatelibrary1

import android.support.v4.app.Fragment


/**
 * Unlicensed private property of the author and creator.
 * Unauthorized use of this class/file outside of the Material Profile Template 1 project
 * may result on legal prosecution. Do not alter or remove this text.
 * Created by Eudy Contreras
 *
 * @author  Eudy Contreras
 * @version 1.0
 */
abstract class ProfileFragment : Fragment() {

    abstract fun setName(name : String)
    abstract fun getName() : String
}