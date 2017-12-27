package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bawei.newsapp.R;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import beans.Foods;

/**
 * Created by admin on 2017/12/9.
 */

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<Foods.ResultsBean> results;

    public NewsAdapter(Context context, List<Foods.ResultsBean> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int i) {
        return results.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        List<String> images = results.get(position).getImages();

        if (null!= images && !"".equals(images)){
            position = 1;
            return position;
        }else {
            position = 0;
            return position;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int position = getItemViewType(i);
        if (position == 1){
            ViewHolder1 holder = null;
            if (view == null){
                holder = new ViewHolder1();
                view = View.inflate(context, R.layout.list1_layout,null);
                holder.imageView = (ImageView) view.findViewById(R.id.img);
                holder.tv1 = (TextView) view.findViewById(R.id.tv1);
                holder.tv2 = (TextView) view.findViewById(R.id.tv2);
                view.setTag(holder);

            }else {
                holder = (ViewHolder1) view.getTag();
            }

            holder.tv1.setText(results.get(i).getDesc());
            holder.tv2.setText(results.get(i).getPublishedAt());

            //Glide.with(context).load(results.get(i).getImages().get(0)).into(holder.imageView);
            ImageLoader.getInstance().displayImage(results.get(i).getImages().get(0),holder.imageView);

        }else if (position == 0){

            ViewHolder2 holder = null;
            if (view == null){
                holder = new ViewHolder2();
                view = View.inflate(context, R.layout.list_layout,null);
                holder.tv1 = (TextView) view.findViewById(R.id.tv1);
                holder.tv2 = (TextView) view.findViewById(R.id.tv2);
                view.setTag(holder);

            }else {
                holder = (ViewHolder2) view.getTag();
            }

            holder.tv1.setText(results.get(i).getDesc());
            holder.tv2.setText(results.get(i).getPublishedAt());



        }




        return view;
    }

    class ViewHolder1{
        ImageView imageView;
        TextView tv1,tv2;


    }
    class ViewHolder2{
        TextView tv1,tv2;


    }
}
