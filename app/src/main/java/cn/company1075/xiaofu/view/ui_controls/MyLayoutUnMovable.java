package cn.company1075.xiaofu.view.ui_controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.model.view.View_Name;
import cn.company1075.xiaofu.utils.Utils_UI;


/**
 * Created by liutianxu on 17/2/16.
 */

//编辑状态与正常状态都不能拖动
public class MyLayoutUnMovable extends RelativeLayout {


    private RelativeLayout.LayoutParams mParams;
    private View mLayoutView;
    private Title_Info iposition;

    //记录手指按下时在小悬浮窗的View上的纵坐标的值
    private float xInView;
    private float yInView;

    //记录手指按下时在屏幕上的坐标的值
    private float xDownInScreen;
    private float yDownInScreen;

    //记录当前手指位置在屏幕上的坐标值
    private float xInScreen;
    private float yInScreen;

    //只赋值一次，记录按下时候的X,Y坐标，不改变
    private float downX;
    private float downY;



    //动画
    private Animation animation_big;
    private Animation animation_small;
    private boolean isScaled;
    //抖动动画
    Animation shakeAnimation;

    public View_Name type;


    public MyLayoutUnMovable(Context context, int viewInt, Title_Info position) {
        super(context);
        this.type = position.getName();
        mLayoutView = LayoutInflater.from(context).inflate(viewInt, this);
        mParams = new RelativeLayout.LayoutParams(position.getTakeX(), position.getTakeY());
        mParams.topMargin = position.getBeginY();
        mParams.leftMargin = position.getBeginX();
        this.type = position.getName();
        //动画
        shakeAnimation = AnimationUtils.loadAnimation(context, R.anim.shake);
        animation_big = AnimationUtils.loadAnimation(context, R.anim.scale_layout_big);
        animation_big.setStartOffset(500);
        animation_big.setFillAfter(true);
        animation_small = AnimationUtils.loadAnimation(context, R.anim.scale_layout_small);
        animation_small.setFillAfter(true);
        isScaled = false;
    }


   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
                xInView = event.getX();
                yInView = event.getY();

                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY();
                downX = xInScreen = event.getRawX();
                downY = yInScreen = event.getRawY();

                if (Utils_UI.isIncludeTouch((int) xInScreen, (int) yInScreen, (int) xDownInScreen, (int) yDownInScreen)) {
                    playAnimation(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                playAnimation(false);
                this.startAnimation(shakeAnimation);
                break;
        }
        return true;
    }
*/

    /**
     * 播放动画。
     */
   /* private void playAnimation(boolean isBig) {
        this.clearAnimation();
        if (isBig) {
            if (isScaled)
                return;
            this.setAnimation(animation_big);
            this.startAnimation(animation_big);
            isScaled = true;
        } else {
            if (!isScaled)
                return;
            this.setAnimation(animation_small);
            this.startAnimation(animation_small);
            isScaled = false;
        }

    }*/

    public View getmLayoutView() {
        return mLayoutView;
    }

    public LayoutParams getmParams() {
        return mParams;
    }

    public Title_Info getIposition() {
        return iposition;
    }
}
