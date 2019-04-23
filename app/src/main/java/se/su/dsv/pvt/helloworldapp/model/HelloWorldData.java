package se.su.dsv.pvt.helloworldapp.model;

import com.google.gson.annotations.SerializedName;

public class HelloWorldData {
    @SerializedName("response")
    private String result;

    private String name;

    private String age;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
