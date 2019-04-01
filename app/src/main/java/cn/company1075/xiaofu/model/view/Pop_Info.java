package cn.company1075.xiaofu.model.view;

import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.baseinfo.View_title;

/**
 * Created by Cxy on 2018/7/24.
 * UI，title的排版信息
 */

public class Pop_Info extends Title_Info {

    public Pop_Info(int x, int y,int width,int height){
        this.setBeginX(AllUser.spaceX*x);
        this.setBeginY(AllUser.spaceX*y);
        this.setTakeX(AllUser.spaceX*width);
        this.setTakeY(AllUser.spaceY*height);
    }
}
