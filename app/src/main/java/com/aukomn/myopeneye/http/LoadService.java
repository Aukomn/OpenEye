package com.aukomn.myopeneye.http;

import com.aukomn.myopeneye.bean.AllRec;
import com.aukomn.myopeneye.bean.CommunityRecommend;
import com.aukomn.myopeneye.bean.Daily;
import com.aukomn.myopeneye.bean.Follow;
import com.aukomn.myopeneye.bean.HomePageRecommend;
import com.aukomn.myopeneye.bean.Message;
import com.aukomn.myopeneye.bean.TabInfo;
import com.aukomn.myopeneye.bean.ThemesContent;
import com.aukomn.myopeneye.bean.TopicBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface LoadService {
    @GET
    Call<TopicBean> getTop(@Url String url);
    @GET
    Call<Message> getMes(@Url String url);
    @GET
    Call<TabInfo> getTab(@Url String url);
    @GET
    Call<ThemesContent> getTheme(@Url String url);
    @GET
    Call<CommunityRecommend> getCrec(@Url String url);
    @GET
    Call<Follow> getFollow(@Url String url);
    @GET
    Call<Daily> getDaily(@Url String url);
    @GET
    Call<AllRec> getAll(@Url String url);
    @GET
    Call<HomePageRecommend> getCommand(@Url String url);
}
