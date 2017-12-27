package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.newsapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.List;

import beans.NewsBeans;

/**
 * Created by admin on 2017/12/17.
 */

public class MyAdapter extends BaseAdapter {
private Context context;
private List<NewsBeans.DataBean> data;

    public MyAdapter(Context context, List<NewsBeans.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view==null){
            view= View.inflate(context,R.layout.grid_layout, null);
            holder=new ViewHolder();
            holder.img=(ImageView) view.findViewById(R.id.image_src);
            holder.title=(TextView) view.findViewById(R.id.text_title);
            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }

        ImageLoader.getInstance().displayImage(data.get(i).getPic_url(),holder.img);
       holder.title.setText(data.get(i).getNews_title());



        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView title;
    }

}
