package com.example.king.kidstime.mvp.View.TimeKeeping.Tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.king.kidstime.mvp.View.TimeKeeping.Fragment.TimeKeepingFragment;

/**
 * Created by KING on 03.01.2018.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter{

    private String[] tabs;
    private Fragment fragment = null;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{
                "Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                fragment = TimeKeepingFragment.newInstance(0);
                return fragment;
            }
            case 1:{
                fragment = TimeKeepingFragment.newInstance(1);
                return fragment;
            }
            case 2:{
                fragment = TimeKeepingFragment.newInstance(2);
                return fragment;
            }
            case 3:{
                fragment = TimeKeepingFragment.newInstance(3);
                return fragment;
            }
            case 4:{
                fragment = TimeKeepingFragment.newInstance(4);
                return fragment;
            }
            case 5:{
                fragment = TimeKeepingFragment.newInstance(5);
                return fragment;
            }
            case 6:{
                fragment = TimeKeepingFragment.newInstance(6);
                return fragment;
            }
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
