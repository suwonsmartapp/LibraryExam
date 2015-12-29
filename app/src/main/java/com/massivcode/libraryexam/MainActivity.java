package com.massivcode.libraryexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String URL_STRING_XML = "http://api.openweathermap.org/data/2.5/weather?q=seoul&mode=xml&appid=2de143494c0b295cca9337e1e96b00e0";
    private final String URL_STRING_JSON = "http://api.openweathermap.org/data/2.5/weather?q=seoul&mode=json&appid=2de143494c0b295cca9337e1e96b00e0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    convertXmlToJson(getStringFromUrl(URL_STRING_XML));
                    convert(getStringFromUrl(URL_STRING_JSON));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * okhttp 라이브러리
     * 해당 URL의 body 의 모든 값을 문자열로 긁어옴
     * @param urlString
     * @return
     * @throws IOException
     */
    private String getStringFromUrl(String urlString) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlString)
                .build();


        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * java-json 라이브러리
     * xml 형식의 문자열을 JSONObject 로 변환
     * @param xml
     * @return
     * @throws JSONException
     */
    private String convertXmlToJson(String xml) throws JSONException {
        JSONObject object = XML.toJSONObject(xml);
        System.out.println("원본 xml String : " + xml);
        System.out.println("변환된 json String : " + object.toString());
        return object.toString();
    }

    /**
     * jackson-databind 라이브러리
     * 입력된 json 문자열로부터 원하는 property 의 값들을 얻음
     * 이를 위해서 모델 클래스가 필요 (어노테이션 필요)
     * @param jsonString
     * @throws IOException
     */
    private void convert(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherInfo weatherInfo = objectMapper.readValue(jsonString, WeatherInfo.class);
        System.out.println(weatherInfo);
    }




}
