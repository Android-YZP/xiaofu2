package cn.company1075.xiaofu.baseinfo;


import java.util.ArrayList;
import java.util.List;

import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.model.view.View_Name;

/**
 * Created by Cxy on 2018/7/24.
 * view title的ui属性参数
 */

public class View_title {

    /**
     * Created by  on 2017/3/1.
     */
    private List<Title_Info> title_infos;

    private int width;
    private int height;
    private int space_width;
    private int space_height;
    private int widthNum = 64;
    private int heightNum = 36;

    public View_title(int width,int height) {
        title_infos = new ArrayList<>();
        this.width = width;
        this.height = height;

        //单个控件的间隔长度
        this.space_width = width/widthNum;
        this.space_height = height/heightNum;

        //设置UI相对应的部件位置
        //时间
        createInto(0, View_Name.TITLE_TIME,0,1,7,5);
        //天气
        createInto(1,View_Name.TITLE_WEATHER,7,1,12,5);
        //测肤
        createInto(2,View_Name.TITLE_XIAOFU,19,1,4,5);
        //产品
        createInto(3,View_Name.TITLE_PRODUCT,23,1,4,5);
        //设置
        createInto(4,View_Name.TITLE_SETTING,27,1,4,5);

    }

    private void createInto(int id , View_Name name, int item_x, int item_y, int item_width, int item_height){
        Title_Info titleInfo= new Title_Info();
        titleInfo.setId(id);
        titleInfo.setName(name);
        titleInfo.setBeginX(space_width*item_x+space_width*(id+1));
        titleInfo.setBeginY(space_height*item_y);
        titleInfo.setTakeX(space_width*item_width);
        titleInfo.setTakeY(space_height*item_height);
        title_infos.add(titleInfo);
    }


    public List<Title_Info> getTitle_infos() {
        return title_infos;
    }

    public int getSpace_width() {
        return space_width;
    }

    public int getSpace_height() {
        return space_height;
    }
}
