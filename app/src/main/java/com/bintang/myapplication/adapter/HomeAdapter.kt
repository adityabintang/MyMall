package com.bintang.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bintang.myapplication.Helpers.formatPrice
import com.bintang.myapplication.R
import com.bintang.myapplication.constant.Constant
import com.bintang.myapplication.ui.home.DataItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HomeAdapter(
    var data: List<DataItem?>?,
    var itemCLick: onClickListener,
) : RecyclerView.Adapter<HomeAdapter.ProductMainHolder>() {
    class ProductMainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namaProduct = itemView.findViewById<TextView>(R.id.txtSubNamaProduct)
        var gambar = itemView.findViewById<ImageView>(R.id.imgGambarProduct)
        var harga = itemView.findViewById<TextView>(R.id.txtHargaProduct)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductMainHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_home, viewGroup, false)
        return ProductMainHolder(view)
    }

    override fun onBindViewHolder(holder: ProductMainHolder, position: Int) {
     val item = data?.get(position)
        var constant: Constant? = null
        constant = Constant()

        var data = item
        val file_maps = HashMap<String, String>()
        for (i in data?.productPhotos?.indices ?: 0..1){
            var item = data?.productPhotos?.get(i)
            file_maps[item?.fileName.toString()] = constant.URL_IMAGE + item?.fileName
            Glide.with(holder.itemView.context)
                .load(file_maps[item?.fileName.toString()])
                .apply(RequestOptions().error(R.drawable.icon_nopic))
                .into(holder.gambar)
        }
        holder.namaProduct.text = item?.title
        holder.harga.formatPrice(item?.price.toString())

        holder.itemView.setOnClickListener {
            itemCLick.detail(item)
        }
    }
    override fun getItemCount(): Int = data?.size ?: 0

    interface onClickListener {
        fun detail(item: DataItem?)
    }
}


