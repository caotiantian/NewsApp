package com.bawei.newsapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.ContentAdapter;
import beans.ContentModel;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mFrm;
    private RadioGroup mGroup;
    private DrawerLayout mDrawer;
    private RelativeLayout relativeLayout;
    private ListView listView;
    private List<ContentModel> list;
    private ContentAdapter adapter;
    private ImageView leftMenu;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏，一定要在setContentView之前
        setContentView(R.layout.activity_main);
        //getActionBar().hide();
        fm = getSupportFragmentManager();
        initView();
        initData();
        adapter = new ContentAdapter(this, list);
        listView.setAdapter(adapter);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frm,new Fragment01());
        transaction.commit();

        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.btn1:
                        //动态添加Fragment
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.frm,new Fragment01());
                        transaction.commit();

                        break;

                    case R.id.btn2:
                        //动态添加Fragment
                        FragmentManager manager2 = getSupportFragmentManager();
                        FragmentTransaction transaction2 = manager2.beginTransaction();
                        transaction2.replace(R.id.frm,new Fragment02());
                        transaction2.commit();

                        break;

                    case R.id.btn3:
                        //动态添加Fragment
                        FragmentManager manager3 = getSupportFragmentManager();
                        FragmentTransaction transaction3 = manager3.beginTransaction();
                        transaction3.replace(R.id.frm,new Fragment03());
                        transaction3.commit();

                        break;

                    case R.id.btn4:
                        //动态添加Fragment
                        FragmentManager manager4 = getSupportFragmentManager();
                        FragmentTransaction transaction4 = manager4.beginTransaction();
                        transaction4.replace(R.id.frm,new Fragment04());
                        transaction4.commit();

                        break;

                    default:
                        break;

                }



            }
        });


    }

    private void initView() {
        mFrm = (FrameLayout) findViewById(R.id.frm);
        mGroup = (RadioGroup) findViewById(R.id.group);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        relativeLayout = (RelativeLayout) findViewById(R.id.left);
        listView = (ListView) findViewById(R.id.left_listview);
        leftMenu = (ImageView) findViewById(R.id.leftmenu);

        leftMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(Gravity.LEFT);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                FragmentTransaction bt = fm.beginTransaction();
                switch ((int) id) {
                    case 0:
                        bt.replace(R.id.content, new Fragment01());
                        break;
                    case 1:
                        bt.replace(R.id.content, new Fragment02());
                        break;
                    case 2:
                        bt.replace(R.id.content, new Fragment03());
                        break;
                    case 3:
                        bt.replace(R.id.content, new Fragment04());
                        break;

                    default:
                        break;
                }
                bt.commit();
                mDrawer.closeDrawer(Gravity.LEFT);
            }
        });

        //关闭
        //   mDrawer.closeDrawer(mLl);


    }
    private void initData() {
        list = new ArrayList<ContentModel>();

        list.add(new ContentModel(R.drawable.doctoradvice2, "新闻", 1));
        list.add(new ContentModel(R.drawable.infusion_selected, "订阅", 2));
        list.add(new ContentModel(R.drawable.mypatient_selected, "图片", 3));
        list.add(new ContentModel(R.drawable.mywork_selected, "视频", 4));
        list.add(new ContentModel(R.drawable.nursingcareplan2, "跟帖", 5));
        list.add(new ContentModel(R.drawable.personal_selected, "投票", 6));
    }
}
