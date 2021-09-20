package com.example.lab5.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab5.DeudoresApp
import com.example.lab5.R
import com.example.lab5.data.dao.DebtorDao
import com.example.lab5.data.entities.Debtor
import com.example.lab5.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var  debtorsAdapter: DebtorsAdapter

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        _binding = FragmentListBinding.inflate(inflater,container,false)
        val root: View = binding.root
       // val textView: TextView = binding.textHome
        listViewModel.text.observe(viewLifecycleOwner, Observer {
           // textView.text = it
        })

        debtorsAdapter = DebtorsAdapter(onItemClicked = { onDebtorItemClicked(it) })
        binding.debtorRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = debtorsAdapter
            setHasFixedSize(false)
        }

        val debtorDAO : DebtorDao = DeudoresApp.database.DebtorDao()
        val listDebtors: MutableList<Debtor> = debtorDAO.getDebtors()
        debtorsAdapter.appendItems(listDebtors)
        return root
    }

    private fun onDebtorItemClicked(debtor: Debtor) {
        findNavController().navigate(ListFragmentDirections.actionNavigationListToDetailFragment2(debtor = debtor))

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}