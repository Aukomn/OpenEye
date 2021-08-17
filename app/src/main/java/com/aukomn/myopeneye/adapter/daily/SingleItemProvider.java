package com.aukomn.myopeneye.adapter.daily;

import com.aukomn.myopeneye.R;
import com.aukomn.myopeneye.bean.Daily;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class SingleItemProvider extends BaseItemProvider<Daily.Item> {
    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dailytext;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, Daily.Item item) {
        baseViewHolder.setText(R.id.textdaily,(item).getData().getText());
    }
}
