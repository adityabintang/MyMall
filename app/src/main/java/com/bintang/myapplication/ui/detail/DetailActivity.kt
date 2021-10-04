package com.bintang.myapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bintang.myapplication.Helpers.formatPrice
import com.bintang.myapplication.R
import com.bintang.myapplication.constant.Constant
import com.bintang.myapplication.databinding.ActivityDetailBinding
import com.bintang.myapplication.ui.home.DataItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataProducts = intent.getParcelableExtra<DataItem>(EXTRA_USER) as DataItem
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val viewBinding = binding.root
        setContentView(viewBinding)
        var constant: Constant? = null
        constant = Constant()
        val imageProducts = binding.imageView4
        val productCode = binding.txtNamaProduct
        val titleProduk = binding.txtSubNamaProduct
        val price = binding.hargaProduct
        val deskripsi = binding.txtDeskripsi

        var data = dataProducts
        val file_maps = HashMap<String, String>()
        for (i in data?.productPhotos?.indices ?: 0..1) {
            var item = data.productPhotos?.get(i)
            file_maps[item?.fileName.toString()] = constant.URL_IMAGE + item?.fileName
            Glide.with(this)
                .load(file_maps[item?.fileName.toString()])
                .apply(RequestOptions().error(R.drawable.icon_nopic))
                .into(imageProducts)
        }
        productCode.text = dataProducts.productCode
        titleProduk.text = dataProducts.title
        price.formatPrice(dataProducts.price.toString())
        deskripsi.text = dataProducts.description
    }
}