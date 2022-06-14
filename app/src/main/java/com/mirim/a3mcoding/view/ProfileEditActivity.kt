package com.mirim.a3mcoding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mirim.a3mcoding.databinding.ActivityProfileEditBinding
import com.mirim.a3mcoding.model.app
import com.mirim.a3mcoding.network.RetrofitClient
import com.mirim.a3mcoding.server.request.EditProfileRequest
import com.mirim.a3mcoding.server.response.Response
import retrofit2.Call
import retrofit2.Callback

class ProfileEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.textTitle.text = "프로필 수정하기"

        binding.editStudentNum.setText(app.user.student_num.toString())
        binding.editName.setText(app.user.name)

        binding.btnSubmit.setOnClickListener {
            var newName = binding.editName.text.toString().trim()
            var newStudentNum = binding.editStudentNum.text.toString().trim()
            editProfile(newName, newStudentNum)

        }

    }
    fun editProfile(newName: String, newStudentNum: String) {
        val name = if (newName.isEmpty()) app.user.name.toString() else newName
        val studentNum =
            if (newStudentNum.isEmpty()) app.user.student_num.toString() else newStudentNum
        RetrofitClient.serviceAPI.editProfile(
            app.user.email,
            EditProfileRequest(name = name, studentNumber = studentNum)
        ).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                Log.d("ProfileEditSuccess", response.body().toString())
                if(response.raw().code() == 200) {
                    app.user.name = name
                    app.user.student_num = studentNum.toInt()
                    Toast.makeText(applicationContext, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else {
                    Toast.makeText(applicationContext, response.raw().message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("ProfileEditFail", t.toString())
            }

        })
    }

}