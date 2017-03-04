package agata91bcomgithub.sdacourseapplication.book;

import android.support.annotation.DrawableRes;

/**
 * Created by RENT on 2017-03-02.
 */
public class Book {


    private int id;
    private boolean isRead;
    @DrawableRes
    private int imageResoursceId;
    private String title;


    public Book(int id, @DrawableRes int imageResoursceId, String title) {
        this.imageResoursceId = imageResoursceId;
        this.title = title;
        this.id = id;
    }
    @DrawableRes
    public int getImageResoursceId() {
        return imageResoursceId;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
