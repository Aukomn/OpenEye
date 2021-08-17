package com.aukomn.myopeneye.adapter.daily;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.aukomn.myopeneye.bean.Daily;
import com.chad.library.adapter.base.BaseProviderMultiAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DailyAdapter extends BaseProviderMultiAdapter<Daily.Item> {
    private Context context;
    public DailyAdapter(Activity activity) {
        super();
        this.context=activity;
        addItemProvider(new SingleItemProvider());
        addItemProvider(new FollowCardProvider(activity));
    }

    @Override
    protected int getItemType(@NotNull List<? extends Daily.Item> list, int i) {
        Log.d("111111",list.get(i).getType());
        if(list.get(i).getType().contains("text"))
            return 0;
        if(list.get(i).getType().contains("ollow"))
            return 1;
        return 0;
    }
}
