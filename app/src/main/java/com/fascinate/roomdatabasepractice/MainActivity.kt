package com.fascinate.roomdatabasepractice

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fascinate.roomdatabasepractice.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModelMainActivity
    private lateinit var myAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            myAdapter = UsersAdapter()
            binding.rcv.adapter = myAdapter
        }

        viewModel = ViewModelProvider(this).get(ViewModelMainActivity::class.java)
        viewModel.getAllUsersObservers().observe(this) {
            myAdapter.setData(ArrayList(it))
            myAdapter.notifyDataSetChanged()
        }


    }

    fun save(view: View) {
        //We can use IO Dispatcher for write and while reading we can use withContext(Dispatcher.Main)
        GlobalScope.launch(Dispatchers.IO) {
            val check = viewModel.insertUser(
                UsersEntity(
                    0,
                    binding.edtFirstName.text.toString(),
                    binding.edtLastName.text.toString(),
                    binding.edtEmail.text.toString(),
                    Date()
                )
            )

            Handler(Looper.getMainLooper()).post {
                if(check > 0)
                {
                    Toast.makeText(applicationContext, "Data inserted...", Toast.LENGTH_SHORT).show()
                    binding.edtEmail.setText("")
                    binding.edtDatePicker.setText("")
                    binding.edtFirstName.setText("")
                    binding.edtLastName.setText("")
                }
                else
                    Toast.makeText(applicationContext, "Data failed to insert...", Toast.LENGTH_SHORT).show()

            }


        }


    }

    //fun update(view: View) {}

    fun setDate(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this@MainActivity, { _, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            binding.edtDatePicker.setText("$dayOfMonth $monthOfYear, $year")

        }, year, month, day)

        dpd.show()


    }


}