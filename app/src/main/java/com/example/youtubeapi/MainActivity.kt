package com.example.youtubeapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.adapter.YoutubeAdapter
import com.example.youtubeapi.databinding.ActivityMainBinding
import com.example.youtubeapi.model.YoutubeModel
import com.example.youtubeapi.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val youtubeAdapter by lazy { YoutubeAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = youtubeAdapter
        }
        callApi()
        youtubeAdapter.onClick = {
            val bundle = bundleOf("item" to it)
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun callApi() {
        RetroInstance.retroInstance().getAllData().enqueue(object : Callback<YoutubeModel> {
            override fun onResponse(call: Call<YoutubeModel>, response: Response<YoutubeModel>) {
                if (response.isSuccessful) {
                    binding.pr.isVisible = false
                    youtubeAdapter.submitList(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<YoutubeModel>, t: Throwable) {
                Log.d("@@@", "onFailure: ${t.message}")
            }
        })
    }
}