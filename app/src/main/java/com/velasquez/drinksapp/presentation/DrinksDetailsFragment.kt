package com.velasquez.drinksapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.velasquez.drinksapp.R
import com.velasquez.drinksapp.data.model.Drink
import kotlinx.android.synthetic.main.fragment_drinks_details.*

class DrinksDetailsFragment : Fragment() {

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drinks_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).into(drink_img)
        drink_title.text = drink.name
        drink_description.text = drink.description
        if (drink.hasAlcohol == "Non_Alcoholic"){
            drink_has_alcohol.text = "No alcohol"
        } else {
            drink_has_alcohol.text = "Has alcohol"
        }
    }

}