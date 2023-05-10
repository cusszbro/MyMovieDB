package com.luthfirr.mymoviedb.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.luthfirr.mymoviedb.R
import com.luthfirr.mymoviedb.core.data.Resource
import com.luthfirr.mymoviedb.databinding.ActivityMainBinding
import com.luthfirr.mymoviedb.ui.adapter.NowPlayingAdapter
import com.luthfirr.mymoviedb.ui.adapter.PopularMovieAdapter
import com.luthfirr.mymoviedb.ui.adapter.TopRatedAdapter
import com.luthfirr.mymoviedb.ui.detail.DetailActivity
import com.luthfirr.mymoviedb.ui.favorite.FavoriteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var topRatedAdapter: TopRatedAdapter
    private lateinit var nowPlayingAdapter: NowPlayingAdapter

    private val mainViewModel: MainViewModel by viewModels()

    private val abc = "abc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Movies"

        popularMovieAdapter = PopularMovieAdapter()
        popularMovieAdapter.onItemClick = { movie ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("extra_data", movie)
            startActivity(intent)
        }

        topRatedAdapter = TopRatedAdapter()
        topRatedAdapter.onItemClick = { movie ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("extra_data", movie)
            startActivity(intent)
        }

        nowPlayingAdapter = NowPlayingAdapter()
        nowPlayingAdapter.onItemClick = { movie ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("extra_data", movie)
            startActivity(intent)
        }

        observePopularMovie()
        observeTopRatedMovie()
        observeNowPlaying()

    }

    private fun observePopularMovie() {
        mainViewModel.popularMovie.observe(this) {
            it?.let { movie ->
                when (movie) {
                    is Resource.Loading -> {
                        binding.progressbar.isVisible = true
                    }
                    is Resource.Success -> {
                        binding.progressbar.isVisible = false
                        popularMovieAdapter.setData(movie.data)

                        // set adapter of rvListMovie
                        binding.rvPopular.apply {
                            setHasFixedSize(true)
                            adapter = popularMovieAdapter
                        }
                    }
                    is Resource.Error -> {
                        binding.progressbar.isVisible = false
                        val errorMessage = movie.message ?: "Sorry, error detected\nPlease try again later"
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun observeTopRatedMovie() {
        mainViewModel.topRatedMovie.observe(this) {
            it?.let { movie ->
                when (movie) {
                    is Resource.Loading -> {
                        binding.progressbar.isVisible = true
                    }
                    is Resource.Success -> {
                        binding.progressbar.isVisible = false
                        topRatedAdapter.setData(movie.data)

                        // set adapter of rvListMovie
                        binding.rvTopRated.apply {
                            setHasFixedSize(true)
                            adapter = topRatedAdapter
                        }
                    }
                    is Resource.Error -> {
                        binding.progressbar.isVisible = false
                        val errorMessage = movie.message ?: "Sorry, error detected\nPlease try again later"
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun observeNowPlaying() {
        mainViewModel.movie.observe(this) {
            it?.let { movie ->
                when (movie) {
                    is Resource.Loading -> {
                        binding.progressbar.isVisible = true
                    }
                    is Resource.Success -> {
                        binding.progressbar.isVisible = false
                        nowPlayingAdapter.setData(movie.data)

                        // set adapter of rvListMovie
                        binding.rvNowPlaying.apply {
                            setHasFixedSize(true)
                            adapter = nowPlayingAdapter
                        }
                    }
                    is Resource.Error -> {
                        binding.progressbar.isVisible = false
                        val errorMessage = movie.message ?: "Sorry, error detected\nPlease try again later"
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                Intent(this, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}