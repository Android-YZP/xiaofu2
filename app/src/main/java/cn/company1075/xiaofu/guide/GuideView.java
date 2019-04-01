package cn.company1075.xiaofu.guide;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.utils.LogInfo;


public class GuideView extends RelativeLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    private final String TAG = getClass().getSimpleName();
    private Context mContext;
    private boolean first = true;
    /**
     * targetView前缀。版本号 + targetView.getId()作为保存在SP文件的key。
     */
    private final String SHOW_GUIDE_PREFIX = "v" + getVersionName() + "_";
    /**
     * GuideView 偏移量
     */
    private int offsetX, offsetY;
    /**
     * targetView 的外切圆半径
     */
    private int radius;
    /**
     * 需要显示提示信息的View
     */
    private View targetView;
    /**
     * 自定义View
     */
    private View customGuideView;
    /**
     * 透明圆形画笔
     */
    private Paint mCirclePaint;
    /**
     * 背景色画笔
     */
    private Paint mBackgroundPaint;
    /**
     * targetView是否已测量
     */
    private boolean isMeasured;
    /**
     * targetView圆心
     */
    private int[] center;
    /**
     * 绘图层叠模式
     */
    private PorterDuffXfermode porterDuffXfermode;
    /**
     * 绘制前景bitmap
     */
    private Bitmap bitmap;
    /**
     * 背景色和透明度，格式 #aarrggbb
     */
    private int backgroundColor;
    /**
     * Canvas,绘制bitmap
     */
    private Canvas temp;
    /**
     * 相对于targetView的位置.在target的那个方向
     */
    private Direction direction;

    /**
     * 形状
     */
    private MyShape myShape;
    /**
     * targetView左上角坐标
     */
    private int[] location;
    /**
     * 版本号
     */
    private String versionName;

    private boolean onClickExit;
    private OnClickCallback onclickListener;
    private RelativeLayout guideViewLayout;
    private boolean showOnce;
    private OnClickCallback listener;

    public String getVersionName() {
        try {
            PackageManager packageManager = getContext().getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "v0";
        }
    }

    public void restoreState() {
        Log.v(TAG, "restoreState");
        offsetX = offsetY = 0;
        radius = 0;
        mCirclePaint = null;
        mBackgroundPaint = null;
        isMeasured = false;
        center = null;
        porterDuffXfermode = null;
        bitmap = null;
        temp = null;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public GuideView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setShape(MyShape shape) {
        this.myShape = shape;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setCustomGuideView(View customGuideView) {
        this.customGuideView = customGuideView;
        if (!first) {
            restoreState();
        }
    }

    public void setBgColor(int background_color) {
        this.backgroundColor = background_color;
    }

    public View getTargetView() {
        return targetView;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
        //        restoreState();
        if (!first) {
            //            guideViewLayout.removeAllViews();
        }
    }

    private void init() {
    }

    public boolean isShowOnce() {
        return showOnce;
    }

    public void setShowOnce(boolean showOnce) {
        this.showOnce = showOnce;
    }

    private boolean hasShown() {
        if (targetView == null)
            return true;
        return mContext.getSharedPreferences(TAG, Context.MODE_PRIVATE).getBoolean(generateUniqId(targetView), false);
    }

    private String generateUniqId(View v) {
        return SHOW_GUIDE_PREFIX + v.getId();
    }

    public int[] getCenter() {
        return center;
    }

    public void setCenter(int[] center) {
        this.center = center;
    }

    public void hide() {
        Log.v(TAG, "hide");
        if (customGuideView != null) {
            targetView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.removeAllViews();
            ((FrameLayout) ((Activity) mContext).getWindow().getDecorView()).removeView(this);
            restoreState();
        }
    }

    public boolean show() {
        Log.v(TAG, "show");
        if (versionName.equals(getVersionName())) {
            if (isShowOnce()) {
                if (!hasShown()) {
                    if (targetView != null) {
                        showHint();
                        mContext.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit().putBoolean(generateUniqId(targetView), true).commit();
                        return true;
                    }
                }
                return false;
            } else {
                showHint();
                return true;
            }
        } else {
            return false;
        }
    }

    private void showHint() {
        if (targetView != null) {
            targetView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        this.setBackgroundResource(R.color.transparent);

        ((FrameLayout) ((Activity) mContext).getWindow().getDecorView()).addView(this);
        first = false;
    }

    /**
     * 添加提示文字，位置在targetView的下边
     * 在屏幕窗口，添加蒙层，蒙层绘制总背景和透明圆形，圆形下边绘制说明文字
     */
    private void createGuideView() {
        Log.v(TAG, "createGuideView");

        // 添加到蒙层
        //        if (guideViewLayout == null) {
        //            guideViewLayout = new RelativeLayout(mContext);
        //        }

        // Tips布局参数
        LayoutParams guideViewParams;
        guideViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        guideViewParams.setMargins(0, center[1] + radius + 10, 0, 0);

        if (customGuideView != null) {

            //            LayoutParams guideViewParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (direction != null) {
                int width = this.getWidth();
                int height = this.getHeight();

                int left = center[0] - radius;
                int right = center[0] + radius;
                int top = center[1] - radius;
                int bottom = center[1] + radius;
                switch (direction) {
                    case LEFT2:
                        this.setGravity(Gravity.RIGHT);
                        guideViewParams.setMargins(-700, 600, 1200, 180);
                        break;
                    case TOP:
                        this.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                        guideViewParams.setMargins(offsetX, offsetY - height + top, -offsetX, height - top - offsetY);
                        break;
                    case LEFT:
                        this.setGravity(Gravity.RIGHT);
                        guideViewParams.setMargins(offsetX - width + left, top + offsetY, width - left - offsetX, -top - offsetY);
                        break;
                    case BOTTOM:
                        this.setGravity(Gravity.CENTER_HORIZONTAL);
                        guideViewParams.setMargins(offsetX, bottom + offsetY, -offsetX, -bottom - offsetY);
                        break;
                    case RIGHT:
                        guideViewParams.setMargins(right + offsetX, top + offsetY, -right - offsetX, -top - offsetY);
                        break;
                    case LEFT_TOP:
                        this.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
                        guideViewParams.setMargins(offsetX - width + left, offsetY - height + top, width - left - offsetX, height - top - offsetY);
                        break;
                    case LEFT_BOTTOM:
                        this.setGravity(Gravity.RIGHT);
                        guideViewParams.setMargins(offsetX - width + left, bottom + offsetY, width - left - offsetX, -bottom - offsetY);
                        break;
                    case RIGHT_TOP:
                        this.setGravity(Gravity.BOTTOM);
                        guideViewParams.setMargins(right + offsetX, offsetY - height + top, -right - offsetX, height - top - offsetY);
                        break;
                    case RIGHT_BOTTOM:
                        guideViewParams.setMargins(right + offsetX, bottom + offsetY, -right - offsetX, -top - offsetY);
                        break;
                }
            } else {
                guideViewParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                guideViewParams.setMargins(offsetX, offsetY, -offsetX, -offsetY);
            }

            //            guideViewLayout.addView(customGuideView);

            this.addView(customGuideView, guideViewParams);
        }
    }

    /**
     * 获得targetView 的宽高，如果未测量，返回｛-1， -1｝
     *
     * @return
     */
    private int[] getTargetViewSize() {
        int[] location = {-1, -1};
        if (isMeasured) {
            location[0] = targetView.getWidth();
            location[1] = targetView.getHeight();
        }
        return location;
    }

    /**
     * 获得targetView 的半径
     *
     * @return
     */
    private int getTargetViewRadius() {
        if (isMeasured) {
            int[] size = getTargetViewSize();
            int x = size[0];
            int y = size[1];

            return (int) (Math.sqrt(x * x + y * y) / 2-10);
        }
        return -1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isMeasured) {
            return;
        }
        if (targetView == null) {
            return;
        }
        drawBackground(canvas);
    }

    private void drawBackground(Canvas canvas) {
        // 先绘制bitmap，再将bitmap绘制到屏幕
        bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        temp = new Canvas(bitmap);

        // 背景画笔
        Paint bgPaint = new Paint();
        if (backgroundColor != 0) {
            bgPaint.setColor(backgroundColor);
        } else {
            bgPaint.setColor(ContextCompat.getColor(getContext(), R.color.shadow));
        }
        bgPaint.setAlpha(200);

        // 绘制屏幕背景
        temp.drawRect(0, 0, temp.getWidth(), temp.getHeight(), bgPaint);

        // targetView 的透明圆形画笔
        if (mCirclePaint == null)
            mCirclePaint = new Paint();
       // temp.drawColor(0x00000000);//画布颜色
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);// 或者CLEAR  SRC_OUT
        mCirclePaint.setXfermode(porterDuffXfermode);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(ContextCompat.getColor(getContext(), R.color.transparent));//高亮部分颜色

        if (myShape != null) {
            RectF oval = new RectF();
            switch (myShape) {
                case CIRCULAR://圆形
                    temp.drawCircle(center[0], center[1], radius, mCirclePaint);//绘制圆形
                    break;
                    case CIRCULAR2:
                        temp.drawCircle(center[0], center[1], 50, mCirclePaint);//绘制圆形
                        break;
                case CIRCULAR3:
                    temp.drawCircle(720, 200, 80, mCirclePaint);//绘制圆形
                    break;
                case ELLIPSE://椭圆
                    //RectF对象
                    oval.left = 720  ;                              //左边
                    oval.top = 575 ;                              //上边
                    oval.right = 1200 ;                             //右边
                    oval.bottom = 685 ;                           //下边
                    temp.drawOval(oval, mCirclePaint);                   //绘制椭圆
                    break;
                case RECTANGULAR://圆角矩形
                    //RectF对象
                    oval.left = targetView.getWidth()*2 / 3 ;                              //左边
                    oval.top = targetView.getHeight()* 2/ 3 ;                              //上边
                    oval.right = targetView.getWidth()*2 / 3;                             //右边
                    oval.bottom =  targetView.getHeight()*2 / 3 ;                           //下边
                    temp.drawRoundRect(oval, 10, 10, mCirclePaint);                   //绘制圆角矩形
                    break;
                default:
            }
        } else {
            temp.drawCircle(center[0]-50, center[1]-50, radius, mCirclePaint);//绘制圆形
        }

        // 绘制到屏幕
        canvas.drawBitmap(bitmap, 0, 0, bgPaint);
        bitmap.recycle();
    }

    public void setClickListener(final OnClickCallback listener) {
        this.listener = listener;
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGuideViewClicked();
            }
        });
    }

    @Override
    public void onGlobalLayout() {
        if (isMeasured) {
            return;
        }
        if (targetView.getHeight() > 0 && targetView.getWidth() > 0) {
            isMeasured = true;
        }

        // 获取targetView的中心坐标
        if (center == null) {
            // 获取右上角坐标
            location = new int[2];
            targetView.getLocationInWindow(location);
            center = new int[2];
            // 获取中心坐标
            center[0] = location[0] + targetView.getWidth() / 2;
            center[1] = location[1] + targetView.getHeight() / 2;
        }
        // 获取targetView外切圆半径
        if (radius == 0) {
            radius = getTargetViewRadius();
        }
        // 添加GuideView
        createGuideView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return listener == null ? true : super.onTouchEvent(event);
    }

    /**
     * 定义GuideView相对于targetView的方位，共八种。不设置则默认在targetView下方
     */
    public enum Direction {
        LEFT, TOP, RIGHT, BOTTOM,
        LEFT_TOP, LEFT_BOTTOM,
        RIGHT_TOP, RIGHT_BOTTOM,

        LEFT2,
    }

    /**
     * 定义目标控件的形状，共3种。圆形，椭圆，带圆角的矩形（可以设置圆角大小），不设置则默认是圆形
     */
    public enum MyShape {
        CIRCULAR, ELLIPSE, RECTANGULAR,CIRCULAR2,CIRCULAR3
    }

    /**
     * GuideView点击Callback
     */
    public interface OnClickCallback {
        void onGuideViewClicked();
    }

    public static class Builder {
        Context mContext;
        private List<GuideView> viewList = new ArrayList<>();
        private int currentIndex = 0;
        private boolean isDebug;
        private float textSize = 0f;
        private String versionName;

        private Builder() {
        }

        public Builder(Context context, String versionName) {
            mContext = context;
            this.versionName = versionName;
        }

        public Builder(Context context, String versionName, boolean isDebug) {
            mContext = context;
            this.versionName = versionName;
            this.isDebug = isDebug;
        }

        public Builder setTextSize(float textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder addHintView(View targetView, int res,Direction dir, MyShape shape, int xOffset, int yOffset, OnClickCallback listener) {
            RelativeLayout rv = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.guide_layout, null);
            ImageView know = rv.findViewById(R.id.guide_img);
            know.setImageResource(res);
            GuideView guideView = new GuideView(mContext);
            guideView.setTargetView(targetView);
            guideView.setCustomGuideView(rv);
            guideView.setDirection(dir);
            guideView.setShape(shape);
            guideView.setClickListener(listener);
            guideView.setVersionName(versionName);
            guideView.setOffsetX(xOffset);
            guideView.setOffsetY(yOffset);
            guideView.setShowOnce(isDebug ? false : true);
            viewList.add(guideView);
            return this;
        }

//        public Builder addHintView(View targetView, View hintView, Direction dir, MyShape shape, OnClickCallback listener) {
//            GuideView guideView = new GuideView(mContext);
//            guideView.setTargetView(targetView);
//            guideView.setCustomGuideView(hintView);
//            guideView.setDirection(dir);
//            guideView.setShape(shape);
//            guideView.setVersionName(versionName);
//            guideView.setClickListener(listener);
//            guideView.setShowOnce(isDebug ? false : true);
//            viewList.add(guideView);
//            return this;
//        }

        public void show() {
            if (viewList.size() > currentIndex) {
                if (!viewList.get(currentIndex).show()) {
                    currentIndex++;
                    show();
                }
            } else {
                viewList.clear();
                viewList = null;
            }
        }

        public void showNext() {
            viewList.get(currentIndex).hide();
            if (++currentIndex < viewList.size()) {
                viewList.get(currentIndex).show();
            } else {
                viewList.clear();
                viewList = null;
            }
        }
    }
}
