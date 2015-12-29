package com.massivcode.libraryexam;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Copyright 2015 Pureum Choe
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * <p/>
 * Created by Ray Choe on 2015-12-29.
 */
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
