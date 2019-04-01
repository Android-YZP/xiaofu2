package cn.company1075.xiaofu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import cn.company1075.xiaofu.utils.SPUtils;
import cn.company1075.xiaofu.utils.Skin;
import cn.company1075.xiaofu.utils.SkinDeatil;
import cn.company1075.xiaofu.view.popup.Popup_xiaofu;


public class FullSkinDetail extends Activity implements View.OnClickListener{

    private int width;
    private int height;
    private Button DrynessBtn,SensitiveBtn,PoreBtn,SenilityBtn,close;
    private TextView forehead,leftface,chin,nose,rightface,score,detail;
    int flag;
    SkinDeatil skin;
   // Skin skin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.xiaofu_fullface_detail);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        //设置Activity的宽高

        //设置屏幕宽高和显示位置
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width/3;
        lp.height = height*4/5;
        lp.gravity = Gravity.CENTER|Gravity.BOTTOM;
        getWindow().setAttributes(lp);

        initView();



    }

    private void initView() {

        skin  = (SkinDeatil) getIntent().getSerializableExtra("SkinDeatil");

        DrynessBtn = findViewById(R.id.DrynessBtn);
        DrynessBtn.setOnClickListener(this);
        SensitiveBtn = findViewById(R.id.SensitiveBtn);
        SensitiveBtn.setOnClickListener(this);
        PoreBtn = findViewById(R.id.PoreBtn);
        PoreBtn.setOnClickListener(this);
        SenilityBtn = findViewById(R.id.SenilityBtn);
        SenilityBtn.setOnClickListener(this);


        //各部位状态
        forehead = findViewById(R.id.foreheadState);
        leftface = findViewById(R.id.leftfaceState);
        rightface = findViewById(R.id.rightfaceState);
        nose = findViewById(R.id.noseState);
        chin = findViewById(R.id.chinState);
        score = findViewById(R.id.detailscore);
        detail = findViewById(R.id.detail);

        close = findViewById(R.id.close);
        close.setOnClickListener(this);

        score.setText(skin.getData().getPfType());
        detail.setText(skin.getData().getPfDesc());

        flag  = getIntent().getIntExtra("value", 1);
        initBtn(flag);
        initData();

          //  skin = (Skin) getIntent().getSerializableExtra("skin");


    }

    private void initBtn(int flag) {

        switch(flag){
            case 1 :
                DrynessBtn.setTextColor(this.getResources().getColor(R.color.white));
                DrynessBtn.setBackgroundResource(R.drawable.user2_icon_s);

                SensitiveBtn.setTextColor(this.getResources().getColor(R.color.string_gray7));
                SensitiveBtn.setBackgroundResource(R.drawable.user2_icon_n);

                PoreBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                PoreBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SenilityBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                SenilityBtn.setBackgroundResource(R.drawable.user2_icon_n);

                break;
            case 2 :

                DrynessBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                DrynessBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SensitiveBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.white));
                SensitiveBtn.setBackgroundResource(R.drawable.user2_icon_s);

                PoreBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                PoreBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SenilityBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                SenilityBtn.setBackgroundResource(R.drawable.user2_icon_n);

                break;
            case 3:
                DrynessBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                DrynessBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SensitiveBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                SensitiveBtn.setBackgroundResource(R.drawable.user2_icon_n);

                PoreBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.white));
                PoreBtn.setBackgroundResource(R.drawable.user2_icon_s);

                SenilityBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                SenilityBtn.setBackgroundResource(R.drawable.user2_icon_n);





                break;
            case 4 :


                DrynessBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                DrynessBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SensitiveBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                SensitiveBtn.setBackgroundResource(R.drawable.user2_icon_n);

                PoreBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.string_gray7));
                PoreBtn.setBackgroundResource(R.drawable.user2_icon_n);

                SenilityBtn.setTextColor(FullSkinDetail.this.getResources().getColor(R.color.white));
                SenilityBtn.setBackgroundResource(R.drawable.user2_icon_s);

                break;


        }
    }




    private void initData(){

        if (Popup_xiaofu.isOpmn){
            switch(flag){
                case 1 :
                    forehead.setText(skin.getData().getDistinguishResultList().get(2).getLevelAbout());
                    leftface.setText(skin.getData().getDistinguishResultList().get(2).getLevelAbout());
                    rightface.setText(skin.getData().getDistinguishResultList().get(2).getLevelAbout());
                    chin.setText(skin.getData().getDistinguishResultList().get(2).getLevelAbout());
                    nose.setText(skin.getData().getDistinguishResultList().get(2).getLevelAbout());
                    break;

                case 2 :
                    forehead.setText(skin.getData().getDistinguishResultList().get(1).getLevelAbout());
                    leftface.setText(skin.getData().getDistinguishResultList().get(1).getLevelAbout());
                    rightface.setText(skin.getData().getDistinguishResultList().get(1).getLevelAbout());
                    chin.setText(skin.getData().getDistinguishResultList().get(1).getLevelAbout());
                    nose.setText(skin.getData().getDistinguishResultList().get(1).getLevelAbout());
                    break;

                case 3 :
                    forehead.setText(skin.getData().getDistinguishResultList().get(0).getLevelAbout());
                    leftface.setText(skin.getData().getDistinguishResultList().get(0).getLevelAbout());
                    rightface.setText(skin.getData().getDistinguishResultList().get(0).getLevelAbout());
                    chin.setText(skin.getData().getDistinguishResultList().get(0).getLevelAbout());
                    nose.setText(skin.getData().getDistinguishResultList().get(0).getLevelAbout());
                    break;
                case 4 :
                    forehead.setText(skin.getData().getDistinguishResultList().get(3).getLevelAbout());
                    leftface.setText(skin.getData().getDistinguishResultList().get(3).getLevelAbout());
                    rightface.setText(skin.getData().getDistinguishResultList().get(3).getLevelAbout());
                    chin.setText(skin.getData().getDistinguishResultList().get(3).getLevelAbout());
                    nose.setText(skin.getData().getDistinguishResultList().get(3).getLevelAbout());
                    break;

            }


        }else {
        switch (flag){
            case 1:
                for (int i = 0; i <skin.getData().getDistinguishDataList2().size() ; i++) {
                    String position = skin.getData().getDistinguishDataList2().get(i).getPosition();
                    String Dryness = skin.getData().getDistinguishDataList2().get(i).getDryness().getLevelAbout();
                    if (position.equals("额头")){
                        forehead.setText(Dryness);
                    }

                    if (position.equals("左脸颊")){
                        leftface.setText(Dryness);
                    }
                    if (position.equals("右脸颊")){
                        rightface.setText(Dryness);
                    }

                    if (position.equals("下巴")){
                        chin.setText(Dryness);
                    }
                    if (position.equals("鼻子")){
                        nose.setText(Dryness);
                    }
                }

                break;

            case 2 :

                for (int i = 0; i <skin.getData().getDistinguishDataList2().size() ; i++){
                    String position = skin.getData().getDistinguishDataList2().get(i).getPosition();
                    String  Sensitive= skin.getData().getDistinguishDataList2().get(i).getSensitive().getLevelAbout();

                    if (position.equals("额头")){
                        forehead.setText(Sensitive);
                    }

                    if (position.equals("左脸颊")){
                        leftface.setText(Sensitive);
                    }
                    if (position.equals("右脸颊")){
                        rightface.setText(Sensitive);
                    }

                    if (position.equals("下巴")){
                        chin.setText(Sensitive);
                    }
                    if (position.equals("鼻子")){
                        nose.setText(Sensitive);
                    }

                }

                break;

            case 3 :

                for (int i = 0; i <skin.getData().getDistinguishDataList2().size() ; i++){
                    String position = skin.getData().getDistinguishDataList2().get(i).getPosition();
                    String  Pore= skin.getData().getDistinguishDataList2().get(i).getPore().getLevelAbout();

                    if (position.equals("额头")){
                        forehead.setText(Pore);
                    }

                    if (position.equals("左脸颊")){
                        leftface.setText(Pore);
                    }
                    if (position.equals("右脸颊")){
                        rightface.setText(Pore);
                    }

                    if (position.equals("下巴")){
                        chin.setText(Pore);
                    }
                    if (position.equals("鼻子")){
                        nose.setText(Pore);
                    }

                }

                break;
            case 4:
                for (int i = 0; i <skin.getData().getDistinguishDataList2().size() ; i++){
                    String position = skin.getData().getDistinguishDataList2().get(i).getPosition();
                    String  Senility= skin.getData().getDistinguishDataList2().get(i).getSenility().getLevelAbout();

                    if (position.equals("额头")){
                        forehead.setText(Senility);
                    }

                    if (position.equals("左脸颊")){
                        leftface.setText(Senility);
                    }
                    if (position.equals("右脸颊")){
                        rightface.setText(Senility);
                    }

                    if (position.equals("下巴")){
                        chin.setText(Senility);
                    }
                    if (position.equals("鼻子")){
                        nose.setText(Senility);
                    }
                }
                break;
        }
        }



    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.DrynessBtn :
                flag = 1;
                initBtn(1);
                initData();
                break ;
            case R.id.SensitiveBtn :
                flag = 2;
                initBtn(2);
                initData();

                break ;
            case R.id.PoreBtn :
                flag = 3;
                initBtn(3);
                initData();

                break ;
            case R.id.SenilityBtn :
                flag = 4;
                initBtn(4);
                initData();

                break ;
            case R.id.close :
                    finish();
                break ;




        }

    }
}
