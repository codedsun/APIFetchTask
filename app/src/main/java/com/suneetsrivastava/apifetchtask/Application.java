package com.suneetsrivastava.apifetchtask;

/**
 * Created by suneetsrivastava on 13/01/18.
 */

public class Application extends android.app.Application {
    public static final String BASE_POST_URL = "https://jsonplaceholder.typicode.com/posts";
    public static final String BASE_COMMENTS_URL = "https://jsonplaceholder.typicode.com/comments";

    public static String getBasePostUrl() {
        return BASE_POST_URL;
    }

    public static String getBaseCommentsUrl() {
        return BASE_COMMENTS_URL;
    }
}
