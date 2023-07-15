package com.example.midterm_160420014.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.R
import com.example.midterm_160420014.viewModel.ListReviewViewModel
import com.example.midterm_160420014.viewModel.UserViewModel

class ReviewFragment : Fragment() {
    private lateinit var reviewVM: ListReviewViewModel
    private lateinit var userVM: UserViewModel
    private val reviewAdapter = ReviewListAdapter(arrayListOf())
    fun observe(){
        userVM.userData.observe(viewLifecycleOwner, Observer {user->
            reviewVM.reviewList.observe(viewLifecycleOwner, Observer {
                Log.d("Content: ",it.toString())
                reviewAdapter.updatereviewList(it,user)
            })
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE)
        val id = ReviewFragmentArgs.fromBundle(requireArguments()).id

        reviewVM = ViewModelProvider(this)[ListReviewViewModel::class.java]
        userVM = ViewModelProvider(this)[UserViewModel::class.java]
//        sharedPref.getString("id","")?.let {
//            userVM.refresh(it)
//            reviewVM.refreshData(id, it)
//        }

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager= LinearLayoutManager(context)
        recView.adapter=reviewAdapter
        observe()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }
}