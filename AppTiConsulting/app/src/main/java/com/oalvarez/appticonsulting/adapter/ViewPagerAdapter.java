package com.oalvarez.appticonsulting.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by oalvarez on 21/12/2016.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> listFragment = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void agregarFragmento(Fragment fragment){
        listFragment.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}
