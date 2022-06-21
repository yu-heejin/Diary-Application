package ddwucom.mobile.finalreport;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

//과제명 : 다이어리 앱
//분반 : 01분반
//학번 : 20200989 성명: 유희진
//제출일

public class MainActivity extends AppCompatActivity {
    private ArrayList<Diary> diaryArrayList;
    private DiaryDBHelper diaryDBHelper;
    private DiaryDBManager diaryDBManager;
    private DiaryAdapter diaryAdapter;
    private ListView listView;

    final int REQ_CODE = 100;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.diary_list);
        diaryDBManager = new DiaryDBManager(this);
        diaryArrayList = diaryDBManager.getAllDiary();


        diaryAdapter = new DiaryAdapter(this, R.layout.custom_adapter, diaryArrayList);

        //diaryArrayList.add(new Diary("다이어리 제목 테스트", "좋음", "흐림", "재밌다", R.mipmap.angry));

        listView.setAdapter(diaryAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "일기 쓰기", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AddDiary.class);
                startActivityForResult(intent, REQ_CODE);
                break;

            case R.id.intro:
                Toast.makeText(this, "유희진", Toast.LENGTH_SHORT).show();
                break;

            case R.id.exit:
                Toast.makeText(this, "나가기", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}