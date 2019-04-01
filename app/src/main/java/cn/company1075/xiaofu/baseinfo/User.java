package cn.company1075.xiaofu.baseinfo;

public class User {

    public static String URL = "http://192.168.1.103:6060";
    public static String URLWeather = "http://116.62.158.95:8060";

    private static volatile User instance=null;
    public int shopid;
    public String name;
    public  String pwd;

    public User() {

    }




    public static  User getInstance() {
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null) {
                    instance = new User();
                }
            }
        }
        return instance;

    }
}
