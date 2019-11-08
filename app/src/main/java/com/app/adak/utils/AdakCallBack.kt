package com.app.adak.utils

import android.animation.Animator
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import com.app.adak.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class MyCallBack<T>(var context: Context, showLoading: Boolean) : Callback<T> {

    var dialog: Dialog = Dialog(context)

    init {
        Log.d("--------", "init")
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dial_loading)
        val dialogBase = dialog.findViewById<FrameLayout>(R.id.dialogBase)
        if (showLoading) {
            dialog.show()
            setVisbileGone(dialogBase, View.VISIBLE, dialog)

        }


    }

    private fun setVisbileGone(view: View, Mode: Int, dialog: Dialog) {

        if (Mode == View.GONE && view.visibility != Mode) {
            view.visibility = View.VISIBLE
            view.animate().scaleY(0.9f).scaleX(0.9f).setDuration(100)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator) {

                    }

                    override fun onAnimationEnd(animator: Animator) {
                        view.animate().scaleY(1.2f).scaleX(1.2f).setDuration(100)
                            .setListener(object : Animator.AnimatorListener {
                                override fun onAnimationStart(animation: Animator) {

                                }

                                override fun onAnimationEnd(animation: Animator) {
                                    view.animate().scaleX(0.5f).scaleY(0.5f).alpha(0f)
                                        .setDuration(200)
                                        .setListener(object : Animator.AnimatorListener {
                                            override fun onAnimationStart(animation: Animator) {

                                            }

                                            override fun onAnimationEnd(animation: Animator) {
                                                view.visibility = View.GONE
                                                try {
                                                    dialog.dismiss()
                                                } catch (e: Exception) {

                                                }

                                            }

                                            override fun onAnimationCancel(animation: Animator) {

                                            }

                                            override fun onAnimationRepeat(animation: Animator) {

                                            }
                                        })
                                }

                                override fun onAnimationCancel(animation: Animator) {

                                }

                                override fun onAnimationRepeat(animation: Animator) {

                                }
                            })
                    }

                    override fun onAnimationCancel(animator: Animator) {

                    }

                    override fun onAnimationRepeat(animator: Animator) {

                    }
                })
        }
        if (Mode == View.VISIBLE && view.visibility != Mode) {
            view.visibility = View.VISIBLE
            view.animate().scaleY(1.2f).scaleX(1.2f).alpha(1f).setDuration(200)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator) {

                    }

                    override fun onAnimationEnd(animator: Animator) {


                        view.animate().scaleY(0.9f).scaleX(0.9f).setDuration(100)
                            .setListener(object : Animator.AnimatorListener {
                                override fun onAnimationStart(animation: Animator) {

                                }

                                override fun onAnimationEnd(animation: Animator) {
                                    view.animate().scaleX(1f).scaleY(1f).setDuration(100)
                                        .setListener(object : Animator.AnimatorListener {
                                            override fun onAnimationStart(animation: Animator) {

                                            }

                                            override fun onAnimationEnd(animation: Animator) {

                                            }

                                            override fun onAnimationCancel(animation: Animator) {

                                            }

                                            override fun onAnimationRepeat(animation: Animator) {

                                            }
                                        })
                                }

                                override fun onAnimationCancel(animation: Animator) {

                                }

                                override fun onAnimationRepeat(animation: Animator) {

                                }
                            })


                    }

                    override fun onAnimationCancel(animator: Animator) {

                    }

                    override fun onAnimationRepeat(animator: Animator) {

                    }
                })
        }

    }


    override fun onResponse(call: Call<T>, response: Response<T>) {
        Log.d("--------", "onResponse")
        if (dialog.isShowing) {
            val dialogBase = dialog.findViewById<FrameLayout>(R.id.dialogBase)
            setVisbileGone(dialogBase, View.GONE, dialog)


        }


    }


    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.d("--------", "onFailure")
        if (dialog.isShowing) {
            val dialogBase = dialog.findViewById<FrameLayout>(R.id.dialogBase)
            setVisbileGone(dialogBase, View.GONE, dialog)
        }

    }

}
