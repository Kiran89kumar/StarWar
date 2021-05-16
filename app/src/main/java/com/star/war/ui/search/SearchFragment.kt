package com.star.war.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.star.war.R
import com.star.war.databinding.FragmentSearchBinding
import com.star.war.repo.model.SearchCharacter
import com.star.war.repo.model.SearchResponse
import com.star.war.ui.base.BaseFragment
import com.star.war.ui.helper.ObservableData
import com.star.war.ui.helper.OnItemClickListener
import com.star.war.ui.helper.RxSearchObservable
import com.star.war.utils.async
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : BaseFragment(), OnItemClickListener<SearchCharacter> {

    override val fragmentLayoutResId: Int
        get() = R.layout.fragment_search

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            (it as FragmentSearchBinding).vm = viewModel
        }
        initSearchListener()
    }

    private fun initSearchListener() {
        viewModel.addDisposable {
            RxSearchObservable.fromView(sv_character)
                .startWith("")
                .debounce ( 300, TimeUnit.MILLISECONDS )
                .distinctUntilChanged()
                .async()
                .subscribe {
                    performSearch(it)
                }
        }
    }

    private fun performSearch(query: String) {
        if(viewModel.searchQuery != query) {
            viewModel.searchLiveData.removeObserver(searchListener)
            viewModel.searchCharacter(query).observeWithFragment(this, searchListener)
        }
    }

    private val searchListener = Observer<ObservableData<SearchResponse, Throwable>> {
        if(it.hasError()) {
            closeKeyboard()
            handleApiError(it.error)
        } // Data case is handled in VM
    }

    override fun onItemClicked(item: SearchCharacter) {
        closeKeyboard()
        val action = SearchFragmentDirections.showDetailsAction(item)
        Navigation.findNavController(frg_search).navigate(action)
    }
}