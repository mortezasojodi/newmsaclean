package com.example.newcleanmsa.Product

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.ProductEntity
import com.example.newcleanmsa.R
import kotlinx.android.synthetic.main.card_view.view.*

class ProductAdapter (private val context: Context, val showDetail: (ProductEntity)->Unit): RecyclerView.Adapter<ProductAdapter.AnimeViewHolder>() {


    private var listData = ArrayList<ProductEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.card_view,parent,false)
        return  AnimeViewHolder(view)

    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val i = listData[position]
        holder.setData(i,position)
    }

    override fun getItemCount(): Int {
        return listData.size;
    }

    fun setData(newListData:List<ProductEntity>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class AnimeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun setData( hobbi:ProductEntity?, pos: Int){
            hobbi?.let {
                itemView.txvTitle.text = hobbi.title
                itemView.imgShare.setImageURI(Uri.parse(hobbi.image))

                itemView.setOnClickListener({
                    showDetail(hobbi)
                })

            }
        }
        }


}