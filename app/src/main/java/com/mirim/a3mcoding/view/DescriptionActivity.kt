package com.mirim.a3mcoding.view

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mirim.a3mcoding.R
import com.mirim.a3mcoding.databinding.ActivityDescriptionBinding
import com.mirim.a3mcoding.network.RetrofitClient
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class DescriptionActivity : AppCompatActivity() {
    lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.textTitle.text = intent.getStringExtra("title")
        val problemType = intent.getStringExtra("problemType")
        val desc_path = intent.getStringExtra("desc_path")
        var path = RetrofitClient.BASE_URL + "description/" + problemType+"/"+desc_path
        val file = File(path)

        binding.btnHome.setOnClickListener {
            finish()
        }

        AsyncTask.execute {
            val sb = StringBuffer()

            try {
                val url = URL(path)
                val reader = BufferedReader(
                    InputStreamReader(url.openStream())
                )
                var str: String? = null
                while (reader.readLine().also { str = it } != null) {
                    sb.append(
                        """
                $str
                
                """.trimIndent()
                    )
                }

                runOnUiThread {
                    binding.txtContent.text = (sb.toString())
                }
                //Log.d("myapp", sb.toString());
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "파일을 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}