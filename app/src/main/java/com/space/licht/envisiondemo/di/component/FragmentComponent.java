package com.space.licht.envisiondemo.di.component;

import android.app.Activity;

import com.space.licht.envisiondemo.di.module.FragmentModule;
import com.space.licht.envisiondemo.di.scope.FragmentScope;
import com.space.licht.envisiondemo.ui.fragment.classification.MemberFragment;
import com.space.licht.envisiondemo.ui.fragment.discover.DiscoverFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DiscoverFragment dailyFragment);

    void inject(MemberFragment dailyFragment);

}
