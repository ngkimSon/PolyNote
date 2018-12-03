package com.demon.polynote.model;
public class Note {
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_NOTE + " TEXT,"
                    + COLUMN_IMAGE + " BLOB,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";
    private int id;
    private String title;
    private String note;
    private byte[] image;
    private String timestamp;
    public Note() {
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public Note(int id, String title, String note, byte[] image, String timestamp) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.image = image;
        this.timestamp = timestamp;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getNote() {
        return note;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
