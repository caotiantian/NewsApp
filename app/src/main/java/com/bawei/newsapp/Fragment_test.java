package com.bawei.newsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.wzq.wzlibrary.view.XListView;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import adapter.BannerImageLoader;
import adapter.PhoneAdapter;
import beans.Foods;
import beans.Phone;
import utils.NetWorkUtil;

/**
 * Created by admin on 2017/12/13.
 */

public class Fragment_test extends Fragment {

    private View view;
    private Banner mBanner;
    private XListView mXlist;
    private List<Phone.DataBean.PcFeedFocusBean> pc;
    String url = "http://p1.pstatp.com/origin/4ad10012a289a5625ce6.jpg";
    String url1 = "http://p3.pstatp.com/origin/4acf001374c91b7da4af.jpg";
    String url2 = "http://p3.pstatp.com/origin/4ad0001662e1541f0460.jpg";
    String url3 = "http://p1.pstatp.com/origin/4ad1001656304235f565.jpg";
    String url4 = "http://a0.att.hudong.com/15/08/300218769736132194086202411_950.jpg";
    String url5 = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
    private ArrayList<String> imgs = new ArrayList<String>();
     String title1;
    int currentpager = 1;
    private String jsonUurl = "https://www.toutiao.com/api/pc/focus/";
     //private String JSON_URL2 = "http://gank.io/api/data/Android/10/1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        initView(view);
        Bundle b = getArguments();
         title1 = b.getString("title1");


        new AsyncTask<String,Integer,String>(){
            @Override
            protected String doInBackground(String... strings) {
                String getjson = new NetWorkUtil().getjson(jsonUurl);

                return getjson;
            }

            @Override
            protected void onPostExecute(String s) {

                Phone phone = new Gson().fromJson(s, Phone.class);
                pc = phone.getData().getPc_feed_focus();

                PhoneAdapter adapter = new PhoneAdapter(getContext(),pc);
                mXlist.setAdapter(adapter);


            }
        }.execute();
//        new AsyncTask<String,Integer,String>(){
//            @Override
//            protected String doInBackground(String... strings) {
//                String getjson = new NetWorkUtil().getjson(title+currentpager);
//
//                return getjson;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//
//                Foods foods = new Gson().fromJson(s, Foods.class);
//              data = foods.getResults();
//                NewsAdapter adapter = new NewsAdapter(getContext(),data);
//
//
//                mXlist.setAdapter(adapter);
//
//
//            }
//        }.execute();

        return view;
    }

    private void initView(View view) {

        mBanner = (Banner) view.findViewById(R.id.banner);
        mXlist = (XListView) view.findViewById(R.id.xlist);

        mBanner.setDelayTime(2000);
        mBanner.setImageLoader(new BannerImageLoader());
        imgs.add(url);
        imgs.add(url1);
        imgs.add(url2);
        imgs.add(url3);
        imgs.add(url4);
        imgs.add(url5);
        mBanner.setImages(imgs);
        mBanner.start();


        mXlist.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {

                new AsyncTask<String,Integer,String>(){
                    @Override
                    protected String doInBackground(String... strings) {
                        String getjson = new NetWorkUtil().getjson(jsonUurl);

                        return getjson;
                    }

                    @Override
                    protected void onPostExecute(String s) {

                        Phone phone = new Gson().fromJson(s, Phone.class);
                        pc = phone.getData().getPc_feed_focus();

                        PhoneAdapter adapter = new PhoneAdapter(getContext(),pc);
                        mXlist.setAdapter(adapter);


                    }
                }.execute();
                closexlist();

            }

            @Override
            public void onLoadMore() {

                new AsyncTask<String,Integer,String>(){
                    @Override
                    protected String doInBackground(String... strings) {
                        String getjson = new NetWorkUtil().getjson(jsonUurl);



                        return getjson;

                    }

                    @Override
                    protected void onPostExecute(String s) {

                        Phone phone = new Gson().fromJson(s, Phone.class);
                        pc = phone.getData().getPc_feed_focus();

                        PhoneAdapter adapter = new PhoneAdapter(getContext(),pc);
                        mXlist.setAdapter(adapter);


                    }
                }.execute();
                closexlist();

            }
        });




    }
    public void closexlist(){
        mXlist.stopLoadMore();
        mXlist.stopRefresh();



    }
}
