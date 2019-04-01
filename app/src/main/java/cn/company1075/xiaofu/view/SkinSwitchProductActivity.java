package cn.company1075.xiaofu.view;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.guide.GuideView;
import cn.company1075.xiaofu.utils.APKVersionCodeUtils;
import cn.company1075.xiaofu.utils.LogInfo;
import cn.company1075.xiaofu.utils.SkinDeatil;
import cn.company1075.xiaofu.utils.xiaofu.BannerIndicator;
import cn.company1075.xiaofu.view.fragment.FullSkinFragment;
import cn.company1075.xiaofu.view.fragment.ProduckFragmeng;
import cn.company1075.xiaofu.view.popup.Popup_Setting;

public class SkinSwitchProductActivity extends AppCompatActivity{

    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    BannerIndicator bannerIndicator;
    private int width;
    private int height;
    Button close;
    private SkinDeatil skin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skinswitchproduct);
        initWindow();
        initView();



    }
    private void initWindow() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        //设置Activity的宽高

        //设置屏幕宽高和显示位置
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width-60;
        lp.height = height*4/5+80;
        lp.gravity = Gravity.CENTER|Gravity.BOTTOM;
        getWindow().setAttributes(lp);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }


    private void initView() {
        skin = (SkinDeatil)getIntent().getSerializableExtra("skin");
        LogInfo.i("==============", skin.getData().getPfType());
        close = findViewById(R.id.close_layout);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mViewPager = findViewById(R.id.switch_view_pager);
        mFragments = new ArrayList<Fragment>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("SkinData",skin);//这里的values就是我们要传的值
        FullSkinFragment fragment =  new FullSkinFragment() ;
        fragment.setArguments(bundle);
        ProduckFragmeng fragmeng2  =    new ProduckFragmeng();
        fragmeng2.setArguments(bundle);
        mFragments.add(fragment);
        mFragments.add(fragmeng2);



        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        bannerIndicator = findViewById(R.id.switch_indicator);
        bannerIndicator.setNumber(mFragments.size());
        bannerIndicator.setPosition(mViewPager.getCurrentItem());
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bannerIndicator.setPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (Popup_Setting.isHelp){
            showGuideHelp();
        }else {
            showGuide();
        }

    }

    private void showGuide() {
        final GuideView.Builder builder = new GuideView.Builder(this, APKVersionCodeUtils.getVerName(this));
        builder.addHintView(bannerIndicator, R.mipmap.g5, GuideView.Direction.LEFT, GuideView.MyShape.CIRCULAR2, 1500, 750, new GuideView.OnClickCallback() {
            @Override
            public void onGuideViewClicked() {
                builder.showNext();
            }
        });
        builder.show();


    }

    private void showGuideHelp() {
        final GuideView.Builder builder = new GuideView.Builder(this, APKVersionCodeUtils.getVerName(this),true);
        builder.addHintView(bannerIndicator, R.mipmap.g5, GuideView.Direction.LEFT, GuideView.MyShape.CIRCULAR2, 1500, 750, new GuideView.OnClickCallback() {
            @Override
            public void onGuideViewClicked() {
                builder.showNext();
                Popup_Setting.isHelp = false;
            }
        });
        builder.show();
    }


    public class FragmentAdapter extends FragmentPagerAdapter {
        //存放fragment的集合
        private List<Fragment> mFragments;

        public FragmentAdapter(FragmentManager fragmentManager, List<Fragment> mFragments) {
            super(fragmentManager);
            this.mFragments = mFragments;
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
