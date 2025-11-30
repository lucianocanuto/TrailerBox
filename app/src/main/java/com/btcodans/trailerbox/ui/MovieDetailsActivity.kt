package com.btcodans.trailerbox.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.btcodans.trailerbox.databinding.ActivityMovieDetailsBinding
import com.btcodans.trailerbox.data.repository.MovieRepository
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private val repo by lazy { MovieRepository("b10f2c762703b5fb0dcecf42212f083c") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra("movieId", 0)
        val title = intent.getStringExtra("movie_title")
        binding.tvTitle.text = title

        // YoutubePlayerView observar o lifecycle
        lifecycle.addObserver(binding.youtubePlayerView)

        if (movieId != -1) loadTrailer(movieId)
    }

    private fun loadTrailer(movieId: Int) {
        MainScope().launch {
            try {
                val videos = repo.getVideos(movieId)
                val yt = videos.results.firstOrNull {
                    it.site.equals("YouTube", true) &&
                            it.type.equals("Trailer", true)
                }

                yt?.let { video ->
                    startYoutube(video.key)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun startYoutube(videoKey: String) {

        // Inicia o vídeo
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(player: YouTubePlayer) {
                player.loadVideo(videoKey, 0f)
            }
        })

        // Fullscreen
        binding.youtubePlayerView.addFullscreenListener(object : FullscreenListener {

            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                binding.fullscreenContainer.visibility = View.VISIBLE
                binding.fullscreenContainer.addView(fullscreenView)
            }

            override fun onExitFullscreen() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                binding.fullscreenContainer.removeAllViews()
                binding.fullscreenContainer.visibility = View.GONE
            }
        })
    }
}
