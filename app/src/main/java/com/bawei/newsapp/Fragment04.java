package com.bawei.newsapp;

import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.text.format.DateUtils;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.List;

import adapter.MyAdapter;
import beans.NewsBeans;
import utils.NetWorkUtil;

/**
 * Created by admin on 2017/12/13.
 */

public class Fragment04 extends Fragment {

    private View view;
    private PullToRefreshGridView mPullToRefreshGrid;
    private  List<NewsBeans.DataBean> data;
    // 网址
    private String URL = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";
    int z = 10;
    int p = 10;
    private  MyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment04_layout, null);

        // 刚进入初次加载
        new GetDataTask().execute(URL + z);


        initView(view);
        return view;
    }

    private void initView(View view) {
        mPullToRefreshGrid = (PullToRefreshGridView) view.findViewById(R.id.pull_to_refresh_grid);

        //监听器
        mPullToRefreshGrid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            //下拉刷新
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {

                String label = "刚刚刷新成功了！";
                //
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                //页数
                z = z-1;

                //使用异步来加载数据
               new GetDataTask().execute(URL+z);





            }

            @Override

            //上拉加载
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

                p = p+1;
                //使用异步线程加载数据
                new GetDataTask2().execute(URL+p);

            }
        });


    }

    //下拉刷新加载更多的数据
    private  class GetDataTask extends AsyncTask<String,Integer,String>{
        private String s;

        @Override
        protected String doInBackground(String... strings) {

            String url = strings[0];
            //请求数据
             s = new NetWorkUtil().getjson(url);

            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            NewsBeans newsBeans = new Gson().fromJson(s, NewsBeans.class);
            data = newsBeans.getData();
            adapter = new MyAdapter(getContext(), data);
            mPullToRefreshGrid.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            mPullToRefreshGrid.onRefreshComplete(); //关闭刷新下拉


        }
    }

    //上拉加载更多的数据
    private  class GetDataTask2 extends AsyncTask<String,Integer,String>{
        private String s;

        @Override
        protected String doInBackground(String... strings) {

            String url = strings[0];
            //请求数据
            s = new NetWorkUtil().getjson(url);

            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            NewsBeans newsBeans = new Gson().fromJson(s, NewsBeans.class);
            List<NewsBeans.DataBean> data2 = newsBeans.getData();
            data.addAll(data2);
            adapter.notifyDataSetChanged();
            mPullToRefreshGrid.onRefreshComplete(); //关闭刷新下拉


        }
    }

}
