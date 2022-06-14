package com.mirim.a3mcoding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mirim.a3mcoding.databinding.ActivityEditPasswordBinding
import com.mirim.a3mcoding.model.app
import com.mirim.a3mcoding.network.RetrofitClient
import com.mirim.a3mcoding.server.request.EditPasswordRequest
import com.mirim.a3mcoding.server.request.EditProfileRequest
import com.mirim.a3mcoding.server.response.Response
import retrofit2.Call
import retrofit2.Callback

class EditPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.textTitle.text = "비밀번호 수정하기"

        binding.btnSubmit.setOnClickListener {
            var password = binding.editPassword.text.toString().trim()
            var newPassword = binding.editNewPassword.text.toString().trim()
            editPassword(password, newPassword)

        }

    }
    fun editPassword(password: String, newPassword: String) {
        if(password.isEmpty()) {
            Toast.makeText(applicationContext, "기존 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if(newPassword.length < 8) {
            Toast.makeText(applicationContext, "비밀번호는 8자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
            return
        }
        RetrofitClient.serviceAPI.editPassword(
            app.user.email,
            EditPasswordRequest(password = password, newPassword = newPassword)
        ).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                Log.d("ProfileEditSuccess", response.body().toString())
                if(response.raw().code() == 200) {
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