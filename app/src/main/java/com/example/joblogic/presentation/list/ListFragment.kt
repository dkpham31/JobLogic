package com.example.joblogic.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.joblogic.R
import com.example.joblogic.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.UnsupportedOperationException
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.joblogic.presentation.ListType
import com.example.joblogic.presentation.MainViewModel
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.view.ViewGroup

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: BasisAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
        setupBackPress()
        setupRecyclerView()
        subscribeData()
    }

    private fun setupToolBar() {
        binding.tvTitle.text = when (viewModel.listType.value) {
            ListType.CALL -> getString(R.string.call_list)
            ListType.BUY -> getString(R.string.buy_list)
            ListType.SELL -> getString(R.string.sell_list)
            else -> throw UnsupportedOperationException("unsupported")
        }

        binding.toolbarLayout.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    private fun subscribeData() {
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT).show()
        }

        viewModel.listData.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressCircular.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setupRecyclerView() {
        adapter = BasisAdapter()
        val linearLayoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        val dividerItemDecoration =
            DividerItemDecoration(this.context, linearLayoutManager.orientation)

        with(binding.recyclerView) {
            this.adapter = this@ListFragment.adapter
            layoutManager = linearLayoutManager
            addItemDecoration(dividerItemDecoration)
        }
    }

}