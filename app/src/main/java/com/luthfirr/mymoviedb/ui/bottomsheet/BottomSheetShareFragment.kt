package com.luthfirr.mymoviedb.ui.bottomsheet

import android.content.ContentResolver
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.luthfirr.mymoviedb.R
import com.luthfirr.mymoviedb.core.domain.model.Movie
import com.luthfirr.mymoviedb.databinding.FragmentBottomSheetShareBinding
import com.luthfirr.mymoviedb.utils.Constant

class BottomSheetShareFragment(private val movie: Movie?) : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetShareBinding? = null
    private val binding get() = _binding!!
    private lateinit var contentResolver: ContentResolver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentResolver = requireActivity().contentResolver
        return inflater.inflate(R.layout.fragment_bottom_sheet_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBottomSheetShareBinding.bind(view)

        binding.apply {
            Glide.with(requireContext())
                .asBitmap()
                .load(Constant.IMAGE_BASE_URL + movie?.backdropPath)
                .transform(FitCenter(), RoundedCorners(16))
                .into(ivShareMoviePoster)

            tvShareMovieTitle.text = movie?.title

            btnWa.setOnClickListener {
                val imageDrawable = ivShareMoviePoster.drawable
                val mBitmap = (imageDrawable as BitmapDrawable).bitmap
                val path = MediaStore.Images.Media.insertImage(
                    contentResolver,
                    mBitmap,
                    "Image Description",
                    null
                )
                val uri = Uri.parse(path)
                val intentShare = Intent(Intent.ACTION_SEND)
                intentShare.setPackage("com.whatsapp")
                intentShare.type = "image/*"
                intentShare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intentShare.putExtra(Intent.EXTRA_STREAM, uri)
                intentShare.putExtra(
                    Intent.EXTRA_TEXT,
                    "Movie Name : \n${movie?.title} " +
                            "\nRating : ${movie?.voteAverage}" +
                            "\nRelease date : ${movie?.releaseDate}" +
                            "\nOverview : \n${movie?.overview}"
                )
                startActivity(intentShare)
            }

            btnIg.setOnClickListener {
                val imageDrawable = ivShareMoviePoster.drawable
                val mBitmap = (imageDrawable as BitmapDrawable).bitmap
                val path = MediaStore.Images.Media.insertImage(
                    contentResolver,
                    mBitmap,
                    "Image Description",
                    null
                )
                val uri = Uri.parse(path)
                val intentShare = Intent(Intent.ACTION_SEND)
                intentShare.setPackage("com.instagram.android")
                intentShare.type = "image/*"
                intentShare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intentShare.putExtra(Intent.EXTRA_STREAM, uri)
                intentShare.putExtra(
                    Intent.EXTRA_TEXT,
                    "Movie Name : \n${movie?.title} " +
                            "\nRating : ${movie?.voteAverage}" +
                            "\nRelease date : ${movie?.releaseDate}" +
                            "\nOverview : \n${movie?.overview}"
                )
                startActivity(intentShare)
            }

            btnOther.setOnClickListener {
                val subject = "Share about ${movie?.title}"
                val imageDrawable = ivShareMoviePoster.drawable
                val mBitmap = (imageDrawable as BitmapDrawable).bitmap
                val path = MediaStore.Images.Media.insertImage(
                    contentResolver,
                    mBitmap,
                    "Image Description",
                    null
                )
                val uri = Uri.parse(path)
                val intentShare = Intent(Intent.ACTION_SEND)
                intentShare.type = "image/*"
                intentShare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intentShare.putExtra(Intent.EXTRA_STREAM, uri)
                intentShare.putExtra(Intent.EXTRA_SUBJECT, subject)
                intentShare.putExtra(
                    Intent.EXTRA_TEXT,
                    "Movie Name : \n${movie?.title} " +
                            "\nRating : ${movie?.voteAverage}" +
                            "\nRelease date : ${movie?.releaseDate}" +
                            "\nOverview : \n${movie?.overview}"
                )
                startActivity(intentShare)
            }
        }
    }


}