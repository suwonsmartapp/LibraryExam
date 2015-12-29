JSON/XML 관련 라이브러리 예제
# 1. okhttp
## URL

http://square.github.io/okhttp/

## Gradle
```java
compile 'com.squareup.okhttp:okhttp:2.7.0'
```

## Example
```java
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
```
# 2. java-json
## Download URL

http://www.java2s.com/Code/JarDownload/java-json/java-json.jar.zip

## Example
```java
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
```
```java
원본 xml String : <current><city id="1835848" name="Seoul"><coord lon="126.98" lat="37.57"></coord><country>KR</country><sun rise="2015-12-28T22:46:09" set="2015-12-29T08:21:44"></sun></city><temperature value="273.9" min="273.15" max="274.15" unit="kelvin"></temperature><humidity value="34" unit="%"></humidity><pressure value="1032" unit="hPa"></pressure><wind><speed value="1.5" name="Calm"></speed><gusts></gusts><direction value="190" code="S" name="South"></direction></wind><clouds value="1" name="clear sky"></clouds><visibility value="10000"></visibility><precipitation mode="no"></precipitation><weather number="800" value="sky is clear" icon="01d"></weather><lastupdate value="2015-12-29T05:20:00"></lastupdate></current>

변환된 json String : {"current":{"city":{"id":1835848,"name":"Seoul","coord":{"lon":126.98,"lat":37.57},"country":"KR","sun":{"rise":"2015-12-28T22:46:09","set":"2015-12-29T08:21:44"}},"temperature":{"value":273.9,"min":273.15,"max":274.15,"unit":"kelvin"},"humidity":{"value":34,"unit":"%"},"pressure":{"value":1032,"unit":"hPa"},"wind":{"speed":{"value":1.5,"name":"Calm"},"gusts":"","direction":{"value":190,"code":"S","name":"South"}},"clouds":{"value":1,"name":"clear sky"},"visibility":{"value":10000},"precipitation":{"mode":"no"},"weather":{"number":800,"value":"sky is clear","icon":"01d"},"lastupdate":{"value":"2015-12-29T05:20:00"}}}
```
# 3. jackson-databind
## URL

https://github.com/FasterXML/jackson-databind/

## Gradle
```java

packagingOptions {
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/NOTICE'
    }
    
dependencies {
    compile 'com.fasterxml.jackson.core:jackson-databind:2.6.3'
}
```
## Example
### Model Class
```java
package com.massivcode.libraryexam;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties({"base", "clouds", "dt", "id", "cod"}) // 입력된 JSON 에서 무시할 프로퍼티 설정
public class WeatherInfo {
    public String visibility;
    public String name;
    public Coord coord;
    public Wind wind;
    public Sys sys;
    public ArrayList<Weather> weather;
    public Main main;

    @JsonCreator // 생성자에 추가
    public WeatherInfo(@JsonProperty("visibility") String visibility,
                       @JsonProperty("name") String name,
                       @JsonProperty("coord") Coord coord,
                       @JsonProperty("wind") Wind wind,
                       @JsonProperty("sys") Sys sys,
                       @JsonProperty("weather") ArrayList<Weather> weather,
                       @JsonProperty("main") Main main) {
        this.visibility = visibility;
        this.name = name;
        this.coord = coord;
        this.wind = wind;
        this.sys = sys;
        this.weather = weather;
        this.main = main;
    }

    static class Coord {
        public String lon;
        public String lat;

        @JsonCreator
        public Coord(@JsonProperty("lon") String lon,
                     @JsonProperty("lat") String lat) {
            this.lon = lon;
            this.lat = lat;
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "lon='" + lon + '\'' +
                    ", lat='" + lat + '\'' +
                    '}';
        }
    }

    static class Wind {
        public String speed;
        public String deg;

        @JsonCreator
        public Wind(@JsonProperty("speed") String speed,
                    @JsonProperty("deg") String deg) {
            this.speed = speed;
            this.deg = deg;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "speed='" + speed + '\'' +
                    ", deg='" + deg + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties({"type", "id", "message"})
    static class Sys {
        public String country;
        public String sunrise;
        public String sunset;

        @JsonCreator
        public Sys(@JsonProperty("country") String country,
                   @JsonProperty("sunrise") String sunrise,
                   @JsonProperty("sunset") String sunset) {
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "Sys{" +
                    "country='" + country + '\'' +
                    ", sunrise='" + sunrise + '\'' +
                    ", sunset='" + sunset + '\'' +
                    '}';
        }
    }

    static class Weather {
        public String id;
        public String main;
        public String description;
        public String icon;

        @JsonCreator
        public Weather(@JsonProperty("id") String id,
                       @JsonProperty("main") String main,
                       @JsonProperty("description") String description,
                       @JsonProperty("icon") String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "id='" + id + '\'' +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    static class Main {
        public String temp;
        public String pressure;
        public String humidity;
        public String temp_min;
        public String temp_max;

        @JsonCreator
        public Main(@JsonProperty("temp") String temp,
                    @JsonProperty("pressure") String pressure,
                    @JsonProperty("humidity") String humidity,
                    @JsonProperty("temp_min") String temp_min,
                    @JsonProperty("temp_max") String temp_max) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp='" + temp + '\'' +
                    ", pressure='" + pressure + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", temp_min='" + temp_min + '\'' +
                    ", temp_max='" + temp_max + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "visibility='" + visibility + '\'' +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                ", wind=" + wind +
                ", sys=" + sys +
                ", weather=" + weather +
                ", main=" + main +
                '}';
    }
}

```
### Method
```java
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
```
