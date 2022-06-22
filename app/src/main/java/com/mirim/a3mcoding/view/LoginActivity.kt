package com.mirim.a3mcoding.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mirim.a3mcoding.databinding.ActivityLoginBinding
import com.mirim.a3mcoding.model.User
import com.mirim.a3mcoding.model.app
import com.mirim.a3mcoding.network.RetrofitClient
import com.mirim.a3mcoding.server.request.LoginRequest
import com.mirim.a3mcoding.server.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJoin.setOnClickListener {
            startActivity(Intent(applicationContext, JoinActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            attemptLogin()
        }
    }

    fun attemptLogin() {
        binding.editEmail.setError(null)
        binding.editPassword.setError(null)

        val inputEmail = binding.editEmail.text.toString()
        val inputPassword = binding.editPassword.text.toString()

        var focusView: View? = null
        var cancel: Boolean = false

        if(inputPassword.isEmpty()) {
            binding.editPassword.setError("비밀번호를 입력해주세요.")
            focusView = binding.editPassword
            cancel = true
        } else if(inputPassword.length < 8) {
            binding.editPassword.setError("8자 이상의 비밀번호를 입력해주세요.")
            focusView = binding.editPassword
            cancel = true
        }

        if(inputEmail.isEmpty()) {
            binding.editEmail.setError("학교 이메일을 입력해주세요")
            focusView = binding.editEmail
            cancel = true
        }

        if(cancel) {
            focusView?.requestFocus()
        }
        else {
            startLogin(LoginRequest(userEmail = inputEmail, userPw = inputPassword))
        }
    }
    fun startLogin(data: LoginRequest) {
        RetrofitClient.serviceAPI.userLogin(data).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("LoginActivity", response.toString())
                if(response.raw().code() == 200) {
                    val result = response.body()
                    val user = User(
                        stage = result?.data?.stage,
                        email = result?.data?.email,
                        student_num = result?.data?.student_num,
                        name = result?.data?.name,
                        solve_count = result?.data?.solve_count
                    )
                    app.user = user
                    Toast.makeText(applicationContext, response.body()?.result, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(applicationContext,"로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                Log.d("LoginActivity-fail", t.toString())
            }

        })
    }


}