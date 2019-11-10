package com.app.adak.ui.home

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.app.adak.R
import com.app.adak.data.network.NetModule
import com.app.adak.data.network.model.Users
import com.app.adak.data.network.services.ApiService
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import com.app.adak.data.network.DaggerAppComponent
import kotlinx.android.synthetic.main.activity_home.*

import retrofit2.Response
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatDelegate
import com.app.adak.App
import com.app.adak.data.network.model.UserCreateRes
import com.app.adak.ui.customWidget.EditTextAdak
import com.app.adak.ui.customWidget.TextViewAdak
import com.app.adak.utils.*
import com.google.android.gms.security.ProviderInstaller
import okhttp3.ResponseBody


class HomeActivity : AppCompatActivity() {


    @Inject
    lateinit var retrofit: Retrofit
    lateinit var userAdapter: UserAdapter
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        App.getApp().AppComponent().inject(this)
        apiService = retrofit.create(ApiService::class.java)

        init()
    }



    private fun init() {

        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        userItems.clear()
        getUser(1, true)
    }


    private fun getUser(page: Int, showLoading: Boolean) {


        val call: Call<Users> = apiService.getUser(page)
        call.enqueue(object : MyCallBack<Users>(this@HomeActivity, showLoading) {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                super.onResponse(call, response)
                var model = response.body()
                loadUserData(model!!, page)
                isLoading = false
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                super.onFailure(call, t)
                logdFailure("getUser:  " + t.message)
                isLoading = false
            }

        })
    }

    var isLoading: Boolean = false
    var userItems: ArrayList<Users.Data> = ArrayList()
    private fun loadUserData(user: Users, page: Int) {
        if (page == 1) {
            userItems.addAll(user.data)
            userAdapter = UserAdapter(this@HomeActivity, R.layout.item_users, userItems)
            userListGrid.adapter = userAdapter
        } else {
            userItems.addAll(user.data)
            userAdapter.notifyDataSetChanged()
        }





        userListGrid.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(p0: AbsListView?, p1: Int, p2: Int, p3: Int) {
                logd("11")
                logd(userListGrid.firstVisiblePosition.toString())
                logd(userListGrid.lastVisiblePosition.toString())
                var itemInPage = user.page * user.perPage
                if (userListGrid.lastVisiblePosition > itemInPage - 2) {
                    if (user.totalPages > page && !isLoading) {
                        logd("userPage:    " + user.page.toString())
                        isLoading = true
                        getUser(page + 1, false)

                    }
                }
            }

            override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
                logd("22")
            }


        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.addUser) {
            addNewUser(-1)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }


    fun addNewUser(pos: Int) {
        var dialog = Dialog(this@HomeActivity)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dial_add_user)
        val btnConfirm = dialog.findViewById<TextViewAdak>(R.id.btnConfirm)
        val btnClose = dialog.findViewById<TextViewAdak>(R.id.btnClose)
        val txtTitle = dialog.findViewById<TextViewAdak>(R.id.txtTitle)
        val etFullName = dialog.findViewById<EditTextAdak>(R.id.etFullName)
        val etJob = dialog.findViewById<EditTextAdak>(R.id.etJob)


        var user = UserCreateRes("", "", "", "")

        if (pos >= 0) {
            txtTitle.text = "Edit user"
            var model = userItems[pos]
            user.id = model.id.toString()
            user.name = model.firstName + " " + model.lastName
            etFullName.setText(user.name)

        } else {
            txtTitle.text = "Add new user"
        }


        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        btnConfirm.setOnClickListener {
            var name = etFullName.text.toString()
            var job = etJob.text.toString()
            if (name.length > 1 && job.length > 1) {
                dialog.dismiss()
                if (pos > 0) {
                    user.name = name
                    user.job = job
                    updateUser(user)
                } else {
                    user.name = name
                    user.job = job
                    addNewUser(user)

                }

            } else {
                toast("Please enter the correct information")
            }


        }


        val window = dialog.window
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.show()


    }


    fun addNewUser(user: UserCreateRes) {

        val call: Call<UserCreateRes> = apiService.createUser(user)
        call.enqueue(object : MyCallBack<UserCreateRes>(this@HomeActivity, true) {
            override fun onResponse(call: Call<UserCreateRes>, response: Response<UserCreateRes>) {
                super.onResponse(call, response)
                var model = response.message()
                if (response.isSuccessful) {
                    toast("Is successfully")
                    userItems.add(
                        Users.Data(
                            "",
                            "",
                            response.body()!!.name,
                            response.body()!!.id!!.toInt(),
                            ""
                        )
                    )
                    userAdapter.notifyDataSetChanged()
                } else {
                    toast("failure")
                }
                logdSuccess("addNewUser" + response.body())
            }

            override fun onFailure(call: Call<UserCreateRes>, t: Throwable) {
                super.onFailure(call, t)
                logdFailure("addNewUser")

            }

        })
    }

    fun updateUser(user: UserCreateRes) {

        val call: Call<UserCreateRes> = apiService.UpdateUser(user.id!!.toInt(), user)
        call.enqueue(object : MyCallBack<UserCreateRes>(this@HomeActivity, true) {
            override fun onResponse(call: Call<UserCreateRes>, response: Response<UserCreateRes>) {
                super.onResponse(call, response)
                var model = response.body()
                if (response.isSuccessful) {
                    toast("Is successfully")
                    var userModel = userItems.find { c -> c.id == model!!.id!!.toInt() }
                    userModel!!.firstName = model!!.name
                    userModel!!.lastName = ""
                    userAdapter.notifyDataSetChanged()
                } else {
                    toast("failure")
                }
                logdSuccess("updateUser" + response.body())
            }

            override fun onFailure(call: Call<UserCreateRes>, t: Throwable) {
                super.onFailure(call, t)
                logdFailure("updateUser")

            }

        })
    }

    fun deleteUser(id: Int) {

        val call: Call<ResponseBody> = apiService.deleteUser(id)
        call.enqueue(object : MyCallBack<ResponseBody>(this@HomeActivity, true) {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                super.onResponse(call, response)
                if (response.isSuccessful) {
                    toast("Is successfully")

                    userItems.remove(userItems.find { c -> c.id == id })
                    userAdapter.notifyDataSetChanged()
                } else {
                    toast("failure")
                }
                logdSuccess("deleteUser" + response.body())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                super.onFailure(call, t)
                logdFailure("deleteUser")

            }

        })
    }
}
