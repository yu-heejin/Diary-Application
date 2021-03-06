package ddwucom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;


public class DiaryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Diary> diaryArrayList;
    private LayoutInflater layoutInflater;
    ViewHolder viewHolder;

    public DiaryAdapter(Context context, int layout, ArrayList<Diary> diaryArrayList) {
        this.context = context;
        this.layout = layout;
        this.diaryArrayList = diaryArrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return diaryArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return diaryArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return diaryArrayList.get(i).get_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;

        if(view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.id = (TextView) view.findViewById(R.id.id);
            viewHolder.diaryTitle = (TextView) view.findViewById(R.id.diary_title);
            viewHolder.date = (TextView) view.findViewById(R.id.date);
            viewHolder.weather = (TextView) view.findViewById(R.id.weather);
            viewHolder.feeling = (TextView) view.findViewById(R.id.feeling);
            viewHolder.feelingIcon = (ImageView) view.findViewById(R.id.feeling_icon);
            //viewHolder.detail = (TextView) view.findViewById(R.id.detail);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.id.setText(String.valueOf(diaryArrayList.get(position).get_id()));
        viewHolder.diaryTitle.setText(diaryArrayList.get(position).getTitle());
        viewHolder.weather.setText(diaryArrayList.get(position).getWeather());
        viewHolder.feeling.setText(diaryArrayList.get(position).getFeeling());
        viewHolder.feelingIcon.setImageResource(diaryArrayList.get(position).getPicture());
        viewHolder.date.setText(diaryArrayList.get(position).getDate());
        //viewHolder.detail.setText(diaryArrayList.get(position).getDetail());

        return view;
    }


    static class ViewHolder {
        TextView id;
        TextView diaryTitle;
        TextView date;
        TextView weather;
        TextView feeling;
        ImageView feelingIcon;
        //TextView detail;
    }
}
