package com.app.adak.utils

import android.app.Activity
import android.widget.Toast

fun Activity.toast(mess:String){
    Toast.makeText(this, mess,Toast.LENGTH_SHORT).show()
}