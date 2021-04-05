package com.example.carinspection.screens.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.carinspection.R
import com.example.carinspection.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        binding.errorText.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel=viewModel

        binding.setLifecycleOwner(this)

        viewModel.authorized.observe(viewLifecycleOwner, Observer {authorized->
            if(authorized){
                binding.errorText.visibility= View.GONE
                findNavController(this).navigate(R.id.action_loginFragment_to_carListFragment)
                viewModel.signedIn()
            }

        })

        viewModel.failed.observe(viewLifecycleOwner, Observer {failed->
            if(failed){
                binding.errorText.visibility= View.VISIBLE
            }

        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
                view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

}