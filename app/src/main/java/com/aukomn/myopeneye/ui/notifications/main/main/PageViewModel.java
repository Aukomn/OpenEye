package com.aukomn.myopeneye.ui.notifications.main.main;

import com.aukomn.myopeneye.bean.ThemesContent;
import com.aukomn.myopeneye.http.LoadUtil;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
    private MutableLiveData<String> murl=new MutableLiveData<>();

    public MutableLiveData<String> getUrl() {
        return murl;
    }

    public void setUrl(String url) {
         murl.setValue(url);
    }

    public LiveData<ThemesContent>getTheme(String url){
        final MutableLiveData<ThemesContent>data=new MutableLiveData<>();
        LoadUtil.getService().getTheme(url)
                .enqueue(new Callback<ThemesContent>() {
                    @Override
                    public void onResponse(Call<ThemesContent> call, Response<ThemesContent> response) {
                        if(response.isSuccessful())
                            data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<ThemesContent> call, Throwable t) {

                    }
                });
        return data;
    }
}