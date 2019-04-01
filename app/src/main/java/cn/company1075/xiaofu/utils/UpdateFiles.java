package cn.company1075.xiaofu.utils;

import java.io.File;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UpdateFiles {
    public static Request getFileRequest(String url, File file, Map<String, String> maps){
        MultipartBody.Builder builder=  new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(maps==null){
            builder.addPart( Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(MediaType.parse("image/png"),file)
            ).build();

        }else{
            for (String key : maps.keySet()) {
                builder.addFormDataPart(key, maps.get(key));
            }

            builder.addPart( Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(MediaType.parse("image/png"),file)
            );

        }
        RequestBody body=builder.build();
        return   new Request.Builder().url(url).post(body).build();

    }

}
