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

import adapter.StudentsAdapter;
import beans.StudentBeans;
import utils.NetWorkUtil;

/**
 * Created by admin on 2017/12/18.
 */

public class Fragmentc extends Fragment {

    private View view;
    private XListView mXlist;
    private String json_url = "http://www.yulin520.com/a2a/impressApi/news/mergeList?pageSize=10&page=";
     int currentpager = 1;
     private    List<StudentBeans.DataBean> data;
     private  StudentsAdapter studentsAdapter;
     String title4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.a_layout, null);
        Bundle b = getArguments();
        title4 = b.getString("title4");
        initView(view);
          new AsyncTask<String,Integer,String>(){

              @Override
              protected String doInBackground(String... strings) {

                  String getjson = new NetWorkUtil().getjson(json_url+currentpager);
                  return getjson;
              }

              @Override
              protected void onPostExecute(String s) {
                  StudentBeans studentBeans = new Gson().fromJson(s, StudentBeans.class);
                  data = studentBeans.getData();
                  studentsAdapter = new StudentsAdapter(getContext(), data);
                  mXlist.setAdapter(studentsAdapter);



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
                        StudentBeans studentBeans = new Gson().fromJson(s, StudentBeans.class);
                        data = studentBeans.getData();
                        studentsAdapter = new StudentsAdapter(getContext(),data);
                        mXlist.setAdapter(studentsAdapter);

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
                        StudentBeans studentBeans = new Gson().fromJson(s, StudentBeans.class);
                        List<StudentBeans.DataBean> data2 = studentBeans.getData();
                        data.addAll(data2);
                        studentsAdapter.notifyDataSetChanged();


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