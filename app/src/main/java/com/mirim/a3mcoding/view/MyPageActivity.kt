package com.mirim.a3mcoding.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mirim.a3mcoding.databinding.ActivityMyPageBinding
import com.mirim.a3mcoding.model.app

class MyPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.textTitle.text = "마이페이지"

        binding.txtEmail.text = app.user.email
        binding.txtName.text = app.user.student_num.toString() + " " + app.user.name
        binding.txtSolvedCount.text = "푼 문제 갯수 : " + app.user.solve_count

        binding.btnEditProfile.setOnClickListener {
            startActivity(Intent(applicationContext, ProfileEditActivity::class.java))
            binding.txtName.text = app.user.student_num.toString() + " " + app.user.name
        }
        binding.btnEditPassword.setOnClickListener {
            startActivity(Intent(applicationContext, EditPasswordActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.txtName.text = app.user.student_num.toString() + " " + app.user.name
    }

    override fun onRestart() {
        super.onRestart()
        binding.txtName.text = app.user.student_num.toString() + " " + app.user.name
    }
}