package com.example.letter;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Reply")
public class Reply extends ParseObject {

    public static final String KEY_LETTER = "letter";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_AUTHOR = "user";
    public static final String KEY_CREATED_AT = "createdAt";

    public ParseObject getLetter() {
        return getParseObject(KEY_LETTER);
    }

    public void setLetter(ParseObject letter) {
        put(KEY_LETTER, letter);
    }

    public String getContent() { return getString(KEY_CONTENT); }

    public void setContent(String content) { put(KEY_CONTENT, content); }

    public ParseUser getUser() {
        return getParseUser(KEY_AUTHOR);
    }

    public void setUser(ParseUser user) {
        put(KEY_AUTHOR, user);
    }
}
