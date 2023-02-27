package com.example.android_projet

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.PictureDrawable
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.caverock.androidsvg.SVG
import com.example.android_projet.ApiClient.apiService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


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

    fun showFinalScoreDialog(nameCountry :String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.bonneReponse) + nameCountry +"\n" +  getString(R.string.score, viewModel.score) )
            //.setMessage(getString(R.string.score, viewModel.score))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.scoreBoard)) { _, _ ->
                viewModel.incrementScore(false)
                findNavController().navigate(R.id.action_fragment_partie_to_resultFragment)
            }
            .setPositiveButton(getString(R.string.play_again)) { _, _ ->
                viewModel.incrementScore(false)
                findNavController().navigate(R.id.action_fragment_partie_to_setUpFragment)
            }
            .show()
    }




    @SuppressLint("SuspiciousIndentation")
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





        val flagImageView = view.findViewById<ImageView>(R.id.flag)

        GlobalScope.launch(Dispatchers.IO) {
            try {

                val response = apiService.getFlags()
                val json = response.string()
                val jsonObject = JSONObject(json)
                val dataArray = jsonObject.getJSONArray("data")
                val data = Gson().fromJson(dataArray.toString(), Array<CountryData>::class.java)

                val randomCountry = data.random()
                val imageUrl = randomCountry.flag
                val nameCountry = randomCountry.name



                // load the SVG from the URL
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                val svg = SVG.getFromInputStream(input)

                // draw the SVG on a canvas
                val picture = svg.renderToPicture()
                val drawable = PictureDrawable(picture)

                // display the drawable in the image view
                activity?.runOnUiThread {
                    flagImageView.setImageDrawable(drawable)
                    for (i in 1..selectedRadioValue) {

                        val button = Button(requireContext())
                        if (i == goodPosition) {
                            Log.d("position bonne reponse", "la bonne reponse est en position $goodPosition")
                            button.text = nameCountry

                        }
                        else {
                            val randomCountryFalse = data.random()
                            val nameCountryFalse = randomCountryFalse.name
                                if  (nameCountryFalse==nameCountry){
                                val randomCountryFalse2 = data.random()
                                val nameCountryFalse2 = randomCountryFalse2.name
                                button.text =  nameCountryFalse2
                            }
                            else
                            button.text =  nameCountryFalse
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
                                showFinalScoreDialog(nameCountry)
                            }
                            else {

                                viewModel.incrementScore(true)
                                Log.d("score", "scoree = ${viewModel.score}")

                                findNavController().navigate(R.id.action_fragment_partie_self)



                            }
                        }
                        parentLayout.addView(button)


                    }
                }

            } catch (e: Exception) {
                Log.e("CountryFragment", "Error loading flag image: ${e.message}")
            }



        // Faire quelque chose avec la valeur
        Log.d("FragmentPartie", "selectedRadioValue = $selectedRadioValue")
        scoreDirect.text = "SCORE : ${viewModel.score}"

        Log.d("safe args", "switchValueEurope = ${viewModel.isEuropeOn}  ")
        Log.d("safe args", "switchValueAsie = ${viewModel.isAsieOn}")
        Log.d("safe args", "switchValueAfrique = ${viewModel.isAfriqueOn}  ")
        Log.d("safe args", "switchValueOceanie = ${viewModel.isOceanieOn} ")
        Log.d("safe args", "switchValueAmeriqueNord = ${viewModel.isAmeriqueNordOn}  ")
        Log.d("safe args", "switchValueAmeriqueSud = ${viewModel.isAmeriqueSudOn} ")


        }

        return view
    }


}



