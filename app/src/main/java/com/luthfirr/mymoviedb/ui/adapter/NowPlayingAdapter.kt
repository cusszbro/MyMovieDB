package com.luthfirr.mymoviedb.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.luthfirr.mymoviedb.core.domain.model.Movie
import com.luthfirr.mymoviedb.databinding.ItemTopRatedBinding
import com.luthfirr.mymoviedb.utils.Constant

class NowPlayingAdapter: RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    private var _binding: ItemTopRatedBinding? = null
    private val binding get() = _binding!!
    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        listData.forEachIndexed { index, _ -> notifyItemChanged(index) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTopRatedBinding.bind(itemView)

        init {
            binding.cvItemTopRated.setOnClickListener { onItemClick?.invoke(listData[adapterPosition]) }
        }

        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(Constant.IMAGE_BASE_URL + data.posterPath)
                    .transform(FitCenter(), RoundedCorners(16))
                    .into(ivMovieTopRated)

                tvTitleTopRated.text = data.title
                tvTitleTopRated.maxLines = 2
                tvTitleTopRated.ellipsize = TextUtils.TruncateAt.END
                tvRateTopRated.text = "Rate : ${data.voteAverage}"
                tvReleaseTopRated.text = data.releaseDate
                tvItemRelease.visibility = View.VISIBLE
                tvReleaseTopRated.visibility = View.VISIBLE
                tvRateTopRated.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemTopRatedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listData[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listData.size

}