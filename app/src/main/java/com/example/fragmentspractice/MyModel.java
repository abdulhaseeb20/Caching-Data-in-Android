package com.example.fragmentspractice;

public class MyModel
{
    private String country_id,country_name,tag_name;

    public MyModel() {
    }

    public MyModel(String country_id, String country_name, String tag_name) {
        this.country_id = country_id;
        this.country_name = country_name;
        this.tag_name = tag_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    @Override
    public String toString() {
        return "MyModel{" +
                "country_id='" + country_id + '\'' +
                ", country_name='" + country_name + '\'' +
                ", tag_name='" + tag_name + '\'' +
                '}';
    }
}
