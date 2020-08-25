package com.velasquez.drinksapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.velasquez.drinksapp.R
import com.velasquez.drinksapp.data.DataSource
import com.velasquez.drinksapp.data.model.Drink
import com.velasquez.drinksapp.domain.repository.DrinksRepositoryImpl
import com.velasquez.drinksapp.domain.vo.OperationResult
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainAdapter.OnDrinkClickListener {

//    private var _binding: FragmentMainBinding? = null
//    private val binding
//        get() = _binding!!

    private val viewModel by viewModels<MainViewModel> { VMFactory(DrinksRepositoryImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupObservers()

    }

    private fun setupObservers(){
        viewModel.fetchDrinksList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is OperationResult.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is OperationResult.Data -> {
                    progressBar.visibility = View.GONE
                    rv_drinks.adapter = MainAdapter(requireContext(), result.data, this)
                }
                is OperationResult.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "An error has occurred while fetching the data", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupSearchView(){
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setDrink(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onDrinkClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment_to_drinksDetailsFragment,bundle)
    }

    private fun setupRecyclerView(){
        rv_drinks.layoutManager = LinearLayoutManager(requireContext())
        rv_drinks.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

}