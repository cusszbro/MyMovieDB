package com.luthfirr.mymoviedb.ui.favorite

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import com.luthfirr.mymoviedb.core.domain.model.Movie
import com.luthfirr.mymoviedb.databinding.ActivityFavoriteBinding
import com.luthfirr.mymoviedb.ui.adapter.FavoriteAdapter
import com.luthfirr.mymoviedb.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter

    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite Movies"

        favoriteAdapter = FavoriteAdapter()
        favoriteAdapter.onItemClick = { movie ->
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra("extra_data", movie)
            startActivity(intent)
        }


        observeFavorite()
        initListener()
    }

    private fun observeFavorite() {
        favoriteViewModel.favoriteMovie.observe(this) {
            it?.let { movies -> setAdapter(movies) }
        }
    }

    private fun setAdapter(movies: List<Movie>) {
        favoriteAdapter.setData(movies)
        binding.viewEmpty.root.visibility = if (movies.isNotEmpty()) View.GONE else View.VISIBLE

        // set adapter of rvListMovie
        binding.rvFavorite.apply {
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    private fun initListener() {
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }

}