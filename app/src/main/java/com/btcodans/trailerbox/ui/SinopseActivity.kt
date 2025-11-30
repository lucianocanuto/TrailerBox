package com.btcodans.trailerbox.ui

import com.btcodans.trailerbox.data.models.Movie
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.btcodans.trailerbox.data.local.FavoriteRepository
import com.btcodans.trailerbox.databinding.ActivitySinopseBinding
import com.bumptech.glide.Glide

class SinopseActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySinopseBinding.inflate(layoutInflater)
    }

    private lateinit var favRepo: FavoriteRepository
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        favRepo = FavoriteRepository(this)

        movie = intent.getParcelableExtra<Movie>("movie")
            ?: throw IllegalArgumentException("Movie não recebido")

        // Dados do filme
        binding.txtTitulo.text = movie.title
        binding.txtSinopse.text = movie.overview

// Converte String? para Double de forma segura
        val nota = movie.vote_average?.toDoubleOrNull() ?: 0.0

// Formata com 1 casa decimal
        val notaFormatada = String.format("%.1f", nota)

        binding.txtNota.text = "⭐ $notaFormatada"


        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(binding.posterImageView)

        // Atualiza o estado inicial do botão
        updateButton()

        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movieId", movie.id)
            startActivity(intent)
        }

        binding.btnAddLista.setOnClickListener {
            if (!favRepo.isFavorite(movie.id)) {
                favRepo.add(movie)
                Toast.makeText(this, "Adicionado à Minha Lista!", Toast.LENGTH_SHORT).show()
            } else {
                favRepo.remove(movie.id)
                Toast.makeText(this, "Removido da Minha Lista!", Toast.LENGTH_SHORT).show()
            }

            // Atualiza o texto do botão após a ação
            updateButton()
            finish()
        }
    }

    /** Atualiza o nome do botão dependendo se o filme está nos favoritos */
    private fun updateButton() {
        val isFav = favRepo.isFavorite(movie.id)

        if (isFav) {
            binding.btnAddLista.text = "Remover"
        } else {
            binding.btnAddLista.text = "Ver depois"
        }
    }
}
