package adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by admin on 2017/12/14.
 */

public class BannerImageLoader extends ImageLoader{

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage((String) path,imageView);

    }
}
