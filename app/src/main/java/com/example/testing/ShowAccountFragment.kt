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
            if(viewModel.getAllAccount()?.size!=0){
                account = viewModel.getAllAccount()?.get(it)
                initView(account!!)
            }else{
                binding.NextButton.isEnabled=false
            }
        }

        val nextButtonObserver= Observer<Boolean> {
            binding.NextButton.isEnabled=it
        }

        val prevButtonObserver= Observer<Boolean> {
            binding.PrevButton.isEnabled=it
        }

        viewModel.prevButton.observe(viewLifecycleOwner,prevButtonObserver)
        viewModel.nextButtonOnShowAccount.observe(viewLifecycleOwner,nextButtonObserver)
        viewModel.numberUserDateAccount.observe(viewLifecycleOwner,numberData)


    }
   private fun initView(accountEntity: AccountEntity){
        binding.creditNumber.text = accountEntity.cardNumber
        binding.balance.text = accountEntity.accBalance
        binding.accountType.text = accountEntity.accType.toString()
    }


}