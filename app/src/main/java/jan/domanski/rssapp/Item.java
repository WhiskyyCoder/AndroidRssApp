package jan.domanski.rssapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.URL;

/**
 * Created by Jan on 2018-03-12.
 */

public class Item {

    private String title;
    private String description;
    private String link;
    private String pubdate;
    private Bitmap img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Bitmap getImg() {
       return  this.img ;
    }
    public Item(){}
}
