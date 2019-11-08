package com.app.adak.utils

import android.app.Activity
import android.util.Log
import com.app.adak.BuildConfig


fun Activity.logdFailure(message: String) {
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName + "debug: ", "onFailure $message")

}

fun Activity.logdSuccess(message: String) {
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName + "debug: ", ".onSuccess $message")
}


fun Activity.logd(message: String) {
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName + "debug: ", message)
}
//
//fun String.logd(message: String) {
//    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName + "debug: ", message)
//}
//
//fun Activity.loadImage(url: String?, imgView: ImageView) {
//    Picasso.with(this).load(G.WebImageUrl + url).placeholder(R.drawable.white_back).into(imgView)
//
//}
//
//fun Activity.loadImageProPlaceHolder(url: String?, imgView: ImageView) {
//    Picasso.with(this).load(G.WebImageUrl + url).placeholder(R.drawable.ic_user_placeholder).into(imgView)
//
//}
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
//fun EditProfile.CustomerOldData.toCustomerProfile() = CustomerProfile(
//        FullName = fullName,
//        IsMen = isMen ?: false,
//        Image = image,
//        Mobile = mobile,
//        cityId = cityId,
//        AreaId = areaId,
//        Email = email,
//        PrivateKey = privateKey(),
//        Age = ""
//
//)