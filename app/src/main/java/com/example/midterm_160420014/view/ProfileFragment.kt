package com.example.midterm_160420014.view


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.midterm_160420014.R
import com.example.midterm_160420014.databinding.FragmentProfileBinding
import com.example.midterm_160420014.model.Users
import com.example.midterm_160420014.viewModel.UserViewModel
import com.google.android.material.textfield.TextInputEditText
class ProfileFragment : Fragment(), ProfileOnClickListener,LogoutListener,TopUpListener {

    private lateinit var userVM:UserViewModel
    private lateinit var dataBinding:FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this)[UserViewModel::class.java]
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        userVM.getUser(sharedPref.getString("uuid","")!!.toInt())
        dataBinding.profileListener=this
        dataBinding.logoutListener=this
        dataBinding.topupListener=this
        observe()
    }
    private fun observe(){
        userVM.userData.observe(viewLifecycleOwner, Observer {
            dataBinding.user=it
        })
    }
    private fun checkStatus(nominal:TextInputEditText){
        userVM.updateStatus.observe(viewLifecycleOwner, Observer {
            if(it==true){
                Toast.makeText(context,"Top up success!!",Toast.LENGTH_SHORT).show()
                nominal.setText("0")
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater,R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onProfileClick(v: View) {
        val action=ProfileFragmentDirections.actionItemProfileToEditProfileFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onDeactivateClick(v: View,user: Users) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder.setTitle("Warning!!!")
            .setMessage("Are you sure you want to deactivate your account?")
            .setPositiveButton("DEACTIVATE") { dialog, _ ->
            userVM.deactivateAccount(user)
            onClickLogout(v)
            dialog.dismiss()
        }
            .setNegativeButton("NO") { dialog, _ -> dialog.dismiss()}
            .show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(Color.RED)
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE)
    }

    override fun onClickTopup(v: View) {
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val nominal = view?.findViewById<TextInputEditText>(R.id.editTextSaldo)
        if (nominal?.text.toString() != "" && nominal?.text.toString().toInt()>0) {
            userVM.updateSaldo(nominal?.text.toString().toInt(),sharedPref.getString("uuid","")!!.toInt())
            checkStatus(nominal!!)
        }
        else{
            Toast.makeText(context,"Nominal must be a number and greater than 0!!",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClickLogout(v: View) {
        val sharedPref = requireActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor=sharedPref.edit()
        editor.clear()
        editor.apply()
        findNavController().popBackStack(findNavController().graph.startDestinationId,true)
        val navController = Navigation.findNavController(requireActivity(),R.id.fragment_host)
        navController.popBackStack(navController.graph.startDestinationId,false)
        navController.navigate(R.id.loginFragment)

        activity?.recreate()
    }
}