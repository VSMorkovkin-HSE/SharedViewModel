package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OrderViewModel : ViewModel() {
    // Fields
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int>
        get() = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String>
        get() = _flavor

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double>
        get() = _price

    val dateOptions = getPickupOptions()

    init {
        resetOrder()
    }

    // Methods
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }


    fun hasNoFlavorSet() = _flavor.value.isNullOrEmpty()

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>() // list with dates
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault()) // used to format dates to corresponding format and locale
        val calendar = Calendar.getInstance() // get current date and time

        options.add(formatter.format(calendar.time)) // add current date to list
        // add three days after current to list
        val availableDatesAfterCurrent = 3
        repeat(availableDatesAfterCurrent) {
            calendar.add(Calendar.DATE, 1)
            options.add(formatter.format(calendar.time))
        }

        return options
    }


}