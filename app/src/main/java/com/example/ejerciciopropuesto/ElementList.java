package com.example.ejerciciopropuesto;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ElementList {
   public  ArrayList<Element> elements;

    public ElementList() {
        this.elements = new ArrayList<>();
    }

    public void addElement(Element element) {
        this.elements.add(element);
    }

    public void editElement(int key, int img) {
        this.elements.get(key).img = img;
    }

    public void removeElement(int key) {
        this.elements.remove(key);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public ElementList fromJson(String json) {
        Gson gson = new Gson();
        return  gson.fromJson(json, ElementList.class);
    }
}
