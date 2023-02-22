package com.example.android_projet

import android.os.Bundle
import android.util.Log
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
        val switchEurope: Switch = view.findViewById(R.id.idEurope)
        val switchAsie: Switch = view.findViewById(R.id.idAsie)
        val switchAfrique: Switch = view.findViewById(R.id.idAfrique)
        val switchOceanie: Switch = view.findViewById(R.id.idOceanie)
        val switchAmeriqueNord: Switch = view.findViewById(R.id.idAmeriqueNord)
        val switchAmeriqueSud: Switch = view.findViewById(R.id.idAmeriqueSud)

        switchEurope.setOnCheckedChangeListener { buttonView, isChecked ->
            // Do something when the switch state changes
        }
        switchAsie.setOnCheckedChangeListener { buttonView, isChecked ->
            // Do something when the switch state changes
        }
        switchAfrique.setOnCheckedChangeListener { buttonView, isChecked ->
            // Do something when the switch state changes
        }
        switchOceanie.setOnCheckedChangeListener { buttonView, isChecked ->
            // Do something when the switch state changes
        }
        switchAmeriqueNord.setOnCheckedChangeListener { buttonView, isChecked ->
            // Do something when the switch state changes
        }
        switchAmeriqueSud.setOnCheckedChangeListener { buttonView, isChecked ->
            // Do something when the switch state changes
        }


        button.setOnClickListener {
            val selectedRadioValue = when (radioGroup.checkedRadioButtonId) {
                R.id.une_solution -> 1
                R.id.deux_solutions -> 2
                R.id.trois_solutions -> 3
                R.id.quatre_solutions -> 4
                else -> 0
            }
            val isSwitchOnEurope = switchEurope.isChecked
            val isSwitchOnAsie = switchAsie.isChecked
            val isSwitchOnAfrique = switchAfrique.isChecked
            val isSwitchOnOceanie = switchOceanie.isChecked
            val isSwitchOnAmeriqueNord = switchAmeriqueNord.isChecked
            val isSwitchOnAmeriqueSud = switchAmeriqueSud.isChecked

            val action = SetUpFragmentDirections.actionSetUpFragment2ToFragmentPartie(selectedRadioValue, isSwitchOnEurope,isSwitchOnAsie,isSwitchOnAfrique,isSwitchOnOceanie,isSwitchOnAmeriqueNord,isSwitchOnAmeriqueSud)
            findNavController().navigate(action)
        }

        return view
    }

}

