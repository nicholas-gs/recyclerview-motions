package com.example.user.recyclerviewanimationstransitions;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataRetriever {

    /**
     * Retrieves data through Json and passes the arrayList to the adapter before calling notifyDataSetChanged().
     * NOTE: Since Volley is async, adapter should be passed an empty arrayList first
     * NOTE: @Nullable RecyclerView is used to trigger layout animations
     *
     * @param adapter
     * @param emailModels
     * @param requestQueue
     * @param recyclerView
     */
    public static void parseJsonToAdapter(String url, final RecyclerView.Adapter adapter,
                                          final ArrayList<EmailModel> emailModels, RequestQueue requestQueue,
                                          @Nullable final RecyclerView recyclerView) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                int emailId = jsonObject.getInt("emailId");
                                boolean isImportant = jsonObject.getBoolean("isImportant");
                                boolean isRead = jsonObject.getBoolean("isRead");
                                String imageUrl = jsonObject.getString("imageUrl");
                                String from = jsonObject.getString("from");
                                String subject = jsonObject.getString("subject");
                                String message = jsonObject.getString("message");
                                String timeStamp = jsonObject.getString("timestamp");
                                EmailModel newEmailModel = new EmailModel(emailId, isRead, isImportant, imageUrl, from, subject,
                                        message, timeStamp);
                                emailModels.add(newEmailModel);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                        // Carry out recyclerview layout animations
                        if (recyclerView != null) {
                            recyclerView.scheduleLayoutAnimation();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
}
