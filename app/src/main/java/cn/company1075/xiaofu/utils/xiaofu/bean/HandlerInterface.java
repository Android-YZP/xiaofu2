package cn.company1075.xiaofu.utils.xiaofu.bean;

import android.os.Message;

public interface HandlerInterface<T> {
    void handleMessage(Message msg);

    void handleMessage(HttpResult<T> result);

    void handleMessage(T result);
}
