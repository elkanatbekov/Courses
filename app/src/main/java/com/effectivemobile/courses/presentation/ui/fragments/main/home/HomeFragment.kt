package com.effectivemobile.courses.presentation.ui.fragments.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.effectivemobile.courses.R
import com.effectivemobile.courses.databinding.FragmentHomeBinding
import com.effectivemobile.courses.presentation.adapters.CourseAdapter
import dev.androidbroadcast.vbpd.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModel()
    private val adapter: CourseAdapter by lazy {
        CourseAdapter { course ->
            viewModel.onBookmarkClicked(course)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.rvCourses.adapter = adapter
    }

    private fun setupListeners() {
        binding.sortContainer.setOnClickListener {
            viewModel.sortCoursesByDate()
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.courses.collect { state ->
                    when (state) {
                        is HomeState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.rvCourses.isVisible = false
                        }

                        is HomeState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.rvCourses.isVisible = true

                            adapter.submitList(state.courses)
                        }

                        is HomeState.Error -> {
                            binding.progressBar.isVisible = false

                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }
}