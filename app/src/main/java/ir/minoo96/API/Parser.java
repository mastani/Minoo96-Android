package ir.minoo96.API;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ir.minoo96.Items.Candidate;
import ir.minoo96.Items.Comment;
import ir.minoo96.Items.Feed;
import ir.minoo96.Items.Post;
import ir.minoo96.Utility.SharedPreferenceHelper;
import ir.minoo96.Utility.Variables;

public class Parser {

    public static void parseCandidates(JSONArray json) {
        Variables.candidates.clear();
        try {
            for (int i = 0; i < json.length(); i++) {
                JSONObject subObj = (JSONObject) json.get(i);

                Candidate item = new Candidate();
                item.setId(subObj.getInt("id"));
                item.setName(subObj.getString("name"));
                item.setProfileName(subObj.getString("profile_name"));
                item.setFatherName(subObj.getString("father_name"));
                item.setAge(subObj.getInt("age"));
                item.setGender(subObj.getInt("gender"));
                item.setHezb(subObj.getString("hezb"));
                item.setTahsilat(subObj.getString("tahsilat"));
                item.setReshteh(subObj.getString("reshteh"));
                item.setBio(subObj.getString("bio"));
                item.setIdea(subObj.getString("idea"));
                item.setSavabegh(subObj.getString("savabegh"));
                item.setReCandidate(subObj.getInt("re_candidate"));
                item.setOthers(subObj.getString("others"));
                item.setImage(subObj.getString("image"));
                item.setTChannel(subObj.getString("tchannel"));
                item.setRegisterDate(subObj.getString("register_date"));

                Variables.candidates.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parseFeeds(JSONArray json) {
        Variables.feeds.clear();
        try {
            for (int i = 0; i < json.length(); i++) {
                JSONObject subObj = (JSONObject) json.get(i);

                Feed item = new Feed();
                item.setId(subObj.getInt("id"));
                item.setName(subObj.getString("title"));
                item.setImge(subObj.getString("image"));
                item.setStatus(subObj.getString("content").trim());
                item.setTimeStamp(subObj.getString("time"));

                Variables.feeds.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parsePosts(JSONArray json) {
        Variables.posts.clear();
        try {
            for (int i = 0; i < json.length(); i++) {
                JSONObject subObj = (JSONObject) json.get(i);

                Post item = new Post();
                item.setId(subObj.getInt("id"));
                item.setCandidateId(subObj.getInt("candidate_id"));
                item.setImage(subObj.getString("image"));
                item.setContent(subObj.getString("content").trim());
                item.setTime(subObj.getString("time"));
                item.setLikes(subObj.getInt("likes"));
                item.setComments(subObj.getInt("comments"));

                Variables.posts.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Comment> parseComments(JSONArray json) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            for (int i = 0; i < json.length(); i++) {
                JSONObject subObj = (JSONObject) json.get(i);

                Comment item = new Comment();
                item.setId(subObj.getInt("id"));
                item.setPostId(subObj.getInt("post_id"));
                item.setUserId(subObj.getInt("user_id"));
                item.setContent(subObj.getString("content").trim());
                item.setDate(subObj.getString("time"));
                item.setUserName(subObj.getString("name"));

                comments.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void parseUser(JSONObject json) {
        try {
            boolean success = json.getBoolean("success");

            if (success) {
                Variables.userId = json.getInt("id");
                Variables.userName = json.getString("name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    public static boolean parserLogin(Context context, JSONObject json) {
//        try {
//            boolean success = json.getBoolean("success");
//
//            if (success) {
//                String sessId = json.getString("sessid");
//                JSONArray user = json.getJSONArray("user");
//                JSONObject userArray = user.getJSONObject(0);
//                int userId = userArray.getInt("id");
//                String userName = userArray.getString("name");
//                String userLastName = userArray.getString("last_name");
//                String userMobile = userArray.getString("mobile");
//                String userEmail = userArray.getString("email");
//                int userCandidateId = userArray.getInt("candidate_id");
//                String userRegisterDate = userArray.getString("register_date");
//                String userLastLogin = userArray.getString("last_login");
//                int userRegisterPending = userArray.getInt("register_pending");
//
//                SharedPreferenceHelper pref = new SharedPreferenceHelper(context, "user");
//                pref.setString("sessId", sessId);
//                pref.setInt("userId", userId);
//                pref.setString("userName", userName);
//                pref.setString("userLastName", userLastName);
//                pref.setString("userMobile", userMobile);
//                pref.setString("userEmail", userEmail);
//                pref.setInt("userCandidateId", userCandidateId);
//                pref.setString("userRegisterDate", userRegisterDate);
//                pref.setString("userLastLogin", userLastLogin);
//                pref.setInt("userRegisterPending", userRegisterPending);
//            }
//
//            return success;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
}
