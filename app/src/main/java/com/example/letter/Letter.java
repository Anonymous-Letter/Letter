package com.example.letter;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Letter")
public class Letter extends ParseObject {

    public static final String KEY_CATEGORY = "category";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_CREATED_AT = "createdAt";

    public String getCategory() {
        return getString(KEY_CATEGORY);
    }

    public void setCategory(String category) {
        put(KEY_CATEGORY, category);
    }

    public String getTitle() {
        return getString(KEY_TITLE);
    }

    public void setTitle(String title) {
        put(KEY_TITLE, title);
    }

    public String getContent() { return getString(KEY_CONTENT); }

    public void setContent(String content) { put(KEY_CONTENT, content); }

    public ParseUser getUser() {
        return getParseUser(KEY_AUTHOR);
    }

    public  void setUser(ParseUser user) {
        put(KEY_AUTHOR, user);
    }
}
