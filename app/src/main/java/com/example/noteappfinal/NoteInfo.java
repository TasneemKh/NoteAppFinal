package com.example.noteappfinal;

import java.util.Date;

public class NoteInfo {
    private String noteTit;
    private String noteDate;
    private String noteTxt;
    private String color;
    private String id;

    public NoteInfo(){}
    public NoteInfo(String id ,String noteTit,String noteTxt,String color,String noteDate){
        this.id=id;
        this.noteTit=noteTit;
        this.noteTxt=noteTxt;
        this.color=color;
        this.noteDate=noteDate;
    }
    public String getNoteTit() {
        return noteTit;
    }

    public void setNoteTit(String noteTit) {
        this.noteTit = noteTit;
    }

    public String getNoteTxt() {
        return noteTxt;
    }

    public void setNoteTxt(String noteTxt) {
        this.noteTxt = noteTxt;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }



}
