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

import java.util.List;

import adapter.NewsAdapter;
import beans.Foods;
import utils.NetWorkUtil;

/**
 * Created by admin on 2017/12/18.
 */

public class Fragmente extends Fragment {

    private View view;
    private XListView mXlist;
    private String json_url = "http://gank.io/api/data/Android/10/";
     int currentpager = 1;
     String title6;
    private  List<Foods.ResultsBean> results;
    private  NewsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.a_layout, null);
        Bundle b = getArguments();
        title6 = b.getString("title6");
        initView(view);
        new AsyncTask<String,Integer,String>(){

            @Override
            protected String doInBackground(String... strings) {

                String getjson = new NetWorkUtil().getjson(json_url+currentpager);
                return getjson;
            }

            @Override
            protected void onPostExecute(String s) {
                Foods foods = new Gson().fromJson(s, Foods.class);
                results = foods.getResults();
                adapter = new NewsAdapter(getContext(), results);
                mXlist.setAdapter(adapter);



            }
        }.execute();



        return view;
    }

    private void initView(View view) {
        mXlist = (XListView) view.findViewById(R.id.xlist);
        mXlist.setPullRefreshEnable(true);
        mXlist.setPullLoadEnable(true);
        mXlist.setRefreshTime("刚刚已经刷新");
        mXlist.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {



                new AsyncTask<String,Integer,String>(){
                    int currentpager = 1;

                    @Override
                    protected String doInBackground(String... strings) {

                        String getjson = new NetWorkUtil().getjson(json_url+currentpager);
                        return getjson;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        Foods foods = new Gson().fromJson(s, Foods.class);
                        results = foods.getResults();
                        adapter = new NewsAdapter(getContext(), results);
                        mXlist.setAdapter(adapter);

                        closeLoder();



                    }
                }.execute();





            }

            @Override
            public void onLoadMore() {

                currentpager++;
                new AsyncTask<String,Integer,String>(){



                    @Override
                    protected String doInBackground(String... strings) {

                        String getjson = new NetWorkUtil().getjson(json_url+currentpager);
                        return getjson;
                    }
                    @Override
                    protected void onPostExecute(String s) {

                        Foods foods = new Gson().fromJson(s, Foods.class);

                        List<Foods.ResultsBean> results1 = foods.getResults();

                        results.addAll(results1);
                        adapter.notifyDataSetChanged();


                        closeLoder();






                    }
                }.execute();







            }
        });




    }
    public void  closeLoder(){
        mXlist.stopRefresh();
        mXlist.stopLoadMore();

    }
}