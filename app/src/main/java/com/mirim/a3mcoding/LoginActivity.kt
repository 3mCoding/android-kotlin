package com.mirim.a3mcoding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mirim.a3mcoding.databinding.ActivityLoginBinding
import com.mirim.a3mcoding.model.User
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
            startLogin(LoginRequest(inputEmail, inputPassword))
        }
    }
    fun startLogin(data: LoginRequest) {
        RetrofitClient.serviceAPI.userLogin(data).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("LoginActivity", response.toString())
                if(response.code() == 200) {
                    val result = response.body()

                    User.step = result?.step
                    User.name = result?.name
                    User.joinData = result?.date?.substring(0, 10)
                    User.rank = result?.rank
                    User.email = data.userEmail
                    User.student_num = result?.student_num

                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show()
            }

        })
    }


}