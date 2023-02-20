package com.example.android_projet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
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
class setUpFragment : Fragment() {
    private var selectedButton = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_up, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.lancer_partie).setOnClickListener {
            findNavController().navigate(R.id.action_setUpFragment2_to_fragment_partie)
        }

        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedButton = checkedId
        }
    }
    interface OnRadioButtonSelectedListener {
        fun onRadioButtonSelected(selectedButton: Int)
    }

    fun setOnRadioButtonSelectedListener(listener: OnRadioButtonSelectedListener) {
        // set listener to radio button selection events
        val radioGroup = view?.findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup?.setOnCheckedChangeListener { _, checkedId ->
            selectedButton = checkedId
            listener.onRadioButtonSelected(selectedButton)
        }
    }
}
