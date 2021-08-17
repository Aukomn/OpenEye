package com.aukomn.myopeneye.ui.notifications.main.main;

import android.content.Context;

import com.aukomn.myopeneye.bean.Tabs;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.MutableLiveData;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private ArrayList<Tabs> tabs;
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public ArrayList<Tabs> getTabs() {
        return tabs;
    }

    public void setTabs(ArrayList<Tabs> tabs) {
        if(tabs==null)return;
        else
        this.tabs = tabs;
    }

    public MutableLiveData<String> getUrl() {
        return url;
    }

    public void setUrl(MutableLiveData<String> url) {
        this.url = url;
    }

    private MutableLiveData<String>url=new MutableLiveData<>();

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
      //  return PlaceholderFragment.newInstance(position + 1);
        return ChildFragment.newInstance(tabs.get(position).getApiUrl());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
   //     return mContext.getResources().getString(TAB_TITLES[position]);
        return tabs.get(position).getName();
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        if (tabs!=null&&tabs.size()>0)
        return tabs.size();
        else return 0;
    }
}