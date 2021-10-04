package com.bintang.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bintang.myapplication.R
import androidx.lifecycle.ViewModelProvider
import com.bintang.myapplication.adapter.HomeAdapter
import com.bintang.myapplication.constant.Constant
import com.bintang.myapplication.databinding.FragmentHomeBinding
import com.bintang.myapplication.ui.detail.DetailActivity
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.daimajia.slider.library.Tricks.ViewPagerEx


class HomeFragment : Fragment(), BaseSliderView.OnSliderClickListener,
    ViewPagerEx.OnPageChangeListener {
    lateinit var viewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        observe()
        viewModel.getProduk()
    }

    private fun observe() {
        viewModel.isSuccess().observe(viewLifecycleOwner, { resSuccess(it) })
        viewModel.isError().observe(viewLifecycleOwner, { resError(it) })
        viewModel.isLoading().observe(viewLifecycleOwner, { isLoading(it) })
    }

    private fun isLoading(it: Boolean?) {
        if (it == true) {
            binding.progressBar.visibility = View.VISIBLE
        } else if (it == false) {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun resError(it: Throwable?) {
        Toast.makeText(context, "${it?.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    private fun resSuccess(it: ResHomeProduk?) {
        if (it?.data != null) {
            val adapter = HomeAdapter(it.data, object : HomeAdapter.onClickListener {
                override fun detail(item: DataItem?) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_USER, item)
                    startActivity(intent)
                    Toast.makeText(context, "${item?.price}", Toast.LENGTH_SHORT).show()
                }
            })
            binding.rvHome.adapter = adapter
            for (i in it.data.indices ?: 0..1) {
                var item = it.data.get(i)
                getSlider(item)
            }
        }
    }

    private fun getSlider(item: DataItem?) {
        var constant: Constant
        constant = Constant()

        var data = item
        val file_maps = HashMap<String, String>()

        for (i in data?.productPhotos?.indices ?: 0..1) {
            var item = data?.productPhotos?.get(i)
            file_maps[item?.fileName.toString()] = constant.URL_IMAGE + item?.fileName
        }

        for (name in file_maps.keys) {
            val textSliderView = TextSliderView(context)
            //initialize a Sliderlayout
            file_maps[name]?.let {
                textSliderView
                    .description(name)
                    .image(it)
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this)
            }

            //tambahkan informasi tambahan
            textSliderView.bundle(Bundle())
            textSliderView.bundle
                .putString("extra", name)
            binding.slider.addSlider(textSliderView)
        }

        binding.slider.setPresetTransformer(SliderLayout.Transformer.Tablet)
        binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        binding.slider.setCustomAnimation(DescriptionAnimation())
        binding.slider.setDuration(5000)
        binding.slider.addOnPageChangeListener(this)
    }

    override fun onSliderClick(slider: BaseSliderView?) {
        Toast.makeText(context, slider?.bundle?.get("extra").toString() + "", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {
    }
}