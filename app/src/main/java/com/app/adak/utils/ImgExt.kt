package com.app.adak.utils

import android.app.Activity
import android.widget.ImageView
import com.app.adak.R
import com.app.adak.ui.customWidget.CircleImageView
import com.squareup.picasso.Picasso


fun Activity.loadImage(url: String?, imgView: ImageView) {
    Picasso.with(this).load(url).placeholder(R.drawable.ic_user_placeholder).into(imgView)

}

fun Activity.loadImageProPlaceHolder(url: String?, imgView: CircleImageView) {
    if (url.isNullOrEmpty()) {
        imgView.setImageResource(R.drawable.ic_user_placeholder)
    } else {
        Picasso.with(this).load(url).placeholder(R.drawable.temp).into(imgView)
    }


}
//
//fun privateKey(): String? {
//
//    return G.getData(G.privateKey)
//}
//
//fun Activity.loadImageWithCorner(url: String, imgView: ImageView, cornerRadius: Int, margin: Int) {
//    val roundedCornersTransformation = RoundedCornersTransformation(cornerRadius, margin)
//    Picasso.with(this).load(G.WebImageUrl + url).transform(roundedCornersTransformation).fit().centerCrop().into(imgView)
//}
//
//fun longValToTime(time: String): String {
//    return DateUtility.getPersianDate(Date(toStringTime(time).toLong()))
//}
//
//fun toStringTime(string: String): String {
//    return string.replace("/Date(", "").replace(")/", "")
//}
//
//fun toStringSqlTime(timeLong: Long): String {
//    return "/Date($timeLong)/"
//}
//
//
//
