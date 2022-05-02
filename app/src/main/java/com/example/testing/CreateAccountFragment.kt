package com.example.testing

import android.accounts.Account
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testing.databinding.FragmentCreateAccountBinding



class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    var creditCard=""
    var userBalance=""
    val viewModel: MainViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences =requireActivity().getSharedPreferences("myInformation",
            Context.MODE_PRIVATE
        )

        var accountCount= sharedPreferences.getString("accountCount", "")?.toInt()
        binding.SaveButton.setOnClickListener {
            creditCard = binding.CreditCardNumber.text.toString()
            userBalance = binding.AccountBalance.text.toString()
            var AccountType: AccountType =
                if (binding.radioButtonLong.isChecked) {
                    AccountType.Long
                } else {
                    AccountType.Short
                }
            var addAccount = AccountEntity(creditCard, userBalance, AccountType)
            viewModel.saveInformation(addAccount)
            viewModel.saveButton()
        }

        binding.NextButton.setOnClickListener {
            viewModel.nextButtonCreateAcccount()
            clearInformation()
        }

        val buttonSaveObservable=Observer<Boolean>{
            binding.SaveButton.isEnabled=it
        }
        val buttonNextObserver= Observer<Boolean> {
            binding.NextButton.isEnabled=it
        }
        val numberAccountObserver= Observer<Int> {
            binding.accountCount.setText(it.toString())
            if(it==accountCount){
                findNavController().navigate(R.id.action_nav_create_account_to_nav_show_account)
            }
        }
        viewModel.saveButtonEnabled.observe(viewLifecycleOwner,buttonSaveObservable)
        viewModel.nextButtonEnabled.observe(viewLifecycleOwner,buttonNextObserver)
        viewModel.numberUserDateAccount.observe(viewLifecycleOwner,numberAccountObserver)



    }

    private fun clearInformation() {
        binding.CreditCardNumber.setText("")
        binding.AccountBalance.setText("")
        binding.radioButtonLong.isChecked=false
        binding.radioButtonShort.isChecked=false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}