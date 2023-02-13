package com.aov2099.catapi.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.aov2099.catapi.R
import com.aov2099.catapi.databinding.ActivityCatDetailBinding
import com.aov2099.catapi.model.ApiCatService
import com.aov2099.catapi.model.CatResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityCatDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUp()
    }

    private fun setUp(){
        val imgId = intent.getStringExtra("ImgId")
        val catName = intent.getStringExtra("catName")
        val catDesc = intent.getStringExtra("description")
        val wikiUrl = intent.getStringExtra("wikipediaUrl")

        Picasso.get().load("https://cdn2.thecatapi.com/images/$imgId.jpg").into(binding.ivCatImage)
        binding.tvDescription.text = catDesc
        binding.tvCatName.text = catName

        binding.btnWikipedia.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(wikiUrl)
            startActivity(intent)
        }
    }

}