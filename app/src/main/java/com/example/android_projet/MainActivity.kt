package com.example.android_projet

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.*

class MainActivity : AppCompatActivity() {
    val selectedButton: Int? = null
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val rv: RecyclerView = findViewById(R.id.rv)
        //rv.layoutManager = LinearLayoutManager(this)
        //rv.adapter = ListAdapter(IntRange(0, 100).toList())
    }
    private val client = OkHttpClient()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var selectedOption = ""

        when(item?.itemId) {
            R.id.help -> { selectedOption ="Help"
                showHelpDialog()
            }
            R.id.Infos ->{ selectedOption ="Infos"
                showInfosDialog()
            }

        }

        Toast.makeText(this,"Option : " + selectedOption, Toast.LENGTH_SHORT).show()

        return super.onOptionsItemSelected(item)
    }
    private fun showInfosDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.infos))
            .setMessage(getString(R.string.infoApps))
            .show()
    }
    private fun showHelpDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.help))
            .setMessage(getString(R.string.helpText))
            .show()
    }
}