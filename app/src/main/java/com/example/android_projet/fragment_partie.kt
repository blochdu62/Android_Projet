package com.example.android_projet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_partie.newInstance] factory method to
 * create an instance of this fragment.
 */

class fragment_partie : Fragment() {
    private var selectedButton = -1
    private lateinit var test: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_partie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        test = view.findViewById(R.id.test)
        val selectedButtonTextView = view.findViewById<TextView>(R.id.test)

        // Afficher la valeur de selectedButton
        selectedButtonTextView.text = selectedButton.toString()

        // Register as the listener for radio button selection events
        val listener = activity as? setUpFragment.OnRadioButtonSelectedListener
        listener?.let {
            // If the listener exists, set it as the listener for radio button events
            val firstFragment = activity?.supportFragmentManager?.findFragmentById(R.id.setUpFragment2) as? setUpFragment
            firstFragment?.setOnRadioButtonSelectedListener(it)
        }

        //selectedButton = (activity as? setUpFragment)?.test ?: -1
        selectedButtonTextView.text = selectedButton.toString()
    }


}
