package com.example.wellnessapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find buttons by the use of their ID's
        val HealthButton = findViewById<Button>(R.id.health_recepies)
        val NutritionButton = findViewById<Button>(R.id.nutrition_advice)
        val MeditationButton = findViewById<Button>(R.id.meditation)
        val HydrationButton = findViewById<Button>(R.id.hydration_alert)
        val ExerciseButton = findViewById<Button>(R.id.start_exercise)
        val MotivationButton = findViewById<Button>(R.id.daily_motivarion)
        val WeeklyGoalsButton = findViewById<Button>(R.id.weekly_goals)
        val ProgressButton = findViewById<Button>(R.id.check_progress)
        val LearnMoreButton = findViewById<Button>(R.id.lernmore)
//        set on click listeners for each button
        HealthButton.setOnClickListener {
            val intent = Intent(applicationContext, HealthActivity::class.java)
            startActivity(intent)

            showInterstitialAd()
        }
        NutritionButton.setOnClickListener {
            val intent = Intent(applicationContext, NutritionActivity::class.java)
            startActivity(intent)
        }
        MeditationButton.setOnClickListener {
            val intent = Intent(applicationContext, MeditationActivity::class.java)
            startActivity(intent)
        }
        HydrationButton.setOnClickListener {
            val intent = Intent(applicationContext, HydrationActivity::class.java)
            startActivity(intent)
        }
        ExerciseButton.setOnClickListener {
            val intent = Intent(applicationContext, ExerciseActivity::class.java)
            startActivity(intent)
        }
        MotivationButton.setOnClickListener {
            val intent = Intent(applicationContext, MotivationActivity::class.java)
            startActivity(intent)
        }
        WeeklyGoalsButton.setOnClickListener {
            val intent = Intent(applicationContext, WeeklyGoalsActivity::class.java)
            startActivity(intent)
        }
        ProgressButton.setOnClickListener {
            val intent = Intent(applicationContext, ProgressActivity::class.java)
            startActivity(intent)
        }
        //below is the implicit intent, when the button learn more is clicked, it takes us to a default bowser
        LearnMoreButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse("https://mostfitphysiocenter.com/7-important-tips-for-keeping-fit-and-staying-healthy/")
            startActivity(intent)
        }

//        Below is the implementation of the banner ads
        MobileAds.initialize(this)
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

    }
    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        //Requests interstitial ads
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }
    //Function checks if ad already running not to run anothet one and overlap - which is wrong
    fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }
}