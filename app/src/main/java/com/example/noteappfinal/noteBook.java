package com.example.noteappfinal;

public class noteBook {
    private String image;
    private String ImageTxt;



    public noteBook(){}
    public noteBook(String image ,String ImageTxt){
        this.image=image;
        this.ImageTxt=ImageTxt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageTxt() {
        return ImageTxt;
    }

    public void setImageTxt(String imageTxt) {
        ImageTxt = imageTxt;
    }

}
