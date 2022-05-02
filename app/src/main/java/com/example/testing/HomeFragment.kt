package com.example.testing

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testing.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var pref : SharedPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener {
            saveInformationInSharedPreferebce()
        }
    }

    private fun saveInformationInSharedPreferebce() {
       pref= this.requireActivity().getSharedPreferences("myInformation", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("name", binding.editTextPersonName.text.toString())
        editor.putString("fatherName", binding.fatherName.text.toString())
        editor.putString("phone", binding.phone.text.toString())
        editor.putString("codePosti", binding.codePostal.text.toString())
        editor.putString("accountCount", binding.AccountCount.text.toString())
        editor.apply()
        editor.commit()
        Toast.makeText(activity, "your information was saved!" , Toast.LENGTH_SHORT ).show()
        findNavController().navigate(R.id.action_nav_home_to_nav_show_information )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}