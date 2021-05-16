package com.star.war.ui.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.star.war.R
import com.star.war.databinding.FragmentDetailsBinding
import com.star.war.repo.model.DetailResponse
import com.star.war.repo.model.FilmResponse
import com.star.war.ui.base.BaseFragment
import com.star.war.ui.helper.ObservableData
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailFragment : BaseFragment() {

    override val fragmentLayoutResId: Int
        get() = R.layout.fragment_details

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            (it as FragmentDetailsBinding).vm = viewModel
        }
        img_back.setOnClickListener { activity?.onBackPressed() }
        init()
        fetchDetailsAndFilmData()
    }

    private fun init() {
        val searchCharacter = arguments?.let {
            DetailFragmentArgs.fromBundle(it).searchCharacter
        } ?: throw IllegalArgumentException("Search Character can't be null")
        viewModel.init(searchCharacter)
    }

    private fun fetchDetailsAndFilmData() {
        viewModel.getDetails().observeWithFragment(this, detailListener, false)
        viewModel.getFilms().observeWithFragment(this, filmListener)
    }

    private val detailListener = Observer<ObservableData<DetailResponse, Throwable>> {
        //DO NOTHING - Handled in VM
    }

    private val filmListener = Observer<ObservableData<List<FilmResponse>, Throwable>> {
        if(it.hasError()) {
            handleApiError(it.error)
        }
    }
}