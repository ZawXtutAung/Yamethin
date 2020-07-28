package com.zawxtutaung.yamethin;

/**
 * Created by Dell on 12/3/2016.
 */

public class Contact {
    public String name;
    public String phone;
    public String phonetwo;
    public String adress;
    public  String title;
    public String imgview;
    public Integer intimage;
    public Contact(String name, String phone, String adress,String phonetwo) {

        this.name=name;
        this.phone=phone;
        this.adress=adress;
        this.phonetwo=phonetwo;
    }
    public Contact (String name,String title,String imgview){
        this.name=name;
      this.title=title;
        this.imgview=imgview;
    }
    public Contact (String name,String title){
        this.name=name;
        this.title=title;

    }
    public Contact (String name,String title,Integer intimage){
        this.name=name;
        this.title=title;
        this.intimage=intimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getIntimage() {
        return intimage;
    }

    public void setIntimage(Integer intimage) {
        this.intimage = intimage;
    }

    public void setPhone(String phone) {
        this.phone=phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhonetwo() {
        return phonetwo;
    }

    public void setPhonetwo(String phonetwo) {
        this.phonetwo = phonetwo;
    }

    public String getImgview() {
        return imgview;
    }

    public void setImgview(String imgview) {
        this.imgview = imgview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
