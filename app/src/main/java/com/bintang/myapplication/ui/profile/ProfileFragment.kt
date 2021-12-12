package com.mobile.ecommercemymall.View.Profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bintang.myapplication.R
import com.bintang.myapplication.constant.Constant
import com.bintang.myapplication.databinding.FragmentProfileBinding
import com.bintang.myapplication.session.SessionManager
import com.bintang.myapplication.ui.profile.DatasItem
import com.bintang.myapplication.ui.profile.ProfileViewModel
import com.bintang.myapplication.ui.profile.ResProfile
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ProfileFragment : Fragment() {
    lateinit var viewModel: ProfileViewModel
    lateinit var binding: FragmentProfileBinding
    lateinit var compositeDisposable: CompositeDisposable
    private var session: SessionManager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        session = context?.let { SessionManager(it) }
        observe()
        compositeDisposable = CompositeDisposable()
        if (session?.login == true) {
            session?.id?.let { viewModel.getProfile(it) }
        }
//        viewModel.getProfile()
    }

    private fun observe() {
        viewModel.isSuccess().observe(viewLifecycleOwner, Observer { resSuccess(it) })
        viewModel.isError().observe(viewLifecycleOwner, Observer { resError(it) })
        viewModel.isLoading().observe(viewLifecycleOwner, Observer { isLoading(it) })
    }

    private fun isLoading(it: Boolean?) {
        if (it == true) {
            binding.loadingProfile.visibility = View.VISIBLE
        } else if (it == false) {
            binding.loadingProfile.visibility = View.GONE
        }
    }

    private fun resError(it: Throwable?) {
        Toast.makeText(context, "${it?.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    private fun resSuccess(it: ResProfile?) {
        if (it?.datas != null) {
            for (i in it.datas.indices ?: 0..1) {
                var item = it.datas.get(i)
                getImageUser(item)
            }
        }
    }

    private fun getImageUser(item: DatasItem?) {
        var constant: Constant
        constant = Constant()


        Glide.with(this)
            .load("${constant.URL_IMAGE_USER + item?.photo}")
            .apply(RequestOptions().error(R.drawable.icon_nopic))
            .into(binding.circleImageView)

        binding.fullName.setText("${item?.firstName} ${item?.lastName}")
        binding.emailProfile.setText(item?.email)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}