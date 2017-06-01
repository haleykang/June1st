package app.android.test.june1st;

// 교재 따라하기

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TextFileManager {

    private static final String FILE_NAME = "Memo.txt";

    Context mContext = null;

    // 생성자 함수
    public TextFileManager(Context context) {

        mContext = context;
    }

    public void save(String strData) {
        if(strData == null || strData.isEmpty() == true) {
            return;
        }

        FileOutputStream fosMemo = null;

        try {

            fosMemo = mContext.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fosMemo.write(strData.getBytes());
            fosMemo.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

    } // end of save()

    public String load() {

        try {
            FileInputStream fisMemo = mContext.openFileInput(FILE_NAME);
            byte[] memoData = new byte[fisMemo.available()];

            while(fisMemo.read(memoData) != -1) {
            }

            fisMemo.close();

            return new String(memoData);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return "";

    } // end of load()

    public void delete() {

        mContext.deleteFile(FILE_NAME);
    }


} // end of TextFileManager 클래스
