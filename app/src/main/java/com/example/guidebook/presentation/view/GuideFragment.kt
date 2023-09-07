package com.example.guidebook.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guidebook.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guidebook.domain.util.DataConverter
import com.example.guidebook.domain.util.Resource
import com.example.guidebook.presentation.viewmodel.MainActivityViewModel

class GuideFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = GuideRecyclerAdapter()
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var progressBar: ProgressBar
    private val dataConverter = DataConverter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.guideRecycler)
        progressBar = view.findViewById(R.id.progressBar)
        setUpRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        viewModel = (activity as MainActivity).viewModel
        observeData()
        viewModel.getData()
    }

    private fun setUpRecyclerView(){
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun observeData(){
        viewModel.dataLiveData.observe(viewLifecycleOwner, Observer {resource ->
            when(resource){
                is Resource.Success ->{
                    progressBar.visibility = View.GONE
                    resource.data?.let {responseData ->
                        adapter.list.submitList(responseData.data)
                        responseData.data.forEach {data->
                            viewModel.addDataDb(dataConverter.toDataDB(data))
                        }
                    }
                }

                is Resource.Error ->{
                    progressBar.visibility = View.GONE
                    Log.i("err", resource.message.toString())
                }

                is Resource.Loading ->{
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }
}