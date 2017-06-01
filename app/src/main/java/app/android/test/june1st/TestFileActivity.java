package app.android.test.june1st;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class TestFileActivity extends AppCompatActivity {

    // 1. 변수 선언
    // 방금전에 만든 MyFileManagerForClass 사용하기 위한 변수 선언
    // private MyFileManagerForClass m_myFileManagerForClass;

    // 1-1) 파일 이름 보관 변수
    private String mFileName = "haley01.txt"; // 기본 파일 이름

    // 1-2) 파일 생성 시 사용할 변수
    private FileOutputStream mFileOutputStream;

    // 1-3) 파일을 읽을 떄 사용할 변수
    private FileInputStream mFileInputStream;

    // 1-4) XML 주소 태그 보관 변수
    private Button mWriteFileBt, mReadFileBt;
    private TextView mTvFromFile;
    private EditText mEtToFile;

    // 2. 함수 재정의


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_file);

        myLog("onCreate() 시작");

        this.mWriteFileBt = (Button)this.findViewById(R.id.writeFileBt);
        this.mReadFileBt = (Button)this.findViewById(R.id.readFileBt);
        this.mTvFromFile = (TextView)this.findViewById(R.id.tvFromFile);
        this.mEtToFile = (EditText)this.findViewById(R.id.etToFile);

        // this.m_myFileManagerForClass = new MyFileManagerForClass(this);


        // 1. 파일 쓰기 버튼
        this.mWriteFileBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myLog("파일 쓰기 버튼 클릭");
                // Log.v("*TestFileActivity*", "파일 쓰기 버튼 클릭");

                // 내장 메모리(/data/data/프로젝트_패키지이름) 경로에 파일 만들기
                // 1. openFileOutput() : 파일 만들기
                try {

                    mFileOutputStream = openFileOutput(mFileName, Context.MODE_PRIVATE);

                    myLog(mFileName + "파일 생성");

                    // 2. EditText에 입력된 내용을 파일에 저장
                    String fileContexts = mEtToFile.getText().toString();

                    // 3. 사용자가 데이터를 입력했는지 여부 검사
                    if(fileContexts.equals("") == true) {
                        shortToast("내용을 입력하세요");

                    } else {
                        shortToast("저장하실 내용은 \"" + fileContexts + "\" 입니다.");

                        // 4. 파일에 새로운 데이터 저장
                        // 먼저 byte 배열을 준비 -> 사용자가 입력한 데이터를 읽어와서 byte 배열에 저장
                        // byte[] bytes = new byte[fileContexts.length()];
                        // fileContexts.length() : 사용자가 입력한 글자 갯수 반환 함수

                        // getBytes() : 사용자가 입력한 데이터를 읽어와서 byte [] 배열에 저장하고
                        // byte [] 배열의 주소를 반환
                        byte[] bytes = fileContexts.getBytes();

                        // 5. write() : 사용자가 입력한 데이터를 파일에 저장
                        mFileOutputStream.write(bytes);

                        myLog("파일에 내용 저장 완료");

                    }

                    // 다 사용한 파일 출력 스트림 객체를 메모리에서 삭제
                    if(mFileOutputStream != null) {
                        mFileOutputStream.close();
                        myLog("파일 출력 스트림 객체 삭제");
                    }


                } catch(IOException e) {

                    myLog("예외 상황 발생 : " + e.getMessage());


                } // end of try~catch

            } // end of onClick()
        }); // end of this.mWriteFileBt.setOnClickListener

        // 2. 파일 읽기 버튼 클릭
        this.mReadFileBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLog("파일 읽기 버튼 클릭");

                try {

                    // 1. 이전에 만든 파일에서 데이터 읽어오기
                    mFileInputStream = openFileInput(mFileName);

                    myLog("파일 읽기 시작");

                    // 파일에 저장된 데이터를 보관할 변수
                    byte[] readBytes = null;

                    // available() : 데이터 갯수 읽기(바이트 수)
                    int fileLength = mFileInputStream.available();

                    // 데이터 갯수 검사
                    if(fileLength == 0) {

                        shortToast("데이터가 없습니다.");

                    } else {

                        shortToast("저장 데이터 수 : " + fileLength);

                        // 파일에서 가져온 데이터가 저장된 byte 배열 초기화
                        readBytes = new byte[fileLength];

                        // read() : 파일에 저장된 모든 데이터들을 readBytes 배열에 저장
                        int readBytesCount = mFileInputStream.read(readBytes);
                        // readBytesCount : read() 함수가 파일에 저장된 데이터를 읽어오고 난 후의
                        // 총 글자 갯수(바이트 수)를 반환
                        shortToast("파일에서 읽어온 데이터 갯수 : " + readBytesCount);

                        // 파일에서 읽어온 모든 데이터들을 화면에 출력
                        // byte 배열에 저장된 데이터들을 문자열로 변환 먼저
                        String resultString = new String(readBytes);
                        mTvFromFile.setText(resultString);


                    } // end of else

                    if(mFileInputStream != null) {
                        mFileInputStream.close();
                        myLog("파일 입력 객체 삭제");
                    }

                } catch(IOException e) {

                    myLog("예외 상황 발생 : " + e.getMessage());
                } // end of try~catch


            } // end of onClick()
        }); // end of this.mReadFileBt.setOnClickListener


    } // end of onCreate()

    // 3. 사용자 함수 정의
    public void shortToast(String ob) {
        Toast.makeText(this, ob, Toast.LENGTH_SHORT).show();
    }

    public void myLog(String ob) {

        Log.v("*TestFileActivity*", ob);
    }


} // end of TestFileActivity
