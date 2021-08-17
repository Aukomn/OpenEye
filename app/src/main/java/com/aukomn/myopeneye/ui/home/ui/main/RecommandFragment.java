package com.aukomn.myopeneye.ui.home.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.bean.HomePageRecommend;
import com.aukomn.myopeneye.constants.Constants;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A fragment representing a list of Items.
 */
public class RecommandFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private PageViewModel pageViewModel;
 //   private HomeRecAdapter adapter;
    private String next;
    private String url=Constants.VideoUrl.base_url+Constants.VideoUrl.allrec;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecommandFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecommandFragment newInstance(int columnCount) {
        RecommandFragment fragment = new RecommandFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommand_list, container, false);
        // Set the adapter
        //if (view instanceof RecyclerView) {
            Context context = view.getContext();
          //  adapter=new HomeRecAdapter(getActivity());
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listhomerec);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
         //   recyclerView.setAdapter(adapter);
        pageViewModel.getHomecommand(url).observe(getViewLifecycleOwner(), new Observer<HomePageRecommend>() {
            @Override
            public void onChanged(HomePageRecommend homePageRecommend) {
                if(homePageRecommend!=null){
                }
            }
        });
        return view;
    }
}