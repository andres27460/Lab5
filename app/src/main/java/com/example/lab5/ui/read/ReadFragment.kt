package com.example.lab5.ui.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lab5.DeudoresApp
import com.example.lab5.R
import com.example.lab5.data.dao.DebtorDao
import com.example.lab5.data.entities.Debtor
import com.example.lab5.databinding.FragmentReadBinding
class ReadFragment : Fragment() {

    private lateinit var readViewModel: ReadViewModel
    private var _binding: FragmentReadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        readViewModel = ViewModelProvider(this).get(ReadViewModel::class.java)
        _binding = FragmentReadBinding.inflate(inflater,container,false)
        val root: View = binding.root
       // val textView: TextView = binding.textNotifications
        readViewModel.text.observe(viewLifecycleOwner, Observer {
         //   textView.text = it
        })

        binding.readButton.setOnClickListener{
            readDebtors(binding.nameEditText.text.toString())
        }

        return root
    }

    private fun readDebtors(name: String) {
        val debtorDao: DebtorDao = DeudoresApp.database.DebtorDao()
        val debtor: Debtor = debtorDao.readDebtor(name)
        if(debtor != null){
            binding.phoneTextView.text = getString(R.string.phone_value, debtor.phone)
            binding.amountTextView.text = getString(R.string.amount_value, debtor.amount.toString())
        }
        else{
            Toast.makeText(requireContext(), "No Existe", Toast.LENGTH_SHORT).show()
            binding.phoneTextView.setText("")
            binding.amountTextView.setText("")

        }
    }
}