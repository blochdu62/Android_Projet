package com.example.android_projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val selectedButton: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

       // menu?.getItem(2)?.setEnabled(false)
       // menu?.getItem(3)?.isVisible = false

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var selectedOption = ""

        when(item?.itemId){


            R.id.help -> selectedOption ="Help"
            R.id.Jouer -> selectedOption ="Jouer"
            R.id.Quitter -> selectedOption ="Quitter"
            R.id.Infos -> selectedOption ="Infos"
        }

        Toast.makeText(this,"Option : " + selectedOption, Toast.LENGTH_SHORT).show()

        return super.onOptionsItemSelected(item)
    }

}