package com.nhapt.base.fragment;

import com.nhapt.base.R;
import com.nhapt.base.databinding.FragmentSplashBinding;
import com.nhapt.base.mvvm.MVVMFragment;
import com.nhapt.base.navigator.SplashNavigator;
import com.nhapt.base.view_model.SplashViewModel;

public class SplashFragment extends MVVMFragment<SplashViewModel, FragmentSplashBinding> implements SplashNavigator {
    @Override
    protected int layoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected Class modelClass() {
        return SplashViewModel.class;
    }

}
