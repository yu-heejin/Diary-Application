package ddwucom.mobile.finalreport;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

//과제명 : 다이어리 앱
//분반 : 01분반
//학번 : 20200989 성명: 유희진
//제출일

public class MainActivity extends AppCompatActivity {
    private ArrayList<Diary> diaryArrayList;
    private DiaryDBManager diaryDBManager;
    private DiaryAdapter diaryAdapter;
    private ListView listView;

    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Diary diary = diaryArrayList.get(i);

                Intent intent = new Intent(MainActivity.this, UpdateDiary.class);
                intent.putExtra("diary", diary);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE) {
            switch(resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "오늘 하루를 기록했습니다!", Toast.LENGTH_SHORT).show();
                    break;

                case RESULT_CANCELED:
                    Toast.makeText(this, "취소가 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }

        } else if(requestCode == UPDATE_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "수정이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                    break;

                case RESULT_CANCELED:
                    Toast.makeText(this, "수정 취소가 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        diaryArrayList.clear();
        diaryArrayList.addAll(diaryDBManager.getAllDiary());
        diaryAdapter.notifyDataSetChanged();
    }

}