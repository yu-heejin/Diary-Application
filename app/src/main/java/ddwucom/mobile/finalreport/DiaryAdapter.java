package ddwucom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;


public class DiaryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Diary> diaryArrayList;
    private LayoutInflater layoutInflater;
    ViewHolder viewHolder;

    @Override
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
            viewHolder.
        }
    }


    static class ViewHolder {

    }
}
