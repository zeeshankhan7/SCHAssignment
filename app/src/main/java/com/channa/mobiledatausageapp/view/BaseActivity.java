package com.channa.mobiledatausageapp.view;

import android.view.View;

import com.channa.mobiledatausageapp.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.parentLayout)
    View parentLayout;

    protected void initViews() {
        ButterKnife.bind(this);
    }

    protected void showErrorActionSnackBar(String message) {
        final Snackbar snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        }).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();
    }
}
