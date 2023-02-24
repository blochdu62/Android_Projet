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
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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


    private lateinit var viewModel: MyViewModel

    private lateinit var scoreDirect: TextView

    fun showFinalScoreDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.score, viewModel.score))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ ->
                viewModel.incrementScore(false)
                findNavController().navigate(R.id.action_fragment_partie_to_mainFragment)
            }
            .setPositiveButton(getString(R.string.play_again)) { _, _ ->
                viewModel.incrementScore(false)
                findNavController().navigate(R.id.action_fragment_partie_to_setUpFragment)
            }
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        // Désactiver le bouton retour physique
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Ne rien faire pour empêcher le retour en arrière
            }
        })

        var randomNumber = (1..100).random()
        Log.d("aleatoire", "nombre aleatoire = $randomNumber")

        val view = inflater.inflate(R.layout.fragment_partie, container, false)
         val scoreView = viewModel.score.toString()
        val selectedRadioValue = viewModel.selectedRadioValue
        val isEuropeOn = viewModel.isEuropeOn
        val isAsieOn = viewModel.isAsieOn
        val isAfriqueOn = viewModel.isAfriqueOn
        val isOceanieOn = viewModel.isOceanieOn
        val isAmeriqueNordOn = viewModel.isAmeriqueNordOn
        val isAmeriqueSudOn = viewModel.isAmeriqueSudOn


        scoreDirect = view.findViewById(R.id.scoreDirect)


        val parentLayout = view.findViewById<LinearLayout>(R.id.LinearLayout)
        Log.d("nombre de reponses", "selectedRadioValue = ${viewModel.selectedRadioValue}")
        var goodPosition = (1..selectedRadioValue).random()
        for (i in 1..selectedRadioValue) {

            val button = Button(requireContext())
            if (i == goodPosition) {
                Log.d("position bonne reponse", "la bonne reponse est en position $goodPosition")
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
                    showFinalScoreDialog()
                }
                else {

                    viewModel.incrementScore(true)
                    Log.d("score", "scoree = ${viewModel.score}")

                    findNavController().navigate(R.id.action_fragment_partie_self)



                }
            }
            parentLayout.addView(button)


        }

        // Faire quelque chose avec la valeur
        Log.d("FragmentPartie", "selectedRadioValue = $selectedRadioValue")
        scoreDirect.text = "votre score est de: ${viewModel.score}"

        Log.d("safe args", "switchValueEurope = ${viewModel.isEuropeOn}  ")
        Log.d("safe args", "switchValueAsie = ${viewModel.isAsieOn}")
        Log.d("safe args", "switchValueAfrique = ${viewModel.isAfriqueOn}  ")
        Log.d("safe args", "switchValueOceanie = ${viewModel.isOceanieOn} ")
        Log.d("safe args", "switchValueAmeriqueNord = ${viewModel.isAmeriqueNordOn}  ")
        Log.d("safe args", "switchValueAmeriqueSud = ${viewModel.isAmeriqueSudOn} ")

        return view
    }

}