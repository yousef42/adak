package com.app.adak.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.app.adak.R
import com.app.adak.data.network.model.Users
import com.app.adak.utils.loadImageProPlaceHolder
import kotlinx.android.synthetic.main.item_users.view.*


class UserAdapter(
    internal var activity: HomeActivity,
    textViewResourceId: Int,
    var modelArrayList: ArrayList<Users.Data>
) : ArrayAdapter<Users.Data>(activity, textViewResourceId, modelArrayList) {



    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        v = inflater.inflate(R.layout.item_users, null)
        val model = modelArrayList[position]

        v!!.txtName.text=model.firstName +" "+model.lastName
        v.txtEmail.text=model.email
        activity.loadImageProPlaceHolder(model.avatar,v.imgAvatar)
        v.btnEdit.setOnClickListener {

            activity.addNewUser(position)
        }
        v.btnDelete.setOnClickListener {

            activity.deleteUser(model.id)
        }
        return v

    }


}
