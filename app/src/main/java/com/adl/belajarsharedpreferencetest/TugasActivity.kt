package com.adl.belajarsharedpreferencetest

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tugas.*

class TugasActivity : AppCompatActivity() {

    var sliderValue:Int = 0
    var groupCombo:Int = 0
    var switchComp:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tugas)
        val sharedPreference = getSharedPreferences("tugas_activity_setting", Context.MODE_PRIVATE)


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                txtSlider.setText("Nilainya adalah : ${p1.toString()}")
                sliderValue = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                var result = when(p1){
                    R.id.radioButton -> "Option 1"
                    R.id.radioButton2 -> "Option 2"
                    R.id.radioButton3 -> "Option 3"
                    else -> "No Option"
                }
                Toast.makeText(this@TugasActivity, "Pilijannya adalah ${result}",Toast.LENGTH_LONG).show()
                groupCombo = p1
            }

        })

        switch1.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                Toast.makeText(this@TugasActivity, "nilainya adalah ${p1}",Toast.LENGTH_LONG).show()
                switch1.setText(p1.toString())
                switchComp = p1
            }

        })
/*
        switch1.setOnCheckedChangeListener { button, onSwitch ->
           if (onSwitch)
               switch1.setText("OFF")
           else
               switch1.setText("ON")
        }
*/

        btnSave.setOnClickListener({
                var editor = sharedPreference.edit()
                editor.putInt("slider",sliderValue)
                editor.putInt("combo",groupCombo)
                editor.putBoolean("switch",switchComp)
                editor.putString("text",txtInput.text.toString())
                editor.commit()
        })
        loadSave(sharedPreference)
    }

    private fun loadSave(sharedPreference:SharedPreferences){
        sliderValue = sharedPreference.getInt("slider",0)
        groupCombo = sharedPreference.getInt("combo",0)
        switchComp = sharedPreference.getBoolean("switch",false)
        txtInput.setText(sharedPreference.getString("text",""))

        seekBar.setProgress(sliderValue)
        radioGroup.check(groupCombo)
        switch1.setChecked(switchComp)
    }
}