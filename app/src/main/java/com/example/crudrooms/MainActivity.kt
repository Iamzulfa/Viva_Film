package com.example.crudrooms

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudrooms.room.MovieDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var movieAdapter: MovieAdapter

    val db by lazy { MovieDB(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
        setupRecyclerView()
    }

    override fun onStart(){
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val movies = db.movieDao().getMovies()
            Log.d("MainActivity", "dbresponse: $movies")

            withContext(Dispatchers.Main){
                movieAdapter.setData(movies)
            }

        }
    }

    @SuppressLint("RestrictedApi")
    fun setupListener(){
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        add_movie.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

    }

    private fun setupRecyclerView(){
        movieAdapter = MovieAdapter(arrayListOf())

        rv_movie.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

