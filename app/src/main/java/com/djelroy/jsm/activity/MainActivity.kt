package com.djelroy.jsm.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.djelroy.jsm.R
import com.djelroy.jsm.data.Application
import com.djelroy.jsm.view.application.ApplicationListAdapter
import com.djelroy.jsm.view.application.ApplicationViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;




class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewLayoutManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: ApplicationListAdapter
    private lateinit var applicationViewModel: ApplicationViewModel
    private lateinit var fab: FloatingActionButton
    private lateinit var adView: AdView

    companion object {
        const val newApplicationActivityRequestCode = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMobileAds();

        viewLayoutManager = LinearLayoutManager(this)
        viewAdapter = ApplicationListAdapter(this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewLayoutManager
            adapter= viewAdapter
        }

        applicationViewModel = ViewModelProviders.of(this).get(ApplicationViewModel::class.java)
        applicationViewModel.allApplications.observe(this, Observer { applications ->
            applications?.let { viewAdapter.setApplications(it) }
        })

        fab = findViewById<FloatingActionButton>(R.id.fab_new_application)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewApplicationActivity::class.java)
            startActivityForResult(intent, newApplicationActivityRequestCode)
        }
    }

    fun initMobileAds(){
        MobileAds.initialize(this) {}
        adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newApplicationActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val application = Application(
                        it.getStringExtra(NewApplicationActivity.EXTRA_APPLICATION_TITLE),
                        it.getStringExtra(NewApplicationActivity.EXTRA_APPLICATION_COMPANY),
                        it.getStringExtra(NewApplicationActivity.EXTRA_APPLICATION_DESCRIPTION))

                applicationViewModel.insert(application)
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.application_not_saved,
                    Toast.LENGTH_LONG).show()
        }
    }
}
