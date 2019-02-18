package com.channa.mobiledatausageapp.repository;

import android.util.Log;

import com.channa.mobiledatausageapp.data.model.Quarter;
import com.channa.mobiledatausageapp.data.model.Year;
import com.channa.mobiledatausageapp.data.response.DatastoreResponse;
import com.channa.mobiledatausageapp.data.response.QuarterResponse;
import com.channa.mobiledatausageapp.network.APIClient;
import com.channa.mobiledatausageapp.network.action.OnDatastoreResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MobileDataRepository {

    private static final String TAG = "MobileDataRepository";

    APIClient apiClient;
    private List<Year> yearList = new ArrayList<>();

    private List<Quarter> quarterList = new ArrayList<>();
    private MutableLiveData<List<Year>> mutableYearList = new MutableLiveData<>();

    @Inject
    public MobileDataRepository(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    public LiveData<List<Year>> getYearlyMobileDataUsage() {
        apiClient.getMobileDataUsage(new OnDatastoreResponse() {
            @Override
            public void onSuccessDatastoreResponse(DatastoreResponse datastoreResponse) {

                for (QuarterResponse q : datastoreResponse.getResult().getRecords()) {
                    Log.d(TAG, "Quarter: " + q.get_id() + " : " + q.getQuarter());

                    String quarterInfo[] = q.getQuarter().split("-");
                    String year = quarterInfo[0];
                    String quarterName = quarterInfo[1];

                    quarterList.add(new Quarter(q.get_id(), q.getVolume_of_mobile_data(), Integer.parseInt(year), quarterName));
                }
                mutableYearList.setValue(yearList);
            }

            @Override
            public void onErrorResponse(Throwable e) {

            }
        });

        return mutableYearList;

    }

}
