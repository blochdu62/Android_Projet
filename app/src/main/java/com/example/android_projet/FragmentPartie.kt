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
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

    //private lateinit var viewModel: MyViewModel
    private val viewModel: MyViewModel by viewModels()


    private var random: SecureRandom? = null
    private val shakeAnimation: Animation? = null
    private val quizConstraintLayout: ConstraintLayout? = null
    private val questionNumberTextView: TextView? = null
    private val flagImageView: ImageView? = null
    private lateinit var selectedButton: TextView

    //@SuppressLint("MissingInflatedId", "ServiceCast")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Désactiver le bouton retour physique
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Ne rien faire pour empêcher le retour en arrière
            }
        })

        var randomNumber = (1..100).random()
        Log.d("aleatoire", "nombre aleatoire = $randomNumber")

        // Récupérer la valeur passée comme
        val view = inflater.inflate(R.layout.fragment_partie, container, false)

       // viewModel  = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

         val scoreView = viewModel.score.toString()


        val selectedRadioValue = viewModel.selectedRadioValue
        val isEuropeOn = viewModel.isEuropeOn
        val isAsieOn = viewModel.isAsieOn
        val isAfriqueOn = viewModel.isAfriqueOn
        val isOceanieOn = viewModel.isOceanieOn
        val isAmeriqueNordOn = viewModel.isAmeriqueNordOn
        val isAmeriqueSudOn = viewModel.isAmeriqueSudOn

        selectedButton = view.findViewById(R.id.selectedButton)

        val args = FragmentPartieArgs.fromBundle(requireArguments())

       // val isSwitchOnEurope = args.selectedEurope
        //val isSwitchOnAsie = args.selectedAsie
        //val isSwitchOnAfrique = args.selectedAfrique
        //val isSwitchOnOceanie = args.selectedOceanie
        //val isSwitchOnAmeriqueNord = args.selectedAmeriqueNord
        //val isSwitchOnAmeriqueSud = args.selectedAmeriqueSud





      //  var selectedRadioValue = arguments?.getInt("selectedRadioValue", 0) ?: 0


        val parentLayout = view.findViewById<LinearLayout>(R.id.LinearLayout)
        Log.d("TTTTTTTTTTTTTTTQQQQQQQQQQQQQQQQQQQTT", "selectedRadioValue = ${viewModel.selectedRadioValue}")
        Log.d("FragmentPartie", "selectedRadioValue = $selectedRadioValue")
        var goodPosition = (1..selectedRadioValue).random()
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
                    val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        vibrator.vibrate(500)

                    }
                    findNavController().navigate(R.id.action_fragment_partie_to_resultatFragment)
                }
                else {

                    viewModel.incrementScore(true)
                    Log.d("score", "scoree = $scoreView")
                    findNavController().navigate(R.id.action_fragment_partie_self)
                }
            }
            parentLayout.addView(button)


        }

        // Faire quelque chose avec la valeur
        Log.d("FragmentPartie", "selectedRadioValue = $selectedRadioValue")
        selectedButton.text = "Selected Button: $selectedRadioValue"

        Log.d("safe args", "switchValueEurope = $isEuropeOn  ")
        Log.d("safe args", "switchValueAsie = $isAsieOn ")
        Log.d("safe args", "switchValueAfrique = $isAfriqueOn  ")
        Log.d("safe args", "switchValueOceanie = $isOceanieOn ")
        Log.d("safe args", "switchValueAmeriqueNord = $isAmeriqueNordOn  ")
        Log.d("safe args", "switchValueAmeriqueSud = $isAmeriqueSudOn ")

        return view
    }

}