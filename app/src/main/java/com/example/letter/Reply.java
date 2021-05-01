package com.example.letter;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Reply")
public class Reply extends ParseObject {

    public static final String KEY_CONTENT = "content";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";


    public String getContent() { return getString(KEY_CONTENT); }

    public void setContent(String content) { put(KEY_CONTENT, content); }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

}
