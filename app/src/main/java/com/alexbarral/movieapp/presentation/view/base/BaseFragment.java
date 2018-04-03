package com.alexbarral.movieapp.presentation.view.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alexbarral.movieapp.injection.HasComponent;

import butterknife.ButterKnife;

/**
 * Created by alejandrobarral on 3/4/18.
 */

public abstract class BaseFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(getLayoutId(), container, false);
        bindViews(fragmentView);
        initView(savedInstanceState);
        return fragmentView;
    }


    protected abstract void injectComponent();

    /**
     *
     * @param fragmentView
     */
    private void bindViews(View fragmentView) {
        ButterKnife.bind(this, fragmentView);
    }


    /**
     * Use this to initialize view components.
     * @param savedInstanceState
     */
    public void initView(Bundle savedInstanceState) {
    }


    /**
     * @return The layout id for the fragment.
     */
    protected abstract int getLayoutId();

    protected void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection
     */
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getcomponent());
    }
}
