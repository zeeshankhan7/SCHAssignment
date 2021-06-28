package com.zak.datauses.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.zak.datauses.MyApplication
import com.zak.datauses.R
import com.zak.datauses.adapters.MobileDataAdapter
import com.zak.datauses.data.model.YearListWrapper
import com.zak.datauses.utilities.Utils.checkInternetConnection
import com.zak.datauses.viewmodel.MobileDataUsageViewModel
import javax.inject.Inject

class MobileDataUsageActivity : BaseActivity() {
    @BindView(R.id.rvMobileDataUsage)
    var recyclerViewMobileDataUsage: RecyclerView? = null

    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null
    var mobileDataUsageViewModel: MobileDataUsageViewModel? = null
    private var mobileDataAdapter: MobileDataAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_consumption)
        (application as MyApplication).getApplicationComponent()!!.inject(this)
        recyclerViewMobileDataUsage=findViewById(R.id.rvMobileDataUsage)
        initViews()
    }

    override fun initViews() {
        super.initViews()
        initMobileDataUsageRecyclerView(this)
        mobileDataUsage
        if (!checkInternetConnection(this)) {
            showErrorActionSnackBar("No Internet Connection")
        }
    }

    private fun initMobileDataUsageRecyclerView(context: Context) {
        recyclerViewMobileDataUsage!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerViewMobileDataUsage!!.layoutManager = layoutManager
        mobileDataAdapter = MobileDataAdapter(context)
        recyclerViewMobileDataUsage!!.adapter = mobileDataAdapter
        mobileDataUsageViewModel = ViewModelProviders.of(this, viewModelFactory).get(MobileDataUsageViewModel::class.java)
    }

    private val mobileDataUsage: Unit
        private get() {
            mobileDataUsageViewModel!!.yearlyMobileDataUsage.observe(this, Observer<YearListWrapper> { yearListWrapper: YearListWrapper ->
                if (null != yearListWrapper.getYearList()) {
                    mobileDataAdapter!!.setYearList(yearListWrapper.getYearList())
                    mobileDataAdapter!!.notifyDataSetChanged()
                } else {
                    showErrorActionSnackBar(yearListWrapper.getError())
                    Log.e(TAG, "getMobileDataUsage: " + yearListWrapper.getError())
                }
            })
        }

    companion object {
        private const val TAG = "MobileDataUsageActivity"
    }
}