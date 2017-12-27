package com.bawei.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by admin on 2017/12/13.
 */

public class Fragment01 extends Fragment {

    private View view;
    private ViewPager mVp;
    private ArrayList<ChannelBean> channelBeens;
   // private String[] channels = {"关注", "热点", "历史", "北京", "社会", "科技", "娱乐", "国际"};
    private TabLayout mTl;
    private Gson gson;
    String jsonStr = "";
    //    private String[] String_url = {"https://www.toutiao.com/api/pc/focus/",
//                                         "http://gank.io/api/data/Android/10/1",
//                                    "http://www.yulin520.com/a2a/impressApi/news/mergeList?pageSize=10&page=1",
//                                      "http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat" +
//                                              "=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5" +
//                                              "&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys" +
//                                              "&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109" +
//                                              "&_chat_id=0&qtime=20160411091603%22",
//                                                       "http://api.expoon.com/AppNews/getNewsList/type/1/p/",
//                                                         "http://www.93.gov.cn/93app/data.do?channelId=50&startNum=2",
//                                                       "http://120.27.23.105/product/getProducts?pscid=39&page=1",
//                                                         "http://120.27.23.105/product/getCatagory"};
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;
    private ImageButton mImgbtn;
    private   Fragment_test fragment_test;
    private  Fragmenta fragmenta;
    private  Fragmentb fragmentb;
    private  Fragmentc fragmentc;
    private  Fragmentd fragmentd;
    private  Fragmente fragmente;
    private  Fragmentf fragmentf;
    private  Fragmentg fragmentg;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getContext(), R.layout.fragment01_layout, null);

        initView(view);

        fm = getChildFragmentManager();

        channelBeens = new ArrayList<ChannelBean>();

        //准备数据，添加集合   //准备栏目数据
        channelBeens.add(new ChannelBean("热点",true));
        channelBeens.add(new ChannelBean("军事",true));
        channelBeens.add(new ChannelBean("八卦",true));
        channelBeens.add(new ChannelBean("游戏",true));
        channelBeens.add(new ChannelBean("宠物",true));
        channelBeens.add(new ChannelBean("汽车",true));
        channelBeens.add(new ChannelBean("热卖",true));
        channelBeens.add(new ChannelBean("外卖",true));

     //把选择的栏目（true）数据配置给tablayout
        for (int i = 0; i < channelBeens.size(); i++) {
            if (channelBeens.get(i).isSelect()){ //判断是否选中，若选中则添加
                mTl.addTab(mTl.newTab().setText(channelBeens.get(i).getName()));
            }


        }
        fragments = new ArrayList<Fragment>();
        fragment_test = new Fragment_test();
        fragmenta = new Fragmenta();
        fragmentb = new Fragmentb();
        fragmentc = new Fragmentc();
        fragmentd = new Fragmentd();
        fragmente= new Fragmente();
        fragmentf = new Fragmentf();
        fragmentg = new Fragmentg();

        //创建fragment作为vp的pager页
        fragments.add(fragment_test);//保存fragment，作为vp的pager页
        fragments.add(fragmenta);
        fragments.add(fragmentb);
        fragments.add(fragmentc);
        fragments.add(fragmentd);
        fragments.add(fragmente);
        fragments.add(fragmentf);
        fragments.add(fragmentg);

                Bundle b = new Bundle();
        b.putString("title1", channelBeens.get(0).getName());fragments.get(0).setArguments(b);
        b.putString("title2", channelBeens.get(1).getName());fragments.get(1).setArguments(b);
        b.putString("title3", channelBeens.get(2).getName());fragments.get(2).setArguments(b);
        b.putString("title4", channelBeens.get(3).getName());fragments.get(3).setArguments(b);
        b.putString("title5", channelBeens.get(4).getName());fragments.get(4).setArguments(b);
        b.putString("title6", channelBeens.get(5).getName());fragments.get(5).setArguments(b);
        b.putString("title7", channelBeens.get(6).getName());fragments.get(6).setArguments(b);
        b.putString("title8", channelBeens.get(7).getName());fragments.get(7).setArguments(b);






        mVp.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                String name = channelBeens.get(position).getName();
                return name;
                        //channelBeens.get(position);
                        //ArrayList<ChannelBean>;
            }
        });

        mTl.setupWithViewPager(mVp);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ChannelActivity.REQUEST_CODE && resultCode == ChannelActivity.RESULT_CODE){//为true表示是频道管理回调回来的
            jsonStr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);//得到栏目管理的结果
            Toast.makeText(getContext(),jsonStr,Toast.LENGTH_SHORT).show();
            Log.i("main",jsonStr);
            mTl.removeAllTabs();//清空之前的栏目
            //把新选择的栏目结果更新到tablayout上
            gson = new Gson();
            //进行json解析
            Type type= new TypeToken<ArrayList<ChannelBean>>(){}.getType();
            channelBeens = gson.fromJson(jsonStr,type);
            //遍历结果，更新tablayout
            for (int i=0;i<channelBeens.size();i++){
                if(channelBeens.get(i).isSelect()){
                    mTl.addTab(mTl.newTab().setText(channelBeens.get(i).getName()));
                }
            }
        }




    }

    private void initView(View view) {
        mVp = (ViewPager) view.findViewById(R.id.vp);
        mTl = (TabLayout) view.findViewById(R.id.tl);
        mImgbtn = (ImageButton) view.findViewById(R.id.imgbtn);

        mImgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    default:
                        break;
                    case R.id.imgbtn:
                        ChannelActivity.startChannelActivity((AppCompatActivity) getContext(),channelBeens);
                        break;


                }
            }
        });

    }
}
