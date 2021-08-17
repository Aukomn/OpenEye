package com.aukomn.myopeneye.ui.notifications.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.bean.TabInfo;
import com.aukomn.myopeneye.ui.notifications.main.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_theme, container, false);
        final com.aukomn.myopeneye.ui.notifications.main.main.SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), getChildFragmentManager());
        ViewPager viewPager = root.findViewById(R.id.view_pagerth);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabsth);
        viewPager.setOffscreenPageLimit(4);
        tabs.setupWithViewPager(viewPager);
        pageViewModel.getTab().observe(getViewLifecycleOwner(), new Observer<TabInfo>() {
            @Override
            public void onChanged(TabInfo tabInfo) {
               if(tabInfo!=null)
                   sectionsPagerAdapter.setTabs(tabInfo.getTabInfoBean().getTabList());
               sectionsPagerAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }
}