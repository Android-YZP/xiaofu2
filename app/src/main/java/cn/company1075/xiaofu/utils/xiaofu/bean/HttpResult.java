package cn.company1075.xiaofu.utils.xiaofu.bean;

import java.io.Serializable;

public class HttpResult<T> implements Serializable {
    private static final long serialVersionUID = 1000000L;

    public String Message;
    public boolean Success;//true是成功
    public long SaleUserId = -1;//用户id  唯一标示
    /**
     * 1是成功
     */
    public int Code; //0是成功
    public T Data;
    public long T;

    public String UserCode;
    public String Photo;
}
