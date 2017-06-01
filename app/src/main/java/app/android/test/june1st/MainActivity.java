package app.android.test.june1st;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // 1. 재정의 함수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    } // end of onCreate()

    // 2. 사용자 정의 함수
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.toTextFileActivity:
                changPage(TestFileActivity.class);
                break;
            case R.id.toTestBookMemo:
                changPage(TestBookMemo.class);
        }

    } // end of onClick()

    public void changPage(Class<?> cls) {

        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

} // end of MainActivity
