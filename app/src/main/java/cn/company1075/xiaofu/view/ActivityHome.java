package cn.company1075.xiaofu.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;


import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.guide.GuideView;
import cn.company1075.xiaofu.utils.APKVersionCodeUtils;
import cn.company1075.xiaofu.view.fragment.Page_One;
import cn.company1075.xiaofu.view.title.Title_Product;
import cn.company1075.xiaofu.view.title.Title_Setting;
import cn.company1075.xiaofu.view.title.Title_Xiaofu;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutUnMovable;
import xiaofu.xflibrary.ble.XFBleHelper;

public class ActivityHome extends Activity {
    private final String TAG = "Activity_Home";
    RelativeLayout relativeLayout;
    Page_One page_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        showGuide(page_one);

    }



    private void initView() {
        relativeLayout = findViewById(R.id.relativelayout_main);
        AllUser.parent =relativeLayout;
        page_one  = Page_One.getInstance(this);
        for (MyLayoutUnMovable view : page_one.getViews()){
            relativeLayout.addView(view.getmLayoutView(),view.getmParams());
        }


    }

    public void showGuide(final Page_One page_one ) {

        final GuideView.Builder builder = new GuideView.Builder(this, APKVersionCodeUtils.getVerName(this));

        Title_Xiaofu title_xiaofu = (Title_Xiaofu) page_one.getViews().get(2);
        builder.addHintView(title_xiaofu.title_xiaofu, R.mipmap.g3, GuideView.Direction.LEFT_BOTTOM, GuideView.MyShape.CIRCULAR, 300, 0, new GuideView.OnClickCallback() {
            @Override
            public void onGuideViewClicked() {
                builder.showNext();
            }
        });
        final Title_Setting title_setting = (Title_Setting) page_one.getViews().get(4);
        builder.addHintView(title_setting.title_setting, R.mipmap.g1, GuideView.Direction.LEFT_BOTTOM, GuideView.MyShape.CIRCULAR, 300, 0, new GuideView.OnClickCallback() {
            @Override
            public void onGuideViewClicked() {
                builder.showNext();
                AllUser.initPopup_layout(title_setting.popup_setting);
                final GuideView.Builder builder2 = new GuideView.Builder(ActivityHome.this, APKVersionCodeUtils.getVerName(ActivityHome.this));
                builder2.addHintView(title_setting.popup_setting.bind, R.mipmap.g2, GuideView.Direction.LEFT2, GuideView.MyShape.ELLIPSE, -500, -500, new GuideView.OnClickCallback() {
                    @Override
                    public void onGuideViewClicked() {
                        AllUser.close_layout(title_setting.popup_setting, title_setting.popup_setting.getiPosition());
                        builder2.showNext();
                        final GuideView.Builder builder3 = new GuideView.Builder(ActivityHome.this, APKVersionCodeUtils.getVerName(ActivityHome.this));
                        Title_Product title_product = (Title_Product) page_one.getViews().get(3);
                        builder3.addHintView(title_product.layout, R.mipmap.g4, GuideView.Direction.LEFT_BOTTOM, GuideView.MyShape.CIRCULAR, 300, 0, new GuideView.OnClickCallback() {
                            @Override
                            public void onGuideViewClicked() {
                                builder3.showNext();
                            }
                        });
                        builder3.show();
                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        builder2.show();
                    }
                }, 200);
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        XFBleHelper.onActivityResult(this, requestCode, resultCode);
    }


}
