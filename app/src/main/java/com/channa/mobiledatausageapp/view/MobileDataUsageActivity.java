package com.channa.mobiledatausageapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.channa.mobiledatausageapp.MyApplication;
import com.channa.mobiledatausageapp.R;
import com.channa.mobiledatausageapp.adapter.MobileDataAdapter;
import com.channa.mobiledatausageapp.utility.Utils;
import com.channa.mobiledatausageapp.viewmodel.MobileDataUsageViewModel;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class MobileDataUsageActivity extends BaseActivity {

    private static final String TAG = "MobileDataUsageActivity";

    @BindView(R.id.rvMobileDataUsage)
    RecyclerView recyclerViewMobileDataUsage;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    MobileDataUsageViewModel mobileDataUsageViewModel;
    private MobileDataAdapter mobileDataAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_data_usage);

        ((MyApplication) getApplication()).getApplicationComponent().inject(this);

        initViews();
    }

    @Override
    protected void initViews() {
        super.initViews();

        if (!Utils.checkInternetConnection(this)) {
            showActionSnackBar("Retry", "No Internet Connection", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initViews();
                }
            });
        } else {
            initMobileDataUsageRecyclerView(this);
        }


    }

    private void initMobileDataUsageRecyclerView(Context context) {
        recyclerViewMobileDataUsage.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewMobileDataUsage.setLayoutManager(layoutManager);
        mobileDataAdapter = new MobileDataAdapter(context);
        recyclerViewMobileDataUsage.setAdapter(mobileDataAdapter);

        mobileDataUsageViewModel = ViewModelProviders.of(this, viewModelFactory).get(MobileDataUsageViewModel.class);
        mobileDataUsageViewModel.getYearlyMobileDataUsage().observe(this, yearList -> {
            mobileDataAdapter.setYearList(yearList);
            mobileDataAdapter.notifyDataSetChanged();
        });
    }
}
