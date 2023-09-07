package com.example.guidebook.presentation.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.guidebook.R
import com.example.guidebook.domain.model.Data


class GuideRecyclerAdapter: RecyclerView.Adapter<GuideRecyclerAdapter.ViewHolder>() {
    private val visibleThreshold = 3
    var lastVisibleItem = 0
    private var totalItemCount = 0
    private var loading = false

    private val differ = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val list = AsyncListDiffer(this, differ)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val guideImage: ImageView = itemView.findViewById(R.id.guideImage)
        val guideName: TextView = itemView.findViewById(R.id.guideName)
        val guideEndDate: TextView = itemView.findViewById(R.id.guideEndDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list.currentList[position]
        holder.guideName.text = data.name
        holder.guideEndDate.text = data.endDate
        Glide.with(holder.guideImage.context)
            .load(data.icon)
            .into(holder.guideImage)

        holder.itemView.setOnClickListener {
            onItemClick?.onItemClick(data)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(data: Data)
    }

    private var onItemClick: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClick = onItemClickListener
    }

    fun setLoaded() {
        loading = false
    }
    interface LoadMoreListener{
        fun onLoadMore()
    }
    private var loadMoreListener: LoadMoreListener? = null
    fun setLoadMoreListener(loadMoreListener: LoadMoreListener){
        this.loadMoreListener = loadMoreListener
    }

    fun addMoreData(moreData: List<Data>){
        list.submitList(moreData)
    }

    fun setOnScrollListener(recyclerView: RecyclerView){
        if (recyclerView.layoutManager is LinearLayoutManager) {
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalItemCount = linearLayoutManager.itemCount
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                    Log.i("ind", lastVisibleItem.toString())
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        loadMoreListener?.onLoadMore()
                        loading = true
                    }
                }
            })
        }
    }
}