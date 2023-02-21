package com.example.android_projet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController






// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [setUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_set_up, container, false)

        val button = view.findViewById<Button>(R.id.lancer_partie)
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)

        button.setOnClickListener {
            val selectedRadioValue = when (radioGroup.checkedRadioButtonId) {
                R.id.une_solution -> 1
                R.id.deux_solutions -> 2
                R.id.trois_solutions -> 3
                R.id.quatre_solutions -> 4
                else -> 0
            }

            val action = SetUpFragmentDirections.actionSetUpFragment2ToFragmentPartie(selectedRadioValue)
            findNavController().navigate(action)
        }

        return view
    }

}

