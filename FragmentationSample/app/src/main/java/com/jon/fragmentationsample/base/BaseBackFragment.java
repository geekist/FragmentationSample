package com.jon.fragmentationsample.base;

import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jon.fragmentationsample.R;

public class BaseBackFragment extends MySupportFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
