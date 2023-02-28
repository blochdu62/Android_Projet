package com.example.android_projet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [setUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetUpFragment : Fragment() {

    private lateinit var viewModel: MyViewModel

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
            val view = inflater.inflate(R.layout.fragment_set_up, container, false)
            val button = view.findViewById<Button>(R.id.buttonGo)
            val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)

            val switchEurope: Switch = view.findViewById(R.id.idEurope)
            val switchAsie: Switch = view.findViewById(R.id.idAsia)
            val switchAfrique: Switch = view.findViewById(R.id.idAfrica)
            val switchOceanie: Switch = view.findViewById(R.id.idOceania)
            val switchAmeriqueNord: Switch = view.findViewById(R.id.idNorthAmerica)
            val switchAmeriqueSud: Switch = view.findViewById(R.id.idSouthAmerica)

            switchEurope.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isEuropeOn = isChecked
            }
            switchAsie.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isAsiaOn = isChecked
            }
            switchAfrique.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isAfricaOn = isChecked
            }
            switchOceanie.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isOceaniaOn = isChecked
            }
            switchAmeriqueNord.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isNorthAmericaOn = isChecked
            }
            switchAmeriqueSud.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isSouthAmericaOn = isChecked
            }


            button.setOnClickListener {
                viewModel.selectedRadioValue = when (radioGroup.checkedRadioButtonId) {
                    R.id.radioButtonTwoChoices -> 2
                    R.id.radioButtonThreeChoices -> 3
                    R.id.radioButtonFourChoices -> 4
                    else -> 4
                }

                val action = SetUpFragmentDirections.actionSetUpFragment2ToFragmentPartie(
                    viewModel.selectedRadioValue,
                    viewModel.isEuropeOn,
                    viewModel.isAsiaOn,
                    viewModel.isAfricaOn,
                    viewModel.isOceaniaOn,
                    viewModel.isNorthAmericaOn,
                    viewModel.isSouthAmericaOn
                )
                findNavController().navigate(action)
            }


            return view
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        val switchAfrica : Switch = view.findViewById(R.id.idAfrica)
        val switchNorthAmerica : Switch = view.findViewById(R.id.idNorthAmerica)
        val switchSouthAmerica : Switch = view.findViewById(R.id.idSouthAmerica)
        val switchEurope : Switch = view.findViewById(R.id.idEurope)
        val switchAsia : Switch = view.findViewById(R.id.idAsia)
        val switchOceania : Switch = view.findViewById(R.id.idOceania)


        view.findViewById<Switch>(R.id.idSelectAll).setOnClickListener {
            val switchButton: Switch = view.findViewById(R.id.idSelectAll)

            if (switchButton.isChecked) {
                switchAfrica.isChecked = true
                switchNorthAmerica.isChecked = true
                switchSouthAmerica.isChecked = true
                switchEurope.isChecked = true
                switchAsia.isChecked = true
                switchOceania.isChecked = true
            } else {
                switchAfrica.isChecked = false
                switchNorthAmerica.isChecked = false
                switchSouthAmerica.isChecked = false
                switchEurope.isChecked = false
                switchAsia.isChecked = false
                switchOceania.isChecked = false
            }
        }

    }

    }


