package com.alexbarral.movieapp.presentation.view.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alexbarral.movieapp.App;
import com.alexbarral.movieapp.R;
import com.alexbarral.movieapp.injection.component.AppComponent;
import com.alexbarral.movieapp.injection.module.ActivityModule;
import com.alexbarral.movieapp.presentation.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getAppComponent().inject(this);

        setContentView(getLayoutId());
        bindViews();
        initView(savedInstanceState);
    }

    /**
     * @return default layout for activities
     */
    protected int getLayoutId(){
        return R.layout.activity_layout;
    };

    /**
     *
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }


    /**
     * Use this to initialize view components.
     * @param savedInstanceState
     */
    public void initView(Bundle savedInstanceState) {
    }



    /**
     * Add a Fragment to its activity
     * @param containerViewId The container
     * @param fragment the fragment to be added
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     *
     * @param message The message to be shown
     */
    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}