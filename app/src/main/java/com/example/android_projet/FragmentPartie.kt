package com.example.android_projet

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_partie.newInstance] factory method to
 * create an instance of this fragment.
 */



    class FragmentPartie : Fragment() {
    private lateinit var selectedButton: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Récupérer la valeur passée comme argument
        val view = inflater.inflate(R.layout.fragment_partie, container, false)
        selectedButton = view.findViewById(R.id.selectedButton)
        val selectedRadioValue = arguments?.getInt("selectedRadioValue", 0) ?: 0

        val parentLayout = view.findViewById<LinearLayout>(R.id.LinearLayout)

        for (i in 1..selectedRadioValue) {
            val button = Button(requireContext())
            button.text = "Button $i"
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            parentLayout.addView(button)
        }

        // Faire quelque chose avec la valeur
        Log.d("FragmentPartie", "selectedRadioValue = $selectedRadioValue")
        selectedButton.text = "Selected Button: $selectedRadioValue"

        return view
    }

}