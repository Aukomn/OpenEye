package com.aukomn.myopeneye.ui.notifications.main.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.adapter.ThemeAdapter;
import com.aukomn.myopeneye.bean.ThemesContent;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment representing a list of Items.
 */
public class ChildFragment extends Fragment {
    private PageViewModel viewModel;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private String url;
    SmartRefreshLayout refreshLayout;
    private Boolean isfirst=true;
    private ThemeAdapter adapter;
    private SectionsPagerAdapter pagerAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChildFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ChildFragment newInstance(String url) {
        ChildFragment fragment = new ChildFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel= ViewModelProviders.of(this).get(PageViewModel.class);
        if (getArguments() != null) {
            viewModel.setUrl(getArguments().getString("url"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_theme, container, false);
        adapter=new ThemeAdapter(R.layout.item_child);
        // Set the adapter
            Context context = view.getContext();
            refreshLayout=view.findViewById(R.id.refreshtheme);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listtheme);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);
        if(isfirst){
            refreshLayout.autoRefresh();
           // isfirst=false;
        }
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                viewModel.getUrl().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        viewModel.getTheme(s).observe(getViewLifecycleOwner(), new Observer<ThemesContent>() {
                            @Override
                            public void onChanged(ThemesContent themesContent) {
                                if(themesContent!=null){
                                    adapter.setNewData(themesContent.getItemList());
                                    adapter.notifyDataSetChanged();
                                    refreshLayout.finishRefresh();
                                }
                            }
                        });
                    }
                });
            }
        });

        return view;
    }
}