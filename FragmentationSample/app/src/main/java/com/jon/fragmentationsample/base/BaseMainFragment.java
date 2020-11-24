package com.jon.fragmentationsample.base;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.jon.fragmentationsample.R;

public class BaseMainFragment extends MySupportFragment{
    protected OnFragmentOpenDrawerListener mOpenDrawerListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentOpenDrawerListener) {
            mOpenDrawerListener = (OnFragmentOpenDrawerListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentOpenDrawerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOpenDrawerListener = null;
    }

    protected void initToolbarNav(Toolbar toolbar) {
        initToolbarNav(toolbar, false);
    }

    protected void initToolbarNav(Toolbar toolbar, boolean isHome) {
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenDrawerListener != null) {
                    mOpenDrawerListener.onOpenDrawer();
                }
            }
        });
    }


    public interface OnFragmentOpenDrawerListener {
        void onOpenDrawer();
    }
}
