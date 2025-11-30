package com.btcodans.trailerbox.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.btcodans.trailerbox.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val txtLogo = findViewById<TextView>(R.id.txtLogo)

        // aplica o HTML do logo
        txtLogo.text = HtmlCompat.fromHtml(
            getString(R.string.logo_trailerbox),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        // animação pulsando
        val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse_trailerbox)
        txtLogo.startAnimation(pulse)

        // tempo do splash
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 4000)
    }
}
