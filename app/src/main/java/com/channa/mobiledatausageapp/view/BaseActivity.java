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

    protected void showSnackBar(String message) {
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    protected void showActionSnackBar(String actionText, String message, View.OnClickListener listener) {
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionText, listener)
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                .show();
    }
}
