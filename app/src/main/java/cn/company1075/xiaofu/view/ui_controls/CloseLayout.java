package cn.company1075.xiaofu.view.ui_controls;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.model.view.Title_Info;

/**
 * Created by liutianxu on 17/9/19.
 */

public class CloseLayout {

    private MyLayoutMovable myLayoutMovable;
    private Title_Info iPosition;
    private int record;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    myLayoutMovable.setVisibility(View.GONE);
                    AllUser.popup_info.remove(myLayoutMovable);
                    break;
                case 1:
                    myCallInterface.action_up(myLayoutMovable);
                    break;
            }
        }
    };

    public CloseLayout() {

    }

    public void close(MyLayoutMovable myLayoutMovable, Title_Info iPosition){
        this.myLayoutMovable = myLayoutMovable;
        this.iPosition = iPosition;
        record = AllUser.usable_area;
        MyAnimation.AnimationY(400,myLayoutMovable,0,-840);
        Timer t = new Timer();
        task tt = new task();
        task2 tt2 = new task2();
        t.schedule(tt, 300);//5000单位是毫秒=5秒
        t.schedule(tt2, 400);
    }

    class task extends TimerTask {
        public void run() {
            if (record == AllUser.usable_area) {
                AllUser.usable_area = AllUser.usable_area + iPosition.getTakeX();
                AllUser.used_area = AllUser.used_area - iPosition.getTakeX();
            }
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

    private MyLayoutMovable.MyCallInterface myCallInterface;

    public void setMyCallInterface(MyLayoutMovable.MyCallInterface myCallInterface) {
        this.myCallInterface = myCallInterface;
    }
}
