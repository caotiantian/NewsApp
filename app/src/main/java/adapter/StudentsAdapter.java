package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.newsapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import beans.StudentBeans;

/**
 * Created by admin on 2017/12/19.
 */

public class StudentsAdapter extends BaseAdapter {
    private Context context;
    private    List<StudentBeans.DataBean> data;

    public StudentsAdapter(Context context, List<StudentBeans.DataBean> data) {
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
            view= View.inflate(context, R.layout.student_layout, null);
            holder=new ViewHolder();
            holder.img=(ImageView) view.findViewById(R.id.img);
            holder.tv1=(TextView) view.findViewById(R.id.tv1);
            holder.tv2=(TextView) view.findViewById(R.id.tv2);
            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }

        ImageLoader.getInstance().displayImage(data.get(i).getUserImg(),holder.img);
        holder.tv1.setText(data.get(i).getTitle());
        holder.tv2.setText(data.get(i).getIntroduction());


        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView tv1,tv2;
    }

}
