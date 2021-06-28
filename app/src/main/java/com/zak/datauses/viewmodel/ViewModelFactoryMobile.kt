package com.zak.datauses.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zak.datauses.repository.MobileDataRepository

class ViewModelFactoryMobile(private val mobileDataRepository: MobileDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MobileDataUsageViewModel::class.java)) {
            MobileDataUsageViewModel(mobileDataRepository) as T
        } else {
            Log.e(TAG, "Invalid ViewModel Type")
            throw IllegalArgumentException("Invalid ViewModel Type")
        }
    }

    companion object {
        private const val TAG = "CustomViewModelFactory"
    }
}