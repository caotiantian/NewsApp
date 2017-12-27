package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.newsapp.R;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

import java.util.List;

import beans.Phone;

/**
 * Created by admin on 2017/12/14.
 */

public class PhoneAdapter extends BaseAdapter {
    private Context context;
    private List<Phone.DataBean.PcFeedFocusBean> pc;

    public PhoneAdapter(Context context, List<Phone.DataBean.PcFeedFocusBean> pc) {
        this.context = context;
        this.pc = pc;
    }

    @Override
    public int getCount() {
        return pc.size();
    }

    @Override
    public Object getItem(int i) {
        return pc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.tu1_layout,null);
            holder.img = (ImageView) view.findViewById(R.id.img);
            holder.tv = (TextView) view.findViewById(R.id.tv);
            view.setTag(holder);
        }else {
            holder =(ViewHolder) view.getTag();
        }

        holder.tv.setText(pc.get(i).getTitle());
        Glide.with(context).load("http:"+pc.get(i).getImage_url()).into(holder.img);

        return view;
    }

    class ViewHolder{
        ImageView img;
        TextView tv;

    }
}
