package gihwan.jsonparse;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.*;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

    TextView mTextSource;
    TextView mTextMsg;
    String mStrJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextSource = (TextView) findViewById(R.id.TextSource);
        mTextMsg = (TextView) findViewById(R.id.TextMessage);


        // JSON 원문 코드를 String 변수에 저장합니다.
        mStrJson = "[{name : Obama, math : 50, phone: {mobile:111-1111, home:222-2222}},\n"
                + "{name : Psy, math : 70, phone: {mobile:333-3333, home:444-4444}},\n"
                + "{name : Yuna, math : 65, phone: {mobile:555-5555, home:666-6666}}]";
        // JSON 원문 코드를 TextView 에 표시합니다.
        mTextSource.setText(mStrJson);

    }


    // 단순 배열 JSON 파싱
    public void onBtnParse1() {
        String strJson = "[11, 22, 33, 44, 55]";
        String strData = "Score:";
        try {
            // JSON 구문을 파싱해서 JSONArray 객체를 생성
            JSONArray jAr = new JSONArray(strJson);
            for (int i = 0; i < jAr.length(); i++) {
                // 배열에서 정수값을 구한다
                strData += " - " + jAr.getInt(i);
            }
        } catch (JSONException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        mTextMsg.setText(strData);
    }

    // 객체 배열 JSON 파싱
    public void onBtnParse2() {
        String strData = "";
        try {
            // JSON 구문을 파싱해서 JSONArray 객체를 생성
            JSONArray jAr = new JSONArray(mStrJson);
            for (int i = 0; i < jAr.length(); i++) {
                // 개별 객체를 하나씩 추출
                JSONObject student = jAr.getJSONObject(i);
                // 객체에서 데이터를 추출
                strData += student.getString("name") + " - " + student.getInt("math") + "\n";
            }
        } catch (JSONException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        mTextMsg.setText(strData);
    }

    // 하위 객체 배열 JSON 파싱
    public void onBtnParse3() {
        String strData = "";
        try {
            // JSON 구문을 파싱해서 JSONArray 객체를 생성
            JSONArray jAr = new JSONArray(mStrJson);
            for (int i = 0; i < jAr.length(); i++) {
                // 개별 객체를 하나씩 추출
                JSONObject student = jAr.getJSONObject(i);
                // 객체에서 하위 객체를 추출
                JSONObject phone = student.getJSONObject("phone");
                // 하위 객체에서 데이터를 추출
                strData += student.getString("name") + " - " + phone.getString("mobile") + " - " + phone.getString("home") + "\n";
            }
        } catch (JSONException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        mTextMsg.setText(strData);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                // 단순 배열 JSON 파싱
                onBtnParse1();
                break;
            case R.id.btn2:
                // 객체 배열 JSON 파싱
                onBtnParse2();
                break;
            case R.id.btn3:
                // 하위 객체 배열 JSON 파싱
                onBtnParse3();
                break;
        }
    }
}

