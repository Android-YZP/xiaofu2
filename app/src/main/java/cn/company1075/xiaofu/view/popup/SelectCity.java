package cn.company1075.xiaofu.view.popup;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.company1075.xiaofu.R;
import cn.company1075.xiaofu.model.view.Selectcity;
import cn.company1075.xiaofu.view.adapter.IndexAdapter;
import cn.company1075.xiaofu.view.ui_controls.IndexView;


/**
 * Created by Administrator on 2017/1/15.
 */
public class SelectCity extends Activity {

    @BindView(R.id.city_listview)
    ListView cityListview;
    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.iv_words)
    IndexView ivWords;
    String[] select_city = {"上海", "北京", "杭州", "广州", "南京", "苏州", "深圳", "成都", "重庆", "天津",
            "宁波", "扬州", "无锡", "福州", "厦门", "武汉", "西安", "沈阳", "大连", "青岛", "济南", "海口", "石家庄",
            "唐山", "秦皇岛", "邯郸", "邢台", "保定", "张家口", "承德", "沧州", "廊坊", "衡水", "太原", "大同", "阳泉",
            "长治", "晋城", "朔州", "晋中", "运城", "忻州", "临汾", "吕梁", "呼和浩特", "包头", "乌海", "赤峰", "通辽",
            "鞍山", "抚顺", "本溪",
            "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛", "长春", "吉林", "四平", "辽源",
            "通化", "白山", "松原", "白城", "哈尔滨", "齐齐哈尔", "鸡西","大庆", "伊春",
            "七台河", "牡丹江", "黑河", "绥化", "大兴安岭", "徐州", "常州", "南通", "连云港", "淮安", "盐城",
            "镇江", "泰州", "宿迁", "温州", "嘉兴", "湖州", "绍兴", "金华", "衢州", "舟山", "台州", "丽水", "合肥", "芜湖",
            "淮南", "马鞍山", "淮北", "铜陵", "安庆", "黄山", "滁州", "阜阳", "宿州", "六安", "亳州", "池州", "宣城",
            "莆田", "三明", "泉州", "漳州", "南平", "龙岩", "宁德", "南昌", "景德镇", "萍乡", "九江", "新余", "赣州",
            "吉安", "宜春", "抚州", "上饶", "淄博", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "莱芜",
            "临沂", "德州", "聊城", "滨州", "菏泽", "郑州", "开封", "洛阳", "平顶山", "安阳", "鹤壁", "新乡", "焦作",
            "濮阳", "许昌", "漯河", "三门峡", "南阳", "商丘", "信阳", "周口", "驻马店", "黄石", "十堰", "宜昌", "襄阳",
            "鄂州", "荆门", "孝感", "荆州", "黄冈", "咸宁", "随州", "潜江", "天门", "株洲", "湘潭", "衡阳", "邵阳",
            "岳阳", "常德", "张家界", "益阳", "郴州", "永州", "怀化", "娄底","韶关", "珠海", "汕头", "佛山", "江门", "湛江",
            "茂名", "肇庆", "惠州", "梅州", "汕尾", "河源", "阳江", "清远", "东莞", "中山", "潮州", "揭阳", "云浮", "南宁", "柳州", "桂林",
            "梧州", "北海", "防城港", "钦州", "贵港", "玉林", "百色", "贺州", "河池", "自贡", "攀枝花", "泸州", "德阳", "绵阳", "广元", "遂宁",
            "内江", "乐山", "南充", "眉山", "宜宾", "广安", "达州", "雅安", "巴中", "资阳","贵阳", "六盘水", "遵义",
            "安顺", "昆明", "曲靖", "玉溪", "保山",
            "普洱", "德宏", "丽江", "怒江", "临沧", "拉萨", "山南", "那曲", "阿里", "铜川", "宝鸡",
            "咸阳", "渭南", "延安", "汉中", "榆林", "安康", "商洛", "兰州", "金昌", "白银", "天水", "武威", "张掖", "平凉", "酒泉",
            "庆阳", "定西", "甘南", "西宁", "海东", "海北", "黄南", "果洛", "玉树", "海西", "银川", "石嘴山", "吴忠", "固原",
            "乌鲁木齐","石河子", "香港", "澳门", "长沙", "三亚", "济源", "来宾" };
    private Handler handler = new Handler();
    /**
     * 联系人的集合
     */
    private ArrayList<Selectcity> selectcity;
    private IndexAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        ButterKnife.bind(this);
        ivWords.setOnIndexChangeListener(new MyOnIndexChangeListener());
        //准备数据
        initData();
        //设置适配器
        adapter = new IndexAdapter(this, selectcity,this);
        cityListview.setAdapter(adapter);
    }


    class MyOnIndexChangeListener implements IndexView.OnIndexChangeListener {

        @Override
        public void onIndexChange(String word) {
            updateWord(word);
            updateListView(word);//A~Z
        }

        private void updateListView(String word) {
            for (int i = 0; i < selectcity.size(); i++) {
                String listWord = selectcity.get(i).getPinyin().substring(0, 1);//YANGGUANGFU-->Y
                if (word.equals(listWord)) {
                    //i是listView中的位置
                    cityListview.setSelection(i);//定位到ListVeiw中的某个位置
                    return;
                }
            }
        }

        private void updateWord(String word) {
            //显示
            tvWord.setVisibility(View.VISIBLE);
            tvWord.setText(word);
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //也是运行在主线程
                    System.out.println(Thread.currentThread().getName() + "------------");

                    tvWord.setVisibility(View.GONE);
                }
            }, 3000);
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {

        selectcity = new ArrayList<>();
        for (int i = 0; i < select_city.length; i++){
            selectcity.add(new Selectcity(select_city[i]));
        }

//        //排序
        Collections.sort(selectcity, new Comparator<Selectcity>() {
            @Override
            public int compare(Selectcity lhs, Selectcity rhs) {
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });

    }

    @Override
    protected void onDestroy() {
        System.gc();
        finish();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
