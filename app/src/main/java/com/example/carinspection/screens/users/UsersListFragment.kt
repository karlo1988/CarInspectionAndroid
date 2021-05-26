package com.example.carinspection.screens.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.carinspection.R
import com.example.carinspection.database.CarInspectionDatabase
import com.example.carinspection.databinding.FragmentLoginBinding
import com.example.carinspection.databinding.FragmentUsersListBinding


class UsersListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentUsersListBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_users_list, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CarInspectionDatabase.getInstance(application).carInspectionDao
        val viewModelFactory = UsersListViewModelFactory(dataSource, application)
        val usersListViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(UsersListViewModel::class.java)

        binding.usersListViewModel = usersListViewModel

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
                item,
                view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

}