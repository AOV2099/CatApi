package com.aov2099.catapi.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aov2099.catapi.R
import com.aov2099.catapi.controller.CatAdapter
import com.aov2099.catapi.controller.CatsClickListener
import com.aov2099.catapi.databinding.ActivityMainBinding
import com.aov2099.catapi.model.ApiCatService
import com.aov2099.catapi.model.CatResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CatsClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CatAdapter
    private val catData = mutableListOf<CatResponse>()

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        setUpRv()
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getData(){

        CoroutineScope(Dispatchers.IO).launch {
            val retrofitCall = getRetrofit()
                .create(ApiCatService::class.java)
                .getCats("breeds?limit=50&page=0&api_key=live_7gCVSCp20OkAk7ldsoBkTF344obsFKulKZrtXQhqjZNFGjsafc3rAPxCdMsWoZ7K")

            val res: List<CatResponse>? =  retrofitCall.body()

            runOnUiThread{

                if (retrofitCall.isSuccessful){
                    val cats:List< CatResponse > = res ?: emptyList()

                    if (cats.isNotEmpty()){
                        catData.addAll(cats)
                    }
                    adapter.notifyDataSetChanged()
                }

            }
        }

    }

    private fun setUpRv(){

        adapter = CatAdapter(catData, this)
        binding.rvCats.layoutManager = GridLayoutManager(this, 2)
        binding.rvCats.adapter = adapter
    }

    override fun onClick(cat: CatResponse) {
        //Toast.makeText(this, cat.breedId, Toast.LENGTH_SHORT).show()
        val detailIntent = Intent(this, CatDetailActivity::class.java)
        detailIntent.putExtra("ImgId", cat.imgId)
        detailIntent.putExtra("catId", cat.breedId)
        detailIntent.putExtra("catName", cat.catName)
        detailIntent.putExtra("description", cat.description)
        detailIntent.putExtra("wikipediaUrl", cat.wikipediaUrl)

        startActivity(detailIntent)
    }


}