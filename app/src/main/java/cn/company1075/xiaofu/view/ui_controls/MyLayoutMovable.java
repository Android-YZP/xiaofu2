package cn.company1075.xiaofu.view.ui_controls;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import java.util.TimerTask;

import cn.company1075.xiaofu.model.view.LayoutStatus;
import cn.company1075.xiaofu.model.view.Title_Info;


/**
 * Created by liutianxu on 17/2/16.
 */

//编辑状态可拖动
public class MyLayoutMovable extends MyLayout2{


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
//                case 0:
//                    mylayoutMoveable.clearAnimation();
//                    mylayoutMoveable.setVisibility(GONE);
//                    AllUsers.popup_info.remove(mylayoutMoveable);
//                    updateViewPosition(7*30);
//                    break;
//                case 1:
//                    myCallInterface.action_up(mylayoutMoveable);
////                    updateViewPosition(7*30);
//                    break;
            }
        }
    };
    private String TAG = "MyLayoutMovable";
    //小悬浮窗的高度
    public int viewWidth;
    public int viewHeight;

    private WindowManager windowManager;
    //记录当前手指位置在屏幕上的坐标值
    private float xInScreen;
    private float yInScreen;
    //记录手指按下时在屏幕上的坐标的值
    private float xDownInScreen;
    private float yDownInScreen;
    //记录手指按下时在小悬浮窗的View上的纵坐标的值
    private float xInView;
    private float yInView;
    //是否被选中
    private LayoutStatus iStatus;
    //屏幕高度
    private int iScreenHeight;
    private int iScreenWidth;
    private int statusBarHeght;
    private int maxSpaceTime = 500;
    private Title_Info iPosition;

    //动画
//    private AnimationY animation_big;
//    private AnimationY animation_small;
    private Animation translate;
    private boolean isScaled;


    private int statusBarHeight;

    //只赋值一次，记录按下时候的X,Y坐标，不改变
    private float downX;
    private float downY;
    public Button delete_btn;

    public String type;
    //多部件移动控制
//    private MoveControl moveControl;

    public static float DX;
    public static float DY;

    private MyLayoutMovable mylayoutMoveable;

    private int record;

//    private boolean isBreak = false;

    public ViewGroup linearLayout;

    private boolean a = false;

    public boolean canMove = false;

    private boolean isGone = false;

    private int duration = 500;

    private int y;

    private long event_time = 0;

    TranslateAnimation translateAnimation;

    public MyLayoutMovable(Context context) {
        super(context);
    }

    public MyLayoutMovable(Context context, int viewInt, Title_Info position, String type) {
        super(context, viewInt, position);
        this.type = type;
        iPosition = position;
        iStatus = LayoutStatus.NORMAL;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        iScreenWidth = mDisplayMetrics.widthPixels;
        iScreenHeight = mDisplayMetrics.heightPixels;
        viewWidth = iPosition.getTakeX();
        viewHeight = iPosition.getTakeY();
        mParams = new LayoutParams(viewWidth, viewHeight);
        mParams.topMargin = iPosition.getBeginY();
        mParams.leftMargin = iPosition.getBeginX();

        //动画
//        animation_big = AnimationUtils.loadAnimation(context, R.anim.scale_layout_big);
//        animation_big.setFillAfter(true);
//        animation_small = AnimationUtils.loadAnimation(context, R.anim.scale_layout_small);
//        animation_small.setFillAfter(true);
//        translate = AnimationUtils.loadAnimation(context, R.anim.translate);
//        translate.setFillAfter(true);
//        translate.setInterpolator(new DecelerateInterpolator());

        isScaled = false;
//        moveControl = net MoveControl(context);
        mylayoutMoveable = this;


        //初始化删除模块的按钮
//        initDeleteButton();
        //删除模块的点击事件
//        ButtonDelete();

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
//                downX = xInView = event.getX();
//                downY = yInView = event.getY();
//                LogInfo.i(TAG, "xDownInScreen1 = " + xDownInScreen);
//                LogInfo.i(TAG, "yDownInScreen1 = " + yDownInScreen);
//                xDownInScreen = event.getRawX();
//                yDownInScreen = event.getRawY();

//                break;
//            case MotionEvent.ACTION_MOVE:
//                //记录结束值
//                float endY = event.getY();
//
//                yInScreen = event.getRawY();
//
//                y = (int) (yInScreen - yInView);
//                long time = event.getEventTime()-event.getDownTime();
//                DY = endY - downY;
//                if (Math.abs(DY) < 5 && time > 500) {
//                    canMove = true;
//                }
//                if (linearLayout != null && canMove) {
//                    updateViewPosition(y);
//                    linearLayout.setBackgroundResource(R.drawable.musci2_bg);
//                    if (event_time == 0){
//                        event_time = event.getEventTime();
//                    }
//                    if (y<-150){
//                        isGone = true;
//                    }else {
//                        isGone = false;
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                long end_time = event.getEventTime()-event_time;
//                canMove = false;
//                linearLayout.setBackgroundResource(R.drawable.musci_bg);
//                if (isGone) {
//                    record = AllUsers.usable_area;
////                    MyAnimation.AnimationY(duration,this,y,-28*30);
////                    translate.start();
//                    translateAnimation = new TranslateAnimation(0,0,y,-840);
//                    translateAnimation.setDuration(400);               //设置动画持续时间
//                    translateAnimation.setRepeatCount(1);              //设置重复次数
//                    this.setAnimation(translateAnimation);             //设置动画效果
//                    translateAnimation.startNow();
//                    Timer t = new Timer();
//                    task tt = new task();
//                    task2 tt2 = new task2();
//                    t.schedule(tt, 300);//5000单位是毫秒=5秒
//                    t.schedule(tt2, 400);
//                }else {
//                    //回到坐标y的原始位置
//                    updateViewPosition(7*30);
//                }
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

//    /**
//     * true:拦截孩子的事件，但会执行当前控件的onTouchEvent()方法
//     * false:不拦截孩子的事件，事件继续传递
//     *
//     * @param event
//     * @return
//     */
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
////        if (AllUsers.mainLayoutStatus == 0) {
////            return false;
////        }
//        boolean intercept = false;
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
////                isBreak = false;
//                //按下记录X坐标
//                // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
//                downX = xInView = event.getX();
//                downY = yInView = event.getY();
//                xDownInScreen = event.getRawX();
//                yDownInScreen = event.getRawY();
//                xInScreen = event.getRawX();
//                yInScreen = event.getRawY();
////                if (Utils_UI.isInclude(this, xDownInScreen, yDownInScreen)) {
////                    changeType(LayoutStatus.CHOOSE);
////                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //记录结束值
//                float endX = event.getX();
//                float endY = event.getY();
//
//                xInScreen = event.getRawX();
//                yInScreen = event.getRawY();
//                long time = event.getEventTime()-event.getDownTime();
//                DX = Math.abs(endX - downX);
//                DY = endY - downY;
//                if (Math.abs(DX) > 50 && Math.abs(DY)<30) {
//                    intercept = true;
//                    canMove = true;
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
//                break;
//        }
//        return intercept;
//    }


    class task extends TimerTask {
        public void run() {
//            if (record == AllUsers.usable_area) {
//                AllUsers.usable_area = AllUsers.usable_area + iPosition.getTakeX();
//                AllUsers.used_area = AllUsers.used_area - iPosition.getTakeX();
//            }
            handler.sendEmptyMessage(0);
        }
    }

    class task2 extends TimerTask {
        public void run() {
            if (myCallInterface != null) {
                handler.sendEmptyMessage(1);
            }

        }
    }

    public interface MyCallInterface
    {
        void action_up(MyLayoutMovable myLayoutMovable);

    }

    private MyCallInterface myCallInterface;

    public void setMyCallInterface(MyCallInterface myCallInterface) {
        this.myCallInterface = myCallInterface;
    }

    public Title_Info getiPosition() {
        return iPosition;
    }
}
