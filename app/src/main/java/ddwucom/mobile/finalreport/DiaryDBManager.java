package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DiaryDBManager {
    DiaryDBHelper diaryDBHelper = null;
    Cursor cursor = null;

    public DiaryDBManager(Context context) {
        diaryDBHelper = new DiaryDBHelper(context);
    }

    //Create

    //Read
    public ArrayList<Diary> getAllDiary() {
        ArrayList<Diary> diaryArrayList = new ArrayList<>();
        SQLiteDatabase db = diaryDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + diaryDBHelper.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            long id = cursor.getInt(0);
            String title = cursor.getString(1);
            String feeling = cursor.getString(2);
            String weather = cursor.getString(3);
            String date = cursor.getString(4);
            String detail = cursor.getString(5);
            int picture = cursor.getInt(6);
        }

        cursor.close();

    }
    //Update

    //Delete
}
