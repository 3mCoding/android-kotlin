package com.mirim.a3mcoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mirim.a3mcoding.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoinBinding

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
            binding.editPassword.setError("비밀번호를 입력해주세요ㅕ.")
            focusView = binding.editPassword
            check = true
        }
        else if(!inputPasswordCheck.equals(inputPassword)) {

        }
    }
}