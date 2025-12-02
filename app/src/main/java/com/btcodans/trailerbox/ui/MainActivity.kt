package com.btcodans.trailerbox.ui

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.btcodans.trailerbox.R
import com.btcodans.trailerbox.databinding.ActivityMainBinding
import com.btcodans.trailerbox.ui.adapters.TrailerPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val logoText = findViewById<TextView>(R.id.logoText)
        logoText.text = Html.fromHtml(getString(R.string.logo_trailerbox), Html.FROM_HTML_MODE_LEGACY)

        val adapter = TrailerPagerAdapter(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Populares"
                1 -> "Em cartaz"
                else -> "Próximas"
            }
        }.attach()

        binding.txtMinhaLista.setOnClickListener {
            startActivity(Intent(this, MyListActivity::class.java))
        }


    }
}