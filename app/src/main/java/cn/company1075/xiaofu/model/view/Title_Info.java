package cn.company1075.xiaofu.model.view;

/**
 * Created by Cxy on 2018/7/24.
 * UI，title的排版信息
 */

public class Title_Info {
    private View_Name name;
    private int id;
    private int beginX;
    private int beginY;
    private int takeX;
    private int takeY;

    public Title_Info(){

    }

    public View_Name getName() {
        return name;
    }

    public void setName(View_Name name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeginX() {
        return beginX;
    }

    public void setBeginX(int beginX) {
        this.beginX = beginX;
    }

    public int getBeginY() {
        return beginY;
    }

    public void setBeginY(int beginY) {
        this.beginY = beginY;
    }

    public int getTakeX() {
        return takeX;
    }

    public void setTakeX(int takeX) {
        this.takeX = takeX;
    }

    public int getTakeY() {
        return takeY;
    }

    public void setTakeY(int takeY) {
        this.takeY = takeY;
    }
}
