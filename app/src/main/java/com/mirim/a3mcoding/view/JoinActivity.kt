package com.mirim.a3mcoding.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mirim.a3mcoding.databinding.ActivityJoinBinding
import com.mirim.a3mcoding.model.User
import com.mirim.a3mcoding.model.app
import com.mirim.a3mcoding.network.RetrofitClient
import com.mirim.a3mcoding.server.request.JoinRequest
import com.mirim.a3mcoding.server.response.JoinResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoinBinding
    companion object {
        val TAG = "JoinActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJoin.setOnClickListener {
            attemptJoin()
        }
    }

    fun attemptJoin() {
        binding.editName.setError(null)
        binding.editStudentNum.setError(null)
        binding.editPassword.setError(null)
        binding.editPasswordCheck.setError(null)
        binding.editEmail.setError(null)

        val inputName = binding.editName.text.toString()
        val inputStudentNum = binding.editStudentNum.text.toString()
        val inputPassword = binding.editPassword.text.toString()
        val inputPasswordCheck = binding.editPasswordCheck.text.toString()
        val inputEmail = binding.editEmail.text.toString()

        var check = false
        var focusView: View? = null

        if(inputName.isEmpty()) {
            binding.editName.setError("이름을 입력해주세요.")
            focusView = binding.editName
            check = true
        }
        else if(inputStudentNum.isEmpty()) {
            binding.editStudentNum.setError("학번을 입력해주세요.")
            focusView = binding.editStudentNum
            check = true
        }
        else if(inputEmail.isEmpty() && inputEmail.contains("@e-mirim.hs.kr")) {
            binding.editEmail.setError("학교 이메일을 입력해주세요.")
            focusView = binding.editEmail
            check = true
        }
        else if(inputPassword.isEmpty()) {
            binding.editPassword.setError("비밀번호를 입력해주세요.")
            focusView = binding.editPassword
            check = true
        }
        else if(!inputPasswordCheck.equals(inputPassword)) {
            binding.editPasswordCheck.setError("비밀번호가 일치하지 않습니다.")
            focusView = binding.editPasswordCheck
            check = true
        }
        if(check) {
            focusView?.requestFocus()
        }
        else {
            startJoin(JoinRequest(
                userEmail = inputEmail,
            userName = inputName,
            userPw = inputPassword,
            userStudentNum = inputStudentNum))
        }
    }
    fun startJoin(data: JoinRequest) {
        RetrofitClient.serviceAPI.userJoin(data).enqueue(object: Callback<JoinResponse> {
            override fun onResponse(call: Call<JoinResponse>, response: Response<JoinResponse>) {
                Log.d(TAG, response.toString())
                if(response.code() == 201) {
                    val result = response.body()
                    val user = User(
                        stage = result?.data?.stage,
                        email = result?.data?.email,
                        student_num = result?.data?.student_num,
                        name = result?.data?.name,
                        solve_count = result?.data?.solve_count
                    )
                    app.user = user
                    Toast.makeText(applicationContext, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }
                else {
                    Toast.makeText(applicationContext, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        })
    }
}