package com.eudycontreras.materialprofiletemplate1


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eudycontreras.materialprofiletemplatelibrary1.ProfileFragment


/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UserProfileFragment : ProfileFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_user_profile, container, false) as RecyclerView
        view.adapter = FakePageAdapter(20)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    companion object {
        @JvmStatic
        fun newInstance(name : String) : ProfileFragment{
            val fragment = UserProfileFragment()
            fragment.name = name
            return fragment
        }
    }

    private class FakePageAdapter internal constructor(private val numItems: Int) : RecyclerView.Adapter<FakePageVH>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FakePageVH {
            val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_card, viewGroup, false)
            return FakePageVH(itemView)
        }

        override fun onBindViewHolder(fakePageVH: FakePageVH, i: Int) {

        }

        override fun getItemCount(): Int {
            return numItems
        }
    }

    private class FakePageVH internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
}
