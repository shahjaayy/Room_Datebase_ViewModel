package com.fascinate.roomdatabasepractice

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fascinate.roomdatabasepractice.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: UsersDatabase
    private lateinit var list: List<UsersEntity>
    private lateinit var myAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = UsersDatabase.getDatabase(applicationContext)

        binding.rcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
        }


    }

    fun save(view: View) {
        //We can use IO Dispatcher for write and while reading we can use withContext(Dispatcher.Main)
        GlobalScope.launch(Dispatchers.IO) {
            val check = database.userDao().insertUser(
                UsersEntity(
                    0,
                    binding.edtFirstName.text.toString(),
                    binding.edtLastName.text.toString(),
                    binding.edtEmail.text.toString(),
                    Date()
                )
            )

            Handler(Looper.getMainLooper()).post {
                Log.e("checked", "$check")
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

    fun showData(view: View) {
        //As it is the live data so it automatically runs on Background thread so we don't need coroutines
        //But i changed it as simple list so must use new thread

        GlobalScope.launch {
            //TODO("Background processing...")
            list = database.userDao().getAllUsers()
            withContext(Dispatchers.Main) {
                // TODO("Update UI here!")

                myAdapter = UsersAdapter(list)
                binding.rcv.adapter = myAdapter
            }
        }


    }
}