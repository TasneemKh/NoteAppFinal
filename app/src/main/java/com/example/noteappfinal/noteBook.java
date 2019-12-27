package com.example.noteappfinal;

public class noteBook {
    private String noteBookPic;
    private String noteBookTxt;
    public noteBook(){}
    public noteBook(String noteBookPic ,String noteBookTxt){
        this.noteBookPic=noteBookPic;
        this.noteBookTxt=noteBookTxt;
    }

    public String getNoteBookPic() {
        return noteBookPic;
    }

    public void setNoteBookPic(String noteBookPic) {
        this.noteBookPic = noteBookPic;
    }

    public String getNoteBookTxt() {
        return noteBookTxt;
    }

    public void setNoteBookTxt(String noteBookTxt) {
        this.noteBookTxt = noteBookTxt;
    }


}
