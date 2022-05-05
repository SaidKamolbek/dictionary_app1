package com.example.mydictionary.ui.screens

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydictionary.R
import com.example.mydictionary.databinding.DashboardScreenBinding
import com.example.mydictionary.presenters.DashboardViewModel
import com.example.mydictionary.presenters.impl.DashboardViewModelImpl
import com.example.mydictionary.ui.adapters.CursorDashboardAdapter
import com.example.mydictionary.ui.adapters.DashboardAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardScreen : Fragment(R.layout.dashboard_screen) {

    private val viewModel: DashboardViewModel by viewModels<DashboardViewModelImpl>()
    private val binding: DashboardScreenBinding by viewBinding(DashboardScreenBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    //    private val adapter1 = CursorDashboardAdapter()
    private val adapter2 = DashboardAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        viewModel.loadWords()
        binding.recycler.adapter = adapter2
        viewModel.wordLiveData.observe(viewLifecycleOwner) {
            adapter2.submitList(it)
            Snackbar.make(binding.root, "${it.size} shuncha ", Snackbar.LENGTH_SHORT).show()
        }
        setSearchView()
//        viewModel.cursorWordsLiveData.observe(viewLifecycleOwner, cursorWordsObserver)
    }


    private fun setSearchView() {
        binding.apply {
            searchView.isSubmitButtonEnabled = true
            searchView.setOnQueryTextListener(obj)
        }
    }

    private val obj = object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            if (query != null) {
                viewModel.search(query).observe(viewLifecycleOwner) {
                    adapter2.submitList(it)
                }
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText != null) {
                viewModel.search(newText).observe(viewLifecycleOwner) {
                    adapter2.submitList(it)
                }
            }
            return true
        }

    }

    fun getWordsFromDatabaseByLetters(query: String) {
        viewModel.search(query)
    }


}