package com.anmp.habittracker_malasngoding_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.anmp.habittracker_malasngoding_anmp.databinding.FragmentLoginBinding
import com.anmp.habittracker_malasngoding_anmp.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            viewModel.Login(binding.txtUsername.text.toString(), binding.txtPassword.text.toString())
        }

        viewModel.loginStatus.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                val action = LoginFragmentDirections.actionDashboardFragment()
                Navigation.findNavController(view).navigate(action)
            } else {
                Toast.makeText(context, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}