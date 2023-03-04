package com.example.practicaltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practicaltask.adapters.DataChangingAdapter
import com.example.practicaltask.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), DataChangingAdapter.OnItemClickListener {

    val numbers = arrayOf(2, 3, 4, 5, 6, 7, 8, 9)

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: DataChangingAdapter

    var itemArray: ArrayList<Int> = ArrayList()

    var generatedNumber: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // access the spinner

        setUpSpinner()


        setUpOnClicks()


    }

    private fun setUpOnClicks() {


        binding.generate.setOnClickListener() {

            generateNumber(itemArray.size - 1)

        }
    }

    private fun generateNumber(limit: Int) {

        var myNumber: Int = -1

        myNumber = Random.nextInt(1, itemArray.size + 1)

        if (myNumber == generatedNumber) {

            generateNumber(limit)
        } else {
            generatedNumber = myNumber
            adapter.changeGeneratedButton(myNumber)
            binding.generate.isEnabled = false
        }
    }

    private fun setUpSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, numbers
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {


                    setUpRecyclerView(numbers[position])
                    binding.generate.isEnabled = true

                }

                override fun onNothingSelected(parent: AdapterView<*>) {


                }
            }
        }
    }

    private fun setUpRecyclerView(totalNumbers: Int) {

        itemArray.clear()

        var totalItems = (totalNumbers * totalNumbers)
        for (i in 1..totalItems) {
            itemArray.add(i)
        }

        var gridLayoutManager = GridLayoutManager(this, totalNumbers)
        binding.recyclerView.layoutManager = gridLayoutManager
        adapter = DataChangingAdapter(this, itemArray, this)
        binding.recyclerView.adapter = adapter

    }

    override fun onItemClick() {
        binding.generate.isEnabled = true
    }
}