package cn.company1075.xiaofu.baseinfo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.guide.GuideView;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.model.view.View_Name;
import cn.company1075.xiaofu.utils.APKVersionCodeUtils;
import cn.company1075.xiaofu.view.ActivityHome;
import cn.company1075.xiaofu.view.ui_controls.CloseLayout;
import cn.company1075.xiaofu.view.ui_controls.MyAnimation;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutMovable;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutUnMovable;

/**
 * Created by Administrator on 2018/7/25.
 */

public class AllUser {


    public static List<MyLayoutMovable> popup_info = new ArrayList<>();
    public static int usable_area = 64;
    public static int spaceY;
    public static int spaceX;
    public static int used_area = 0;
    public static RelativeLayout parent;


    public static Timer t;
    public static Task3 task3;


    private static MyLayoutMovable myLayout;
    public static CloseLayout closeLayout;



    public static void translateX(MyLayoutMovable myLayoutMovable, int startX, int endX) {

        AnimatorSet animatorSet = new AnimatorSet();//组合动画

        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(myLayoutMovable, "translationX", startX * spaceX, endX * spaceX);


        animatorSet.play(animatorX);
        animatorSet.start();
        //记录并保存移动后的位置
        myLayoutMovable.getiPosition().setBeginX(endX);
    }

    public static void translateX(int duration, MyLayoutUnMovable myLayoutUnMovable, int startX, int endX) {

        AnimatorSet animatorSet = new AnimatorSet();//组合动画

        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(myLayoutUnMovable, "translationX", startX * spaceX, endX * spaceX);


        animatorSet.play(animatorX);
        animatorSet.start();
        //记录并保存移动后的位置
        myLayoutUnMovable.getIposition().setBeginX(endX);
    }


    private static void move1(MyLayoutMovable myLayoutMovable){
        translateX(myLayoutMovable, myLayoutMovable.getiPosition().getBeginX(), 0);
    }

    private static void move2(MyLayoutMovable myLayoutMovable1,MyLayoutMovable myLayoutMovable2){
        translateX(myLayoutMovable1, myLayoutMovable1.getiPosition().getBeginX(),  -10);
        translateX(myLayoutMovable2, myLayoutMovable2.getiPosition().getBeginX(), 11);
    }

    private static void move3(MyLayoutMovable myLayoutMovable1,MyLayoutMovable myLayoutMovable2,MyLayoutMovable myLayoutMovable3){
        translateX(myLayoutMovable1,myLayoutMovable1.getiPosition().getBeginX(), -21);
        translateX(myLayoutMovable2, myLayoutMovable2.getiPosition().getBeginX(), 0);
        translateX(myLayoutMovable3, myLayoutMovable3.getiPosition().getBeginX(), 21);
    }

    public static void moveout(MyLayoutMovable myLayoutMovable){
        translateX(myLayoutMovable, myLayoutMovable.getiPosition().getBeginX(), -120);
    }




    public static void initPopup_layout(final MyLayoutMovable myLayoutMovable) {
        MyAnimation.AnimationY(0, myLayoutMovable, 0, spaceY * 7);
        myLayoutMovable.setVisibility(View.VISIBLE);
        popup_info.remove(myLayoutMovable);
        popup_info.add(myLayoutMovable);
        Title_Info iposition = myLayoutMovable.getiPosition();
        switch (popup_info.size()){

            case  1 :
                move1(myLayoutMovable);
                break;
            case  2 :
                move2(popup_info.get(0),popup_info.get(1));
                break;
            case  3 :
                move3(popup_info.get(0),popup_info.get(1),popup_info.get(2));
                break;
            case  4 :
                //移除第一个添加的页面
                myLayout = popup_info.get(0);
                moveout(myLayout);
                t = new Timer();
                task3 = new Task3();
                t.schedule(task3, 400);
                //移除掉这个页面后要将可用面积加回来
              //  usable_area = usable_area + myLayout.getiPosition().getBeginX();
                popup_info.remove(0);
                move3(popup_info.get(0),popup_info.get(1),popup_info.get(2));

                break;
            default:

                break;


        }
//        if (usable_area > iposition.getTakeX()) {
//            if (popup_info.size() == 1) {
//                move1(myLayoutMovable);
//            } else if (popup_info.size() == 2) {
//                move2(popup_info.get(0),popup_info.get(1));
//            } else if (popup_info.size() == 3) {
//                move3(popup_info.get(0),popup_info.get(1),popup_info.get(2));
//            }
//        } else {
//            if (popup_info.size() == 3) {
//                //移除第一个添加的页面
//                myLayout = popup_info.get(0);
//                moveout(myLayout);
//                t = new Timer();
//                task3 = new Task3();
//                t.schedule(task3, 400);
//                //移除掉这个页面后要将可用面积加回来
//                usable_area = usable_area + myLayout.getiPosition().getBeginX();
//                popup_info.remove(0);
//                move2(popup_info.get(0),popup_info.get(1));
//            } else if (popup_info.size() == 4) {
//                //先关掉一个页面，然后判断，如果可用面积还是不够，则需要关闭两个页面
//                //移除第一个添加的页面
//                myLayout = popup_info.get(0);
//                moveout(myLayout);
//                t = new Timer();
//                task3 = new Task3();
//                t.schedule(task3, 400);
//                //移除掉这个页面后要将可用面积加回来
//                usable_area = usable_area + popup_info.get(0).getiPosition().getTakeX();
//                popup_info.remove(0);
//                if (usable_area < myLayoutMovable.getiPosition().getTakeX()) {
//                    //如果还是不够，则再关闭一个
//                    myLayout = popup_info.get(0);
//                    moveout(myLayout);
//                    t = new Timer();
//                    task3 = new Task3();
//                    t.schedule(task3, 400);
//                    //移除掉这个页面后要将可用面积加回来
//                    usable_area = usable_area + popup_info.get(0).getiPosition().getTakeX();
//                    popup_info.remove(0);
//                    //然后摆放剩下的两个页面的位置
//                    move2(popup_info.get(0),popup_info.get(1));
//                } else {
//                   move3(popup_info.get(0),popup_info.get(1),popup_info.get(3));
//                }
//            }
//        }

//        used_area = myLayoutMovable.getiPosition().getTakeX();
//        usable_area = usable_area - used_area;

        Log.e("djj", "popup.size ==" + popup_info.size());
        Log.e("djj", used_area + "===" + usable_area);
        //用户只要手指碰到屏幕然后抬起就会触发此方法
        closeLayout = new CloseLayout();
        closeLayout.setMyCallInterface(new MyLayoutMovable.MyCallInterface() {
            @Override
            public void action_up(MyLayoutMovable myLayoutMovable) {
                if (popup_info.size() == 1) {
                   move1(popup_info.get(0));
                } else if (popup_info.size() == 2) {
                    move2(popup_info.get(0),popup_info.get(1));
                }
                //让被关闭的页面回到最原始的位置
                move1(myLayoutMovable);
            }
        });
        //动画结束后用户才能再次点击
//        t = new Timer();
//        Task5 task5 = new Task5();
//        t.schedule(task5, 400);
    }


    public static void close_layout(MyLayoutMovable myLayoutMovable,Title_Info layoutPosition){
            closeLayout.close(myLayoutMovable, layoutPosition);
    }

    static class Task3 extends TimerTask {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    myLayout.setVisibility(View.GONE);
                    //消失后回到原来的位置
                    move1(myLayout);
                    break;
//                case 1:
//                    myLayout2.setVisibility(View.GONE);
//                    //消失后回到原来的位置
//                    translateX(myLayout2, myLayout2.iPosition.beginX, 0);
//                    break;
//                case 2:
//                    MainFragment1.musicLayout.music.setEnabled(true);
//                    MainFragment1.appLayout.app.setEnabled(true);
//                    MainFragment1.weatherLayout.weatherlayout.setEnabled(true);
//                    MainFragment1.newsLayout.news_layout.setEnabled(true);
////                    MainFragment1.feixueLayout.feixue.setEnabled(true);
//                    break;
                case 3:
                    myLayout.setVisibility(View.GONE);
                    //消失后回到原来的位置
                    move1(myLayout);
//                    translateY(400,myLayout, myLayout.iPosition.y, -30);
                    break;
//                case 4:
//                    myLayout2.setVisibility(View.GONE);
//                    //消失后回到原来的位置
//                    translateX(myLayout2, myLayout2.iPosition.x, 0);
//                    translateY(400,myLayout2, myLayout2.iPosition.y, -30);
//                    break;
            }
        }
    };
}
