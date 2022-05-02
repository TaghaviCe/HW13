package com.example.testing

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testing.databinding.FragmentShowInformationBinding


class ShowInformationFragment : Fragment() {

    private var _binding: FragmentShowInformationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShowInformationBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences =requireActivity().getSharedPreferences("myInformation", MODE_PRIVATE)
        val usernameValue = sharedPreferences.getString("name", "")
        val fatherName=sharedPreferences.getString("fatherName", "")
        val phone=sharedPreferences.getString("phone", "")
        val codePosti=sharedPreferences.getString("codePosti", "")
        val account=sharedPreferences.getString("accountCount", "")
        binding.editTextPersonName.setText("نام و نام خانوادگی:  "+usernameValue)
        binding.fatherName.setText("اسم پدر:  "+fatherName)
        binding.phone.setText("تلفن:  "+phone)
        binding.codePostal.setText("کد پستی:  "+codePosti)
        binding.accountCount.setText("تعداد حساب: "+account)
        binding.buttonChange.setOnClickListener {
            findNavController().navigate(R.id.action_nav_show_information_to_nav_home)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}