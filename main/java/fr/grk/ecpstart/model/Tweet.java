package fr.grk.ecpstart.model;

/**
 * Created by grk on 05/12/14.
 */
public class Tweet {
    private String _id;
    private String content;
    private String date;


    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
