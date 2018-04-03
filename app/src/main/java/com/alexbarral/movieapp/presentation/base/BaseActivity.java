package com.alexbarral.movieapp.presentation.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        bindViews();
        initView();
    }

    /**
     *
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }


    /**
     * Use this to initialize view components.
     */
    public void initView() {
    }


    /**
     * @return The layout id for the activity.
     */
    protected abstract int getLayoutId();
}