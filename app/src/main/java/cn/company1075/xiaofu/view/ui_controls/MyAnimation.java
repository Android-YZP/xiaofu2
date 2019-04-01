package cn.company1075.xiaofu.view.ui_controls;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by liutianxu on 17/8/17.
 */

public class MyAnimation {


    public static void AnimationY(int duration, Object object, int startY, int endY){

        AnimatorSet animatorSet = new AnimatorSet();//组合动画

        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(new OvershootInterpolator());

        final ObjectAnimator translationUp = ObjectAnimator.ofFloat(object, "Y",
                startY, endY);

        animatorSet.play(translationUp);//两个动画同时开始
        animatorSet.start();
    }

    public static void AnimationX(int duration, Object object, int startX, int endX){

        AnimatorSet animatorSet = new AnimatorSet();//组合动画

        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(new OvershootInterpolator());

        final ObjectAnimator translationUp = ObjectAnimator.ofFloat(object, "X",
                startX, endX);

        animatorSet.play(translationUp);//两个动画同时开始
        animatorSet.start();
    }
}
