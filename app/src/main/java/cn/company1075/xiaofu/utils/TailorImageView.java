package cn.company1075.xiaofu.utils;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

public class TailorImageView implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {

        //得到原图片的大小,取最小值
        int size = Math.min(source.getWidth(), source.getHeight());
        //长大于宽，还是宽大于长
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        //创建新的bitmap
        Bitmap bitmap = Bitmap.createBitmap(source, x, y, size, size);

        if (bitmap != source) {
            //回收
            source.recycle();
        }
        return bitmap;
    }

    @Override
    public String key() {

        return "lgl";
    }


}
