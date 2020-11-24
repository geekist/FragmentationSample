package com.jon.fragmentationsample;

import android.app.Application;

import androidx.annotation.NonNull;

import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(@NonNull Exception e) {
                            //
                    }
                })
                .install();


    }
}

