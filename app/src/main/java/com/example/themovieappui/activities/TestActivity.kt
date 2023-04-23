package com.example.themovieappui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.themovieappui.R
import kotlinx.android.synthetic.main.activity_test.*
import org.json.JSONObject

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        val jsonData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "geojson",
                "raw", applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }

        val countryData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "country",
                "raw", applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }

        val countryOutput = JSONObject(countryData)
        val allData = countryOutput.getJSONArray("data")

        val outp = JSONObject(jsonData)
        val data = outp.getJSONObject("Data")
        val getGeo = data.getJSONArray("GeoData")

        val tsArr = arrayListOf<String>()
        for (i in 0 until getGeo.length()) {
            val dd = getGeo.getJSONObject(i).get("townShip_EN")

            tsArr.add(dd.toString())

        }

        for (i in tsArr) {
            // Log.d("@myList", i)
        }

        for (i in 0 until allData.length()) {
            val cc = allData.getJSONObject(i).get("name")
            edTownship.append(cc.toString())
            edTownship.append("\n")
            Log.d("@CC", cc.toString())
        }


//        for (i in 0 until tsArr.size) {
//            edTownship.append(tsArr[i])
//            edTownship.append("\n")
//        }

    }
}