package com.zak.datauses.viewmodel

import com.zak.datauses.repository.MobileDataRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.zak.datauses.data.model.YearListWrapper

class MobileDataUsageViewModel(var mobileDataRepository: MobileDataRepository) : ViewModel() {
    val yearlyMobileDataUsage: LiveData<YearListWrapper>
        get() = mobileDataRepository.yearlyMobileDataUsage
}