package app.android.test.june1st;

/*
    수업 강의용
    파일 관련 일만 처리하는 파일 관리자 클래스 만들기
 */


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MyFileManagerForClass {

    // write() 함수를 갖고 있는 FileOutputStream 클래스를 사용하기 위한 변수
    private FileOutputStream m_fileOutputStream;

    // read() 함수를 갖고 있는 FileInputStream 클래스를 사용하기 위한 변수
    private FileInputStream m_fileInputStream;

    // Context 클래스를 사용한 참조 변수 선언
    private Context mContext;

    // 파일 이름 보관 변수 선언
    private String mFileName = "";

    // 다른 클래스에서 사용하기 위한 겟 셋 함수
    public FileOutputStream getM_fileOutputStream() {
        return m_fileOutputStream;
    }

    public void setM_fileOutputStream(FileOutputStream m_fileOutputStream) {
        this.m_fileOutputStream = m_fileOutputStream;
    }

    public FileInputStream getM_fileInputStream() {
        return m_fileInputStream;
    }

    public void setM_fileInputStream(FileInputStream m_fileInputStream) {
        this.m_fileInputStream = m_fileInputStream;
    }


    public boolean useOpenFileOutput(String fileContext) {
        boolean result = true;

        if(fileContext == null || fileContext.length() == 0) {
            return false;
        }

        try{
            m_fileOutputStream = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);

            byte [] bytes = fileContext.getBytes();
            m_fileOutputStream.write(bytes);
            m_fileOutputStream.close();

        } catch(Exception e ) {
            e.printStackTrace();
            return false;
        }

        return result;
    }

    // openFileInput() 함수를 사용하는 함수
    public String useOpenFileInput() {

        // 파일에서 가져온 데이터를 보관하는 변수
        String result = "";

        try{
            m_fileInputStream = mContext.openFileInput(mFileName);

            // read() 함수의 반환 값을 보관할 변수
            byte [] bytes = null;

            // available() : 데이터의 byte수가 없으면 0을 반환! 0일 경우 데이터 없음
            int fileLength = m_fileInputStream.available();

            if(fileLength == 0) {
                return "";
            }

            bytes = new byte[fileLength];

            // read() 함수 실행 -> 파일에 있는 모든 데이터가 bytes 배열로 복사됨
            // 그리고 바이트 수를 알려줌

            int count = m_fileInputStream.read(bytes);

            result = new String(bytes);
            m_fileInputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        // result 변수가 갖게되는 값을 반환하는 명령문
        return result;


    }

    // 위에서 만든 파일 이름 보관 변s수에 파일 이름을 넘겨주는 함수 만들기
    public void saveFileName(String value) {
        this.mFileName = value;
    }

    // 파일 이름을 읽어오는 함수
    public String readFileName() {
        return this.mFileName;
    }


    // 위에서 만든 컨텍스트 참조 변수에 메모리에 있는 객체 주소를 저장하는 생성자 함수
    public MyFileManagerForClass(Context contextValue) {

        this.mContext = contextValue;
    }

    // 일반 함수로도 구현 가능
    public void saveContext(Context contextValue) {
        this.mContext = contextValue;
    }

    public MyFileManagerForClass() {
        // defalut 생성자
    }


}
