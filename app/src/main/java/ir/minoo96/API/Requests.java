package ir.minoo96.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ir.minoo96.Items.Comment;
import ir.minoo96.Utility.SharedPreferenceHelper;
import ir.minoo96.Utility.Variables;
import ir.minoo96.Utility.Volley.AppController;

public class Requests {
    private final static String URL_BASE = "http://test.minoo96.ir/API/";

    private final static String URL_INIT = URL_BASE + "loadAll.php";
    private final static String URL_CANDIDATES = URL_BASE + "candidates.php";
    private final static String URL_FEED = URL_BASE + "news.php";
    private final static String URL_POSTS = URL_BASE + "posts.php";
    //private final static String URL_LOGIN = URL_BASE + "auth.php";
    private final static String URL_USER = URL_BASE + "user.php";
    private final static String URL_LIKE = URL_BASE + "like.php";
    private final static String URL_COMMENT = URL_BASE + "comment.php";
    private final static String URL_SEND_COMMENT = URL_BASE + "comment_send.php";
    private final static String URL_UPDATE_USERNAME = URL_BASE + "update_username.php";

    public static void fetchInit(final Context context, final RequestCallback callback) {
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_INIT, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    try {
                        SharedPreferenceHelper cahce = new SharedPreferenceHelper(context, "cache");

                        JSONArray candidates = response.getJSONArray("candidates");
                        Parser.parseCandidates(candidates);
                        cahce.setString("candidates", candidates.toString());

                        JSONArray news = response.getJSONArray("news");
                        Parser.parseFeeds(news);
                        cahce.setString("news", news.toString());

                        JSONArray posts = response.getJSONArray("posts");
                        Parser.parsePosts(posts);
                        cahce.setString("posts", posts.toString());
                    } catch (Exception ex) {

                    }
                }

                callback.onSuccess();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
        });

        jsonReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonReq);
    }

    public static void fetchCandidates(final Context context, final RequestCallback callback) {
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_CANDIDATES, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    try {
                        SharedPreferenceHelper cahce = new SharedPreferenceHelper(context, "cache");
                        JSONArray candidates = response.getJSONArray("candidates");
                        Parser.parseCandidates(candidates);
                        cahce.setString("candidates", candidates.toString());
                    } catch (Exception ex) {

                    }
                }

                callback.onSuccess();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
        });

        jsonReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonReq);
    }

    public static void fetchNews(final Context context, final RequestCallback callback) {
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    SharedPreferenceHelper cahce = new SharedPreferenceHelper(context, "cache");
                    JSONArray news = response.getJSONArray("news");
                    Parser.parseFeeds(news);
                    cahce.setString("news", news.toString());
                } catch (Exception ex) {

                }

                callback.onSuccess();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
        });

        jsonReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonReq);
    }

    public static void fetchPosts(final Context context, final RequestCallback callback) {
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_POSTS, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    SharedPreferenceHelper cahce = new SharedPreferenceHelper(context, "cache");
                    JSONArray posts = response.getJSONArray("posts");
                    Parser.parsePosts(posts);
                    cahce.setString("posts", posts.toString());
                } catch (Exception ex) {

                }

                callback.onSuccess();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
        });

        jsonReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonReq);
    }

    public static void fetchUser(final Context context, final String serial, final String model, final RequestCallback callback) {
        StringRequest req = new StringRequest(Request.Method.POST, URL_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    Parser.parseUser(json);

                    SharedPreferenceHelper cahce = new SharedPreferenceHelper(context, "cache");
                    cahce.setString("user", json.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                callback.onSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("serial", serial);
                params.put("model", model);
                return params;
            }
        };

        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(req);
    }

    public static void fetchLike(final int post, final int user, final RequestCallback callback) {
        StringRequest req = new StringRequest(Request.Method.POST, URL_LIKE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("post", "" + post);
                params.put("user", "" + user);
                return params;
            }
        };

        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(req);
    }

    public static void fetchComment(final Context context, final int post, final CommentsCallback callback) {
        StringRequest req = new StringRequest(Request.Method.POST, URL_COMMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Comment> comments = new ArrayList<>();
                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray commentsJson = json.getJSONArray("comments");
                    comments = Parser.parseComments(commentsJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                callback.onSuccess(comments);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("post", "" + post);
                return params;
            }
        };

        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(req);
    }

    public static void fetchSendComment(final Context context, final int post, final int user, final String content, final RequestCallback callback) {
        StringRequest req = new StringRequest(Request.Method.POST, URL_SEND_COMMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("post", "" + post);
                params.put("user", "" + user);
                params.put("content", "" + content);
                return params;
            }
        };

        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(req);
    }

    public static void fetchUpdateUserName(final Context context, final int user, final String name, final RequestCallback callback) {
        StringRequest req = new StringRequest(Request.Method.POST, URL_UPDATE_USERNAME, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", "" + user);
                params.put("name", "" + name);
                return params;
            }
        };

        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(req);
    }


//    public static void reqLogin(final Context context, final String username, final String password, final RequestCallback callback) {
//        StringRequest req = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject mainObject = new JSONObject(response);
//                    boolean res = Parser.parserLogin(context, mainObject);
//
//                    if (res)
//                        callback.onSuccess();
//                    else
//                        callback.onFailed();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                callback.onFailed();
//                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("mobile-email", username);
//                params.put("password", password);
//                return params;
//            }
//        };
//
//        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        AppController.getInstance().addToRequestQueue(req);
//    }

//    public static void reqRegister(final Context context, final String name, final String lastName, String email, String mobile, String password, final RequestCallback callback) {
//        StringRequest req = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject mainObject = new JSONObject(response);
//                    boolean res = Parser.parserLogin(context, mainObject);
//
//                    if (res)
//                        callback.onSuccess();
//                    else
//                        callback.onFailed();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                callback.onFailed();
//                Toast.makeText(context, "خطا در اتصال به اینترنت", Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
////                params.put("mobile-email", username);
////                params.put("password", password);
//                return params;
//            }
//        };
//
//        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        AppController.getInstance().addToRequestQueue(req);
//    }
}
