package com.example.calculator

import android.icu.text.NumberFormat
import android.icu.text.NumberFormat.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener { calculateTip() }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun calculateTip() {

      val stringintextField=binding.costOfService.text.toString()
      val cost =stringintextField.toDoubleOrNull()
        if (cost==null){
            binding.result.text=" "
            Toast.makeText(this,"Please enter valid amount !",Toast.LENGTH_SHORT).show()
            return
        }
        val selectId=binding.tipOption.checkedRadioButtonId
        val percent=when(selectId){
            R.id.option20 -> 0.20
            R.id.option18 -> 0.18
            else -> 0.15
        }
        var tip=percent*cost
        val roundup=binding.roundUp.isChecked
        if(roundup){
        tip= kotlin.math.ceil(tip)}
        val formattedtip= NumberFormat.getCurrencyInstance().format(tip)
        binding.result.text=getString(R.string.tip_amount,formattedtip)






    }
}