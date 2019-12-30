package com.example.exercise4

import android.app.*
import android.os.Bundle
import android.widget.TextView
import android.widget.DatePicker
import java.text.DateFormat
import java.util.Calendar


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var calendar:Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Initialize a calendar instance
        calendar = Calendar.getInstance()

        // Get the system current date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        // Initialize a new date picker dialog and return it
        return DatePickerDialog(
            activity, // Context
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, // Theme
            this, // DatePickerDialog.OnDateSetListener
            year, // Year
            month, // Month of year
            day // Day of month
        )
    }


    // When date set and press ok button in date picker dialog
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val text1: TextView = activity.findViewById(R.id.saving_view)
        val text2: TextView = activity.findViewById(R.id.transfer_view)
        // Display the selected date in text view
        activity.findViewById<TextView>(R.id.text_view).text = String.format("Date of birth: " + formatDate(year,month,day))
        activity.findViewById<TextView>(R.id.age_view).text = String.format("Age: "+ getAge(year,month,day))
        val num = Integer.parseInt(getAge(year, month, day))
        val result = when(num){
            in 0..15 -> "age too low"
            in 16..20 -> "Min Saving Amount(RM): 5000"
            in 21..25 -> "Min Saving Amount(RM): 14000"
            in 26..30 -> "Min Saving Amount(RM): 29000"
            in 31..35 -> "Min Saving Amount(RM): 50000"
            in 36..40 -> "Min Saving Amount(RM): 78000"
            in 41..45 -> "Min Saving Amount(RM): 116000"
            in 46..50 -> "Min Saving Amount(RM): 165000"
            in 51..55 -> "Min Saving Amount(RM): 228000"
            else -> "No Record"
        }
        val take = when(num){
            in 16..20 -> "Can take saving(30%)(RM): 1500"
            in 21..25 -> "Can take saving(30%)(RM): 4200"
            in 26..30 -> "Can take saving(30%)(RM): 8700"
            in 31..35 -> "Can take saving(30%)(RM): 15000"
            in 36..40 -> "Can take saving(30%)(RM): 23400"
            in 41..45 -> "Can take saving(30%)(RM): 34800"
            in 46..50 -> "Can take saving(30%)(RM): 49500"
            in 51..55 -> "Can take saving(30%)(RM): 68400"
            else -> "No"
        }
        text1.setText(result)
        text2.setText(take)
    }

    // Custom method to format date
    private fun formatDate(year:Int, month:Int, day:Int):String{
        // Create a Date variable/object with user chosen date
        calendar.set(year, month, day, 0, 0, 0)
        val chosenDate = calendar.time
        // Format the date picker selected date
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return df.format(chosenDate)
    }
    private fun getAge(year:Int, month:Int, day:Int):String {
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        dob.set(year, month, day)
        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
        {
            age--
        }
        val ageInt = age
        val ageS = ageInt.toString()

        return ageS
    }
}