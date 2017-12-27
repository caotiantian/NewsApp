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

import adapter.MyAdapter;
import adapter.NewsAdapter;
import beans.Foods;
import beans.NewsBeans;
import utils.NetWorkUtil;

/**
 * Created by admin on 2017/12/18.
 */

public class Fragmentb extends Fragment {

    private View view;
    private XListView mXlist;
    private String URL = "http://api.expoon.com/AppNews/getNewsList/type/2/p/";
     int currentpager = 1;
     private  List<Foods.ResultsBean> results;
     private String title3;
    private  MyAdapter adapter;
    private  List<NewsBeans.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.a_layout, null);
        Bundle b = getArguments();
        title3 = b.getString("title3");
        initView(view);
          new AsyncTask<String,Integer,String>(){

              @Override
              protected String doInBackground(String... strings) {

                  String getjson = new NetWorkUtil().getjson(URL+currentpager);
                  return getjson;
              }

              @Override
              protected void onPostExecute(String s) {
                  NewsBeans newsBeans = new Gson().fromJson(s, NewsBeans.class);
                  data = newsBeans.getData();
                  adapter = new MyAdapter(getContext(), data);

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

                        String getjson = new NetWorkUtil().getjson(URL+currentpager);
                        return getjson;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        NewsBeans newsBeans = new Gson().fromJson(s, NewsBeans.class);
                        data = newsBeans.getData();
                        adapter = new MyAdapter(getContext(), data);

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

                        String getjson = new NetWorkUtil().getjson(URL+currentpager);
                        return getjson;
                    }
                    @Override
                    protected void onPostExecute(String s) {

                        NewsBeans newsBeans = new Gson().fromJson(s, NewsBeans.class);
                        List<NewsBeans.DataBean> data2 = newsBeans.getData();


                        data.addAll(data2);
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