package com.btcodans.trailerbox.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.btcodans.trailerbox.databinding.FragmentListBinding
import com.btcodans.trailerbox.ui.adapters.MovieAdapter
import com.btcodans.trailerbox.data.repository.MovieRepository
import kotlinx.coroutines.launch

class NowPlayingFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MovieAdapter
    private val repo by lazy { MovieRepository("b10f2c762703b5fb0dcecf42212f083c") }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MovieAdapter { movie ->
            val i = Intent(requireContext(), SinopseActivity::class.java)
            i.putExtra("movie", movie)
            startActivity(i)

        }
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recycler.adapter = adapter


        loadMovies()
    }


    private fun loadMovies() {
        lifecycleScope.launch {
            try {
                val resp = repo.getNowPlaying()
                adapter.submitList(resp.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}