package cn.company1075.xiaofu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.DocumentsContract;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

import javax.xml.transform.Result;

import cn.company1075.xiaofu.baseinfo.User;
import cn.company1075.xiaofu.view.fragment.Page_One;
import xiaofu.xflibrary.ble.bean.XFBleScanDevice;

public class SPUtils {

    public static final String FILE_NAME = "share_data";
              public static void put(Context context, String key, Object object)
     {

              SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                                 Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                if (object instanceof String)
                 {
                       editor.putString(key, (String) object);
                     } else if (object instanceof Integer)
                   {
                         editor.putInt(key, (Integer) object);
                  } else if (object instanceof Boolean)
                    {
                       editor.putBoolean(key, (Boolean) object);
                     } else if (object instanceof Float)
                 {
                        editor.putFloat(key, (Float) object);
                   } else if (object instanceof Long)
                  {
                      editor.putLong(key, (Long) object);
                   } else
                {
                      editor.putString(key, object.toString());
                    }

                SharedPreferencesCompat.apply(editor);
            }

            /**
      * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
      * @param defaultObject
     * @return
     */
         public static Object get(Context context, String key, Object defaultObject)
     {
            SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                             Context.MODE_PRIVATE);

                if (defaultObject instanceof String)
                    {
                      return sp.getString(key, (String) defaultObject);
                  } else if (defaultObject instanceof Integer)
                     {
                     return sp.getInt(key, (Integer) defaultObject);
                    } else if (defaultObject instanceof Boolean)
                    {
                     return sp.getBoolean(key, (Boolean) defaultObject);
                  } else if (defaultObject instanceof Float)
                 {
                       return sp.getFloat(key, (Float) defaultObject);
                   } else if (defaultObject instanceof Long)
                   {
                         return sp.getLong(key, (Long) defaultObject);
                 }

             return null;
          }

              /**
    * 移除某个key值已经对应的值
    * @param context
       * @param key
        */
           public static void remove(Context context, String key)
    {
                SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                               Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
              editor.remove(key);
               SharedPreferencesCompat.apply(editor);
          }

            /**
     * 清除所有数据
     * @param context
      */
           public static void clear(Context context)
    {
                SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                              Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = sp.edit();
          editor.clear();
               SharedPreferencesCompat.apply(editor);
        }

          /**
     * 查询某个key是否已经存在
    * @param context
     * @param key
     * @return
     */
          public static boolean contains(Context context, String key)
     {
              SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                             Context.MODE_PRIVATE);
              return sp.contains(key);
           }

             /**
    * 返回所有的键值对
     *
      * @param context
      * @return
     */
            public static Map<String, ?> getAll(Context context)
    {
                SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                                Context.MODE_PRIVATE);
               return sp.getAll();
            }

            /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
    *
     * @author zhy
      *
     */
           private static class SharedPreferencesCompat
    {
                 private static final Method sApplyMethod = findApplyMethod();

              /**
                 * 反射查找apply的方法
               *
                  * @return
                */
           @SuppressWarnings({ "unchecked", "rawtypes" })
              private static Method findApplyMethod()
              {
                     try
                   {
                           Class clz = SharedPreferences.Editor.class;
                           return clz.getMethod("apply");
                          } catch (NoSuchMethodException e)
                     {
                        }

                   return null;
               }

           /**
                 * 如果找到则使用apply执行，否则使用commit
               *
                 * @param editor
            */
            public static void apply(SharedPreferences.Editor editor)
             {
                       try
                 {
                               if (sApplyMethod != null)
                                  {
                                    sApplyMethod.invoke(editor);
                                   return;
                                 }
                       } catch (IllegalArgumentException e)
                         {
                           } catch (IllegalAccessException e)
                     {
                        } catch (InvocationTargetException e)
                    {
                          }
                      editor.commit();
                   }
           }



     public static void putBean(Context context, String key, Object object){
               SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
               Gson gson = new Gson();
               String jsonStr=gson.toJson(object);
               SharedPreferences.Editor editor = sp.edit() ;
               editor.putString(key, jsonStr) ; //存入json串
               editor.commit() ; //提交
           }

    public static User getUser(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        String jsonStr=sp.getString(key,"" );
        LogInfo.i("JSON", jsonStr);

        if(jsonStr!="")
        {
            Gson gson = new Gson();
            Type userType = new TypeToken<User>(){}.getType();//用于获取泛型信息
            User root=gson.fromJson(jsonStr, userType);
            return  root;
        }else {
            return  null;
        }

    }

    public static String getSkinDetailjsonStr(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        String jsonStr=sp.getString(key,"" );
        LogInfo.i("JSON", jsonStr);
        if(jsonStr!="")
        {
            return  jsonStr;
        }else {
            return  null;
        }

    }

    public static Page_One getPage_one(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        String jsonStr=sp.getString(key,"" );
        LogInfo.i("JSON", jsonStr);

        if(jsonStr!="")
        {
            Gson gson = new Gson();
            Type userType = new TypeToken<Page_One>(){}.getType();//用于获取泛型信息
            Page_One root=gson.fromJson(jsonStr, userType);
            return  root;
        }else {
            return  null;
        }

    }

    public static SkinDeatil getSkinDetail(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        String jsonStr=sp.getString(key,"" );
        LogInfo.i("JSON", jsonStr);

        if(jsonStr!="")
        {
            Gson gson = new Gson();
            Type userType = new TypeToken<SkinDeatil>(){}.getType();//用于获取泛型信息
            SkinDeatil root=gson.fromJson(jsonStr, userType);
            return  root;
        }else {
            return  null;
        }

    }


    public static XFBleScanDevice getXFBleScanDevice(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        String jsonStr=sp.getString(key,"" );
        LogInfo.i("JSON", jsonStr);

        if(jsonStr!="")
        {
            Gson gson = new Gson();
            Type userType = new TypeToken<XFBleScanDevice>(){}.getType();//用于获取泛型信息
            XFBleScanDevice root=gson.fromJson(jsonStr, userType);
            return  root;
        }else {
            return  null;
        }

    }

    public static void removeBean(Context context,String key){
    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);//创建sp对象,如果有key为"SP_PEOPLE"的sp就取出
    sp.getString(key, "");
    SharedPreferences.Editor editor = sp.edit() ;
    editor.clear();
    editor.commit();

}







}
