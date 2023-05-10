package com.luthfirr.mymoviedb.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.luthfirr.mymoviedb.R
import com.luthfirr.mymoviedb.core.domain.model.Movie
import com.luthfirr.mymoviedb.databinding.ActivityDetailBinding
import com.luthfirr.mymoviedb.ui.bottomsheet.BottomSheetShareFragment
import com.luthfirr.mymoviedb.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    private val def = "def"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAppbar()

        val movie = intent.getParcelableExtra<Movie>("extra_data")
        val movieId = movie?.id

        setupDetail(movie)
    }

    private fun setupAppbar() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupDetail(movie: Movie?) {
        binding.apply {

            ivBack.setOnClickListener {
                onBackPressed()
            }

            Glide.with(this@DetailActivity)
                .asBitmap()
                .load(Constant.IMAGE_BASE_URL + movie?.backdropPath)
                .into(ivDetailMovieBackground)

            Glide.with(this@DetailActivity)
                .asBitmap()
                .load(Constant.IMAGE_BASE_URL + movie?.posterPath)
                .transform(FitCenter(), RoundedCorners(16))
                .into(ivDetailMoviePoster)

            tvDetailMovieTitle.text = movie?.title
            tvDetailDescription.text = movie?.overview

            movie?.let {
                setFavoriteState(it.isFavorite)
                btnFavorite.setOnClickListener {
                    // switch movie's favorite state
                    movie.isFavorite = !movie.isFavorite

                    // set new favorite state to database
                    detailViewModel.setFavoriteMovie(movie, movie.isFavorite)

                    // set new favorite state ui
                    setFavoriteState(movie.isFavorite)

                    //set toast if button click
                    toastFavorite(movie.isFavorite)
                }

                btnShare.setOnClickListener {
                    showBottomSheetShare(movie)
                }
            }
        }
    }

    private fun getDetailReview(movieId: Int?) {

    }

    private fun setFavoriteState(isFavorite: Boolean) {
        if (isFavorite) {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
        }
        else {
            binding.btnFavorite.setImageResource(R.drawable.ic_no_favorite)
        }
    }

    private fun toastFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            Toast.makeText(this, "Successfully added to your Favorite list", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Successfully removed to your Favorite list", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showBottomSheetShare(movie: Movie?) {
        val bottomSheetShareFragment = BottomSheetShareFragment(movie)
        bottomSheetShareFragment.show(supportFragmentManager, bottomSheetShareFragment.tag)
    }

}