package ddwucom.mobile.finalreport;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Diary> diaryArrayList;
    private DiaryAdapter diaryAdapter;
    private ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diaryArrayList = new ArrayList<Diary>();
        diaryAdapter = new DiaryAdapter(this, R.layout.custom_adapter, diaryArrayList);

        diaryArrayList.add(new Diary("다이어리 제목 테스트", "좋음", "흐림", "재밌다", R.mipmap.angry));

        listView = (ListView) findViewById(R.id.diary_list);

        listView.setAdapter(diaryAdapter);
    }
}