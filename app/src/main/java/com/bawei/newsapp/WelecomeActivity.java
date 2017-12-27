package com.bawei.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.ViewpagerAdapter;
import global.APPConstants;
import utils.SpUtils;

public class WelecomeActivity extends AppCompatActivity implements View.OnClickListener {

    private List<View> list;
    private Button startBtn;

    // 引导页图片资源
    private static final int[] pics = {R.layout.welecome_layout1,
            R.layout.welecome_layout2, R.layout.welecome_layout3, R.layout.welecome_layout4};

    // 底部小点图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;
    private ViewPager mVpGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welecome);
        initView();

        list = new ArrayList<View>();
        // 初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);

            if (i == pics.length - 1) {
                startBtn = (Button) view.findViewById(R.id.login);
                startBtn.setTag("enter");
                startBtn.setOnClickListener(this);
            }

            list.add(view);

        }
        // 初始化adapter

        ViewpagerAdapter adapter = new ViewpagerAdapter(list);
        mVpGuide.setAdapter(adapter);
        mVpGuide.addOnPageChangeListener(new PageChangeListener());

        initDots();


    }


    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[pics.length];

        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            // 得到一个LinearLayout下面的每一个子元素
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(false);// 都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(true); // 设置为白色，即选中状态

    }

    /**
     * 设置当前view
     *
     * @param position
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        mVpGuide.setCurrentItem(position);
    }

    /**
     * 设置当前指示点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position) {
            return;
        }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex = position;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 如果切换到后台，就设置下次不进入功能引导页
        SpUtils.putBoolean(WelecomeActivity.this, APPConstants.FIRST_OPEN, true);
        finish();
    }

    private void initView() {
        mVpGuide = (ViewPager) findViewById(R.id.vp_guide);
    }


    class PageChangeListener implements ViewPager.OnPageChangeListener {
        // 当滑动状态改变时调用
        @Override
        public void onPageScrollStateChanged(int position) {
            // arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。

        }

        // 当前页面被滑动时调用
        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {
            // arg0 :当前页面，及你点击滑动的页面
            // arg1:当前页面偏移的百分比
            // arg2:当前页面偏移的像素位置

        }

        // 当新的页面被选中时调用
        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
            setCurDot(position);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getTag().equals("enter")) {
            enterMainActivity();
            return;
        }

        int position = (Integer) view.getTag();
        setCurView(position);
        setCurDot(position);


    }

    private void enterMainActivity() {
        Intent intent = new Intent(WelecomeActivity.this,
                SplashActivity.class);
        startActivity(intent);
        SpUtils.putBoolean(WelecomeActivity.this, APPConstants.FIRST_OPEN, true);
        finish();
    }
}
