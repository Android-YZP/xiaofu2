package cn.company1075.xiaofu.view.title;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.baseinfo.AllUser;
import cn.company1075.xiaofu.model.view.Pop_Info;
import cn.company1075.xiaofu.model.view.Title_Info;
import cn.company1075.xiaofu.view.popup.Popup_xiaofu;
import cn.company1075.xiaofu.view.ui_controls.MyLayoutUnMovable;


/**
 * Created by Administrator on 2017/1/10.
 */
public class Title_Xiaofu extends MyLayoutUnMovable{

    private static final String TAG = "Title_Time";

    private Context context;
    private Popup_xiaofu popup_xiaofu;
    @BindView(R.id.title_xiaofu)
   public RelativeLayout title_xiaofu;


    public Title_Xiaofu(Context context,Title_Info position) {
        super(context, R.layout.title_xiaofu, position);
        this.context = context;
        ButterKnife.bind(this);
        //添加至主界面中
        Pop_Info pop_info = new Pop_Info(22,0, 20, 28);
        popup_xiaofu = new Popup_xiaofu(context, pop_info, "xiaofu_pop");
        AllUser.parent.addView(popup_xiaofu,popup_xiaofu.mParams);
        popup_xiaofu.setVisibility(View.GONE);
    }

    @OnClick({R.id.title_xiaofu})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_xiaofu:
                AllUser.initPopup_layout(popup_xiaofu);
                break;

        }


    }

}
