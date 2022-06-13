package com.mirim.a3mcoding.view

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.AsyncTaskLoader
import com.mirim.a3mcoding.databinding.ActivityDescriptionBinding
import com.mirim.a3mcoding.network.RetrofitClient
import java.io.*
import java.net.URL
import java.nio.channels.AsynchronousByteChannel

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
            }
        }


    }
}