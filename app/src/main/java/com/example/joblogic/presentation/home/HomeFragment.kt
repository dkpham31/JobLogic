package com.example.joblogic.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.joblogic.databinding.FragmentHomeBinding
import com.example.joblogic.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.joblogic.R
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listType.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_homeFragment_to_listFragment)
        }
    }
}