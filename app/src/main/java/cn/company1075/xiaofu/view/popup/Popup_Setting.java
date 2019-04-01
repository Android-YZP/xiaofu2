package cn.company1075.xiaofu.view.popup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tencent.bugly.beta.Beta;

import cn.company1075.xiaofu.BindActivity;
import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.guide.GuideView;
import cn.company1075.xiaofu.model.view.Pop_Info;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.utils.APKVersionCodeUtils;
import cn.company1075.xiaofu.utils.SPUtils;
import cn.company1075.xiaofu.utils.SkinDeatil;
import cn.company1075.xiaofu.view.ActivityHome;
import cn.company1075.xiaofu.view.fragment.Page_One;
import cn.company1075.xiaofu.view.title.Title_Product;
import cn.company1075.xiaofu.view.title.Title_Setting;
import cn.company1075.xiaofu.view.title.Title_Xiaofu;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutMovable;

public class Popup_Setting extends MyLayoutMovable implements View.OnClickListener{

    private Context context;
    private Title_Info position;

   public Button usinghelp,checkupdate,bind,close;
   private SeekBar seekBar;
   public AudioManager audioManager;
   private int maxVolume ,currentVolume;

   private VolumeReceiver receiver;
   private TextView version;

    private boolean mReceiverTag = false;   //广播接受者标识,bimianchongfu

    public static  boolean isHelp = false;//是否点击了使用帮助

    public Popup_Setting(Context context, Title_Info position, String type) {
        super(context, R.layout.popup_setting, position, type);
        this.context = context;
        this.position = position;

        initView();
        initSoundSeekBar();
        getVersion();
        registerExitReceiver();

    }

    //注册广播
    private void registerExitReceiver() {

        if (!mReceiverTag){
            receiver = new VolumeReceiver();
            IntentFilter filter = new IntentFilter() ;
            filter.addAction("android.media.VOLUME_CHANGED_ACTION") ;
            if (null == receiver){
                context.registerReceiver(receiver, filter) ;//注册广播用于监听物理音量按键变化
            }
        }

    }
    //销毁广播
    private void unRegisterExitReceiver() {
        if (mReceiverTag) {   //判断广播是否注册
            mReceiverTag = false;   //Tag值 赋值为false 表示该广播已被注销
           context.unregisterReceiver(receiver);   //注销广播
        }

    }



    //获取版本号信息
    private void getVersion() {
        int versionCode = APKVersionCodeUtils.getVersionCode(context);
       String apkName =  APKVersionCodeUtils.getVerName(context);
        version.setText("版本号"+"\n"+apkName+ "  "+versionCode);
    }

    private void initView() {

        usinghelp = findViewById(R.id.settingg_usinghelp);
        checkupdate = findViewById( R.id.settin_checkupdate);
        seekBar = findViewById(R.id.setting_progress);
        version = findViewById(R.id.setting_version);
        close = findViewById(R.id.close_layout);
        bind = findViewById(R.id.settin_bind);
        close.setOnClickListener(this);
        usinghelp.setOnClickListener(this);
        checkupdate.setOnClickListener(this);
        bind.setOnClickListener(this);

    }


    //初始化音量控件和监听
    private void initSoundSeekBar() {

        audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekBar.setMax(maxVolume);
        //获取当前音量
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setProgress(currentVolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC , progress,0);
                currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                seekBar.setProgress(currentVolume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }


    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.settingg_usinghelp:
                AllUser.close_layout(this,this.getiPosition() );
                showGuide(Page_One.getInstance(context));
                isHelp = true;
                break;

            case R.id.settin_checkupdate:
                Beta.checkUpgrade();
                break;

            case R.id.close_layout:
                AllUser.close_layout(this,position);
                unRegisterExitReceiver();
                break;

             //绑定店铺
            case R.id.settin_bind:
                context.startActivity(new Intent(context, BindActivity.class));
               // AllUser.close_layout(this, this.getiPosition());
                break;

        }



    }

    //物理按键调节音量后 刷新Seekbar
    private class VolumeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")){
                currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                seekBar.setProgress(currentVolume);
            }
        }
    }


    //防止弹出系统自带的音量控件
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0);
        }else if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0);
        }
        return true;
    }



    public void showGuide(final Page_One page_one ) {

        final GuideView.Builder builder = new GuideView.Builder(context, APKVersionCodeUtils.getVerName(context),true);

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
                final GuideView.Builder builder2 = new GuideView.Builder(context, APKVersionCodeUtils.getVerName(context),true);
                builder2.addHintView(title_setting.popup_setting.bind, R.mipmap.g2, GuideView.Direction.LEFT2, GuideView.MyShape.ELLIPSE, -500, -500, new GuideView.OnClickCallback() {
                    @Override
                    public void onGuideViewClicked() {
                        AllUser.close_layout(title_setting.popup_setting, title_setting.popup_setting.getiPosition());
                        builder2.showNext();
                        final GuideView.Builder builder3 = new GuideView.Builder(context, APKVersionCodeUtils.getVerName(context),true);
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

}
