package com.example.lab5.ui.create

import android.icu.number.NumberFormatter.with
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lab5.DeudoresApp
import com.example.lab5.R
import com.example.lab5.data.dao.DebtorDao
import com.example.lab5.data.entities.Debtor
import com.example.lab5.databinding.FragmentCreateBinding
import kotlinx.android.synthetic.main.fragment_create.*
import java.sql.Types.NULL

class CreateFragment : Fragment() {

    private lateinit var createViewModel: CreateViewModel
    private var _binding: FragmentCreateBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        createViewModel = ViewModelProvider(this).get(CreateViewModel::class.java)
        _binding = FragmentCreateBinding.inflate(inflater,container,false)
        val root = binding.root
        //val textView: TextView = binding.textDashboard
        createViewModel.text.observe(viewLifecycleOwner, Observer {
         //   textView.text = it
        })

        binding.createButton.setOnClickListener(){
            val name = name_edit_text.text.toString()
            val phone = phone_edit_text.text.toString()
            val amount = amount_edit_text.text.toString().toLong()

            createDebtor(name, phone, amount)
        }

        return root
    }

    private fun createDebtor(name: String, phone: String, amount: Long) {
        val debtor = Debtor(id = NULL, name = name, phone = phone, amount = amount)
        val debtorDAO : DebtorDao = DeudoresApp.database.DebtorDao()
        debtorDAO.createDebtor(debtor)
        cleanViews()
    }

    private fun cleanViews() {
        binding.nameEditText.setText("")
        binding.phoneEditText.setText("")
        binding.amountEditText.setText("")
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null

    }
}