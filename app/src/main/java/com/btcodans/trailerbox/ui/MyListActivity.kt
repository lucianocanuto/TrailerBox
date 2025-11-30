package com.btcodans.trailerbox.ui

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.btcodans.trailerbox.R
import com.btcodans.trailerbox.data.local.FavoriteRepository
import com.btcodans.trailerbox.databinding.ActivityMyListBinding
import com.btcodans.trailerbox.ui.adapters.MovieAdapter

class MyListActivity : AppCompatActivity() {

     private val binding by lazy {
         ActivityMyListBinding.inflate(layoutInflater)
     }

    private lateinit var repo: FavoriteRepository
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        /*binding.logoText.text = Html.fromHtml(
            "<font color='#FFFFFF'>Trailer</font><font color='#FF0000'>Box</font>",
            Html.FROM_HTML_MODE_LEGACY
        )*/

        binding.logoText.text = Html.fromHtml(getString(R.string.logo_trailerbox), Html.FROM_HTML_MODE_LEGACY)

        repo = FavoriteRepository(this)

        adapter = MovieAdapter { movie ->
            val i = Intent(this, SinopseActivity::class.java)
            i.putExtra("movie", movie)
            startActivity(i)
        }

        binding.recyclerMyList.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerMyList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.submitList(repo.getAll())
    }
}