package com.example.android_projet

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import java.security.SecureRandom
import kotlin.random.Random


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
    private var random: SecureRandom? = null
    private val shakeAnimation: Animation? = null
    private val quizConstraintLayout: ConstraintLayout? = null
    private val questionNumberTextView: TextView? = null
    private val flagImageView: ImageView? = null
    private lateinit var selectedButton: TextView

    @SuppressLint("MissingInflatedId", "ServiceCast")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val randomNumber = (1..100).random()
        Log.d("aleatoire", "nombre aleatoire = $randomNumber")
        // Récupérer la valeur passée comme argument
        val view = inflater.inflate(R.layout.fragment_partie, container, false)
        selectedButton = view.findViewById(R.id.selectedButton)
        val selectedRadioValue = arguments?.getInt("selectedRadioValue", 0) ?: 0

        val parentLayout = view.findViewById<LinearLayout>(R.id.LinearLayout)
        val goodPosition = (1..selectedRadioValue).random()
        for (i in 1..selectedRadioValue) {

            val button = Button(requireContext())
            if (i == goodPosition) {
                Log.d("TAG", "la bonne reponse est en position $goodPosition")
                button.text = "Bonne position"
            }
            else {
                button.text = "Button $i"
            }
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            button.setOnClickListener {
                if (i != goodPosition) {
                    // Faire trembler l'écran si le bouton cliqué est le 1, le 2 ou le 3
                    val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        vibrator.vibrate(500)
                    }
                }
            }
            parentLayout.addView(button)
        }

        // Faire quelque chose avec la valeur
        Log.d("FragmentPartie", "selectedRadioValue = $selectedRadioValue")
        selectedButton.text = "Selected Button: $selectedRadioValue"



        return view
    }

}