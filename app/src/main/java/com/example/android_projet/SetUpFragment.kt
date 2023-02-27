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

    private lateinit var viewModel: MyViewModel

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
            val view = inflater.inflate(R.layout.fragment_set_up, container, false)
            val button = view.findViewById<Button>(R.id.lancer_partie)
            val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
            val switchAll: Switch = view.findViewById(R.id.idSelectAll)
            val switchEurope: Switch = view.findViewById(R.id.idEurope)
            val switchAsie: Switch = view.findViewById(R.id.idAsie)
            val switchAfrique: Switch = view.findViewById(R.id.idAfrique)
            val switchOceanie: Switch = view.findViewById(R.id.idOceanie)
            val switchAmeriqueNord: Switch = view.findViewById(R.id.idAmeriqueNord)
            val switchAmeriqueSud: Switch = view.findViewById(R.id.idAmeriqueSud)

            switchAll.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.setIsAfriqueOn(value = true)
            }
            switchEurope.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isEuropeOn = isChecked
            }
            switchAsie.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isAsieOn = isChecked
            }
            switchAfrique.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isAfriqueOn = isChecked
            }
            switchOceanie.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isOceanieOn = isChecked
            }
            switchAmeriqueNord.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isAmeriqueNordOn = isChecked
            }
            switchAmeriqueSud.setOnCheckedChangeListener { buttonView, isChecked ->
                viewModel.isAmeriqueSudOn = isChecked
            }


            button.setOnClickListener {
                viewModel.selectedRadioValue = when (radioGroup.checkedRadioButtonId) {
                    R.id.deux_solutions -> 2
                    R.id.trois_solutions -> 3
                    R.id.quatre_solutions -> 4
                    else -> 4
                }

                val action = SetUpFragmentDirections.actionSetUpFragment2ToFragmentPartie(
                    viewModel.selectedRadioValue,
                    viewModel.isEuropeOn,
                    viewModel.isAsieOn,
                    viewModel.isAfriqueOn,
                    viewModel.isOceanieOn,
                    viewModel.isAmeriqueNordOn,
                    viewModel.isAmeriqueSudOn
                )
                findNavController().navigate(action)
            }


            return view
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        val switchAfrica : Switch = view.findViewById(R.id.idAfrique)
        val switchNorthAmerica : Switch = view.findViewById(R.id.idAmeriqueNord)
        val switchSouthAmerica : Switch = view.findViewById(R.id.idAmeriqueSud)
        val switchEurope : Switch = view.findViewById(R.id.idEurope)
        val switchAsia : Switch = view.findViewById(R.id.idAsie)
        val switchOceania : Switch = view.findViewById(R.id.idOceanie)


        view.findViewById<Switch>(R.id.idSelectAll).setOnClickListener {
            val switchButton: Switch = view.findViewById(R.id.idSelectAll)

            switchButton.isChecked = switchAfrica.isChecked && switchNorthAmerica.isChecked && switchSouthAmerica.isChecked &&
                    switchEurope.isChecked && switchAsia.isChecked && switchOceania.isChecked

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


