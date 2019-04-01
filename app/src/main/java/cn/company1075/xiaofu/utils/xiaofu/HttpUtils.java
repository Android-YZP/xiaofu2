package cn.company1075.xiaofu.utils.xiaofu;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

import cn.company1075.xiaofu.utils.xiaofu.bean.ESHandler;
import cn.company1075.xiaofu.utils.xiaofu.bean.HttpResult;
import cn.company1075.xiaofu.utils.xiaofu.bean.ThreadPools;


public class HttpUtils<T> {


    Context context;
    private String url;
    private ArrayMap<String, Object> arrayMap;
    private Class Class;
    private ESHandler handler;
    ESRunnable esRunnable;
    public final static int NETWORK_BAD = -44444;

    public HttpUtils(Context context, String url, ArrayMap<String, Object> arrayMap, Class<T> Class, ESHandler<T> handler) {
        this.context = context;
        this.url = url;
        this.arrayMap = arrayMap;
        this.Class = Class;
        this.handler = handler;
        esRunnable = new ESRunnable();
    }

    public void Start() {
        if (url == null) {
            try {
                if (arrayMap != null) {
                    arrayMap.clear();
                }
            } catch (Exception e) {
            }
            return;
        }
        ThreadPools.execute(esRunnable);
    }

    class ESRunnable implements Runnable {
        @Override
        public void run() {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            String resultString = "";
            HttpURLConnection urlConnection = null;
            try {
                URL urlObject = new URL(url);
                urlConnection = (HttpURLConnection) urlObject.openConnection();
                urlConnection.setDoInput(true); // 允许输入流，即允许下载
                urlConnection.setDoOutput(true); // 允许输出流，即允许上传
                urlConnection.setRequestMethod("POST"); // 使用post请求
                urlConnection.setUseCaches(false); // 不使用缓冲
                urlConnection.setConnectTimeout(5 * 15000);
                urlConnection.setReadTimeout(5 * 15000);
                urlConnection.setInstanceFollowRedirects(true);


                boolean isByte = false;
                if (arrayMap != null) {
                    int size = arrayMap.size();
                    for (int i = 0; i < size; i++) {
                        Object value = arrayMap.valueAt(i);
                        if (value == null) {
                            continue;
                        }
                        if (value.getClass().toString().equals("class [B")) {
                            isByte = true;
                            break;
                        }
                    }
                }
                if (isByte) {
                    String boundary = "--" + UUID.randomUUID().toString();
                    urlConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                    urlConnection.connect();

                    outputStream = urlConnection.getOutputStream();
                    DataOutputStream _dos = new DataOutputStream(outputStream);
                    int size = arrayMap.size();
                    for (int i = 0; i < size; i++) {
                        String key = arrayMap.keyAt(i);
                        Object value = arrayMap.valueAt(i);
                        if (value != null) {
                            if (value.getClass().toString().equals("class [B")) {
                                _dos.writeBytes("--" + boundary + "\r\n");
                                _dos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + key + "\"\r\n");
                                _dos.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
                                _dos.write((byte[]) value);
                                _dos.writeBytes("\r\n");
                            } else {
                                _dos.writeBytes("--" + boundary + "\r\n");
                                _dos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"; \r\n");
                                _dos.writeBytes("\r\n");
                                _dos.write(value.toString().getBytes("utf-8"));
                                _dos.writeBytes("\r\n");
                            }
                        }
                    }
                    _dos.writeBytes("--" + boundary + "--\r\n");
                    _dos.flush();
                    _dos.close();
                } else {
                    urlConnection.connect();
                    outputStream = urlConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(arrayMap));
                    writer.flush();
                    writer.close();
                }
                int _code = urlConnection.getResponseCode();
                if (_code == 200) {
                    inputStream = urlConnection.getInputStream();
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] data = new byte[1024];
                    int count = -1;
                    while ((count = inputStream.read(data)) != -1) {
                        outStream.write(data, 0, count);
                    }
                    byte[] resultArr = outStream.toByteArray();
                    resultString = new String(resultArr, "utf-8");
                    outStream.close();
                } else {
                    resultString = "1001" + _code;
                }
            } catch (MalformedURLException e) {
                resultString = "unknow : " + (e != null ? e.getMessage() : e);
            } catch (IOException e) {
                resultString = "unknow : " + (e != null ? e.getMessage() : e);
            } catch (Exception e) {
                resultString = "unknow : " + (e != null ? e.getMessage() : e);
            } finally {
                try {
                    ThreadPools.remove(esRunnable);
                } catch (Exception e) {
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                    }
                }
                if (urlConnection != null) {
                    try {
                        urlConnection.disconnect();
                    } catch (Exception e) {
                    }
                }
            }

            Log.e("httpStringResult", url + "           " + resultString);

            if (handler == null) {
                if (arrayMap != null) {
                    arrayMap.clear();
                }
                return;
            }
            onPostExecute(resultString);
        }
    }

    private String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("utf-8"), 0, text.length());
        sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    private String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        int length = data.length;
        for (int i = 0; i < length; ++i) {
            byte b = data[i];
            buf.append(String.format("%02x", b));
        }
        return buf.toString();
    }

    private String getQuery(ArrayMap<String, Object> arrayMap) throws UnsupportedEncodingException {
        if (arrayMap == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            String key = arrayMap.keyAt(i);
            Object value = arrayMap.valueAt(i);
            if (value == null) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }

    private void onPostExecute(String result) {
        HttpResult<T> httpResult = new HttpResult<T>();
        if (result == null || result.equals("")) {
            httpResult.Message = "";
            httpResult.Code = -50;
        } else if (result.startsWith("1001")) {
            httpResult.Message = result.replace("1001", "");
            httpResult.Code = -100;
        } else if (result.startsWith("unknow :")) {
            httpResult.Message = "";
            httpResult.Code = -300;
        } else {
            Gson gson = new Gson();
            Type objectType;
            if (Class == null) {
                objectType = type(HttpResult.class, ArrayList.class);
            } else {
                objectType = type(HttpResult.class, Class);
            }
            try {
                httpResult = gson.fromJson(result, objectType);
            } catch (JsonSyntaxException e) {
                objectType = type(HttpResult.class, String.class);
                try {
                    httpResult = gson.fromJson(result, objectType);
                } catch (JsonSyntaxException e2) {
                    try {
                        if (arrayMap != null) {
                            arrayMap.clear();
                        }
                    } catch (Exception e3) {
                    }
                    httpResult.Message = "";
                    httpResult.Code = -100;
                    handler.sendData(httpResult);
                    return;
                }
            }

        }
        handler.sendData(httpResult);
    }

    private ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

}
