package com.example.testing

import android.accounts.Account
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.testing.databinding.FragmentShowAccountBinding
import java.util.*


class ShowAccountFragment : Fragment() {
    private var _binding: FragmentShowAccountBinding? = null
    val viewModel: MainViewModel by viewModels()
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var account:AccountEntity?
        binding.NextButton.setOnClickListener {
            viewModel.nextButtonShowAccount()
        }
        binding.PrevButton.setOnClickListener {
            viewModel.prevButtonShowAccount()
        }
        val numberData=Observer<Int>{
            if(viewModel.getAllAccount().size!=0){
                account = viewModel.getAccount(it)
                initView(account!!)
            }else{
                binding.NextButton.isEnabled=false
            }
        }
        viewModel.numberUserDateAccount.observe(viewLifecycleOwner,numberData)


    }
    fun initView(accountEntity: AccountEntity){
        binding.creditNumber.setText(accountEntity.cardNumber)
        binding.balance.setText(accountEntity.accBalance)
        binding.accountType.setText(accountEntity.accType.toString())
    }


}