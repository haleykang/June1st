package app.android.test.june1st;

// 교재 예제 따라하기

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestBookMemo extends AppCompatActivity {

    EditText mMemoEdit = null;
    TextFileManager mTextFileManager = new TextFileManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_book_memo);

        mMemoEdit = (EditText)findViewById(R.id.memo_edit);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            // 1. 파일에 저장된 메모 텍스트 파일 불러오기
            case R.id.load_bt: {
                String memoData = mTextFileManager.load();

                if(memoData.equals("") == true) {

                    shortToast("저장된 내용이 없습니다.");

                } else {

                    mMemoEdit.setText(memoData);
                    shortToast("불러오기 완료");

                }

                break;
            }
            // 2. 에디트 텍스트에 입력된 메모를 텍스트 파일로 저장하기
            case R.id.save_bt: {
                String memoData = mMemoEdit.getText().toString();
                mTextFileManager.save(memoData);
                mMemoEdit.setText("");
                shortToast("저장 완료");

                break;
            }
            // 3. 저장된 메모 파일 삭제
            case R.id.delete_bt:

            {
                mTextFileManager.delete();
                mMemoEdit.setText("");
                shortToast("삭제 완료");

                break;
            }
        }
    } // end of onClick()

    public void shortToast(String ob) {
        Toast.makeText(this, ob, Toast.LENGTH_SHORT).show();
    }
}
