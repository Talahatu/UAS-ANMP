package com.example.midterm_160420014.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.FragmentReviewBinding
import com.example.midterm_160420014.model.Menus
import com.example.midterm_160420014.model.Restaurants
import com.example.midterm_160420014.viewModel.ListReviewViewModel
import com.example.midterm_160420014.viewModel.RestoDetailViewModel
import com.example.midterm_160420014.viewModel.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ReviewFragment : Fragment(), AddReviewListener {
    private lateinit var reviewVM: ListReviewViewModel
    private lateinit var restoVM:RestoDetailViewModel
    private lateinit var userVM: UserViewModel
    private lateinit var databinding:FragmentReviewBinding
    private val reviewAdapter = ReviewListAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ids = ReviewFragmentArgs.fromBundle(requireArguments())
        reviewVM = ViewModelProvider(this)[ListReviewViewModel::class.java]
        restoVM = ViewModelProvider(this)[RestoDetailViewModel::class.java]
        reviewVM.viewReview(ids.restoId,ids.menuId)
        userVM = ViewModelProvider(this)[UserViewModel::class.java]
        userVM.addAllUserData()
        databinding.reviewListener=this
        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager= LinearLayoutManager(context)
        recView.adapter=reviewAdapter
        observe()
    }
    fun observe(){
        Log.d("MASUK: ","Masuk")
        reviewVM.reviewList.observe(viewLifecycleOwner, Observer {
            Log.d("Review: ",it.toString())
            userVM.allUserData.observe(viewLifecycleOwner, Observer {user->
                Log.d("User: ", user.toString() )
                reviewAdapter.updatereviewList(it,user)
            })
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding = DataBindingUtil.inflate<FragmentReviewBinding>(inflater,R.layout.fragment_review, container, false)
        return databinding.root
    }
    override fun onAddReviewClick(v: View) {
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        var userid=sharedPref.getString("uuid","")
        val ids = ReviewFragmentArgs.fromBundle(requireArguments())
        val txtComment = view?.findViewById<EditText>(R.id.txtComment)?.text.toString()
        reviewVM.addReview(userid!!.toInt(),ids.menuId,ids.restoId,txtComment)
        Toast.makeText(context,"Comment added", Toast.LENGTH_SHORT).show()
        reviewVM.viewReview(ids.restoId,ids.menuId)
        userVM.addAllUserData()
        observe()
    }
}