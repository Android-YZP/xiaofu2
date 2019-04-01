package cn.company1075.xiaofu.utils;

public class MyResult {

    /**
     * code : 200
     * msg : 添加成功
     * count : 0
     * data : null
     * url : null
     * id : null
     */

    private int code;
    private String msg;
    private int count;
    private Object data;
    private Object url;
    private Object id;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
