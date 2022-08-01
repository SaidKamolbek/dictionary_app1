package com.example.mydictionary.ui.screens

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mydictionary.R
import com.example.mydictionary.data.model.WordModel
import com.example.mydictionary.databinding.DashboardScreenBinding
import com.example.mydictionary.databinding.WordScreenBinding
import com.example.mydictionary.presenters.DashboardViewModel
import com.example.mydictionary.presenters.impl.DashboardViewModelImpl
import com.example.mydictionary.ui.adapters.CursorDashboardAdapter
import com.example.mydictionary.ui.adapters.DashboardAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardScreen : Fragment(R.layout.dashboard_screen) {

    private val viewModel: DashboardViewModel by viewModels<DashboardViewModelImpl>()
    private val binding: DashboardScreenBinding by viewBinding(DashboardScreenBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    private val adapter1 = CursorDashboardAdapter()
//    private val adapter2 = DashboardAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadWords()
        binding.recycler.adapter = adapter1
        viewModel.cursorWordsLiveData.observe(viewLifecycleOwner) {
            adapter1.submitCursor(it)
        }
        adapter1.setOnItemClickListener {
            openBottomSheetDialog(it)
        }

        setSearchView()

    }

    private fun openBottomSheetDialog(wordModel: WordModel) {
        val dialog = BottomSheetDialog(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.word_screen, null)
        view.findViewById<TextView>(R.id.word_name).text = wordModel.word
        view.findViewById<TextView>(R.id.word_definition).text = wordModel.definition
        view.findViewById<TextView>(R.id.word_synonym).text = wordModel.synonym
        view.findViewById<TextView>(R.id.word_antonym).text = wordModel.antonym
        view.findViewById<TextView>(R.id.word_example).text = wordModel.example

        dialog.setContentView(view)
        dialog.show()
    }

    private fun setSearchView() {
        binding.apply {
            searchView.isSubmitButtonEnabled = true
            searchView.setOnQueryTextListener(obj)
        }
    }

    private val obj = object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            getCursor(query.toString())
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            getCursor(newText.toString())
            return true
        }

    }

    private fun getCursor(txt: String) {
        viewModel.search(txt)
    }


}