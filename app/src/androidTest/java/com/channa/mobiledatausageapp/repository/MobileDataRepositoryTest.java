package com.channa.mobiledatausageapp.repository;

import android.content.Context;

import com.channa.mobiledatausageapp.data.response.DatastoreResponse;
import com.channa.mobiledatausageapp.network.APIClient;
import com.channa.mobiledatausageapp.network.action.OnDatastoreResponse;
import com.channa.mobiledatausageapp.view.MobileDataUsageActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class MobileDataRepositoryTest {

    Context context;

    @Before
    public void setUp() {
        ActivityScenario.launch(MobileDataUsageActivity.class);
        context = ApplicationProvider.getApplicationContext();
        assertNotNull(context);
    }

    @Test
    public void getYearlyMobileDataUsage() {
        APIClient apiClient = new APIClient(context);
        apiClient.getMobileDataUsage(new OnDatastoreResponse() {
            @Override
            public void onSuccessDatastoreResponse(DatastoreResponse datastoreResponse) {
                assertEquals(datastoreResponse.getSuccess(), true);
            }

            @Override
            public void onErrorResponse(Throwable e) {
                assertNotNull(e);
            }
        });
    }
}