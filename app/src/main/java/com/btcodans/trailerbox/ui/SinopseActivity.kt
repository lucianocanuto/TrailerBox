package com.btcodans.trailerbox.ui

import com.btcodans.trailerbox.data.models.Movie

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.btcodans.trailerbox.databinding.ActivitySinopseBinding
import com.bumptech.glide.Glide

class SinopseActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySinopseBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)



        val movie = intent.getParcelableExtra<Movie>("movie")
            ?: throw IllegalArgumentException("Movie não recebido")


        binding.txtTitulo.text = movie.title
        binding.txtSinopse.text = movie.overview
        binding.txtNota.text = "⭐ ${movie.vote_average}"


        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(binding.posterImageView)

        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            startActivity(intent)
        }
    }
}

