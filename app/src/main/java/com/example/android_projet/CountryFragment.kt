package com.example.android_projet


import android.graphics.drawable.PictureDrawable
import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

import com.caverock.androidsvg.SVG

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

import org.json.JSONObject

import java.net.HttpURLConnection
import java.net.URL

class CountryFragment : Fragment() {

    private val apiService = ApiClient.apiService
    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bind the view
        val flagImageView = view.findViewById<ImageView>(R.id.flag)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                // make API call and parse the response using Gson
                val response = apiService.getFlags()
                val json = response.string()
                val jsonObject = JSONObject(json)
                val dataArray = jsonObject.getJSONArray("data")
                val data = Gson().fromJson(dataArray.toString(), Array<CountryDataClass>::class.java)

                // get the flag URL for France
                val fr = data.firstOrNull { it.iso2 == "FR" || it.iso3 == "FRA" }
                val imageUrl = fr?.flag

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
                }

            } catch (e: Exception) {
                Log.e("CountryFragment", "Error loading flag image: ${e.message}")
            }
        }
    }
}