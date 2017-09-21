package com.example.vuphi.order.OrderDessert;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vuphi.networkvolley.UrlData;
import com.example.vuphi.orderfoodapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vuphi on 3/29/2017.
 */

public class FragmentDessert extends Fragment {
    RequestQueue dessertQueue;
    List<ItemDessert> lstDessert;
    RecyclerView rvDessert;
    RecyclerView.LayoutManager layoutManagerDessert;
    private ListDessertAdapter adapterDessert;
    UrlData urlData = new UrlData();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dessert, container, false);

        setHasOptionsMenu(true);

        rvDessert = (RecyclerView) view.findViewById(R.id.rv_dessert);
        rvDessert.setHasFixedSize(true);

        layoutManagerDessert = new LinearLayoutManager(getContext());
        rvDessert.setLayoutManager(layoutManagerDessert);

        lstDessert = new ArrayList<>();

        getDataDessert();
        return view;
    }

    private void getDataDessert(){
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Loading Data", "Please wait...", false, false);

        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlData.URL_DESSERT,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing progress dialog
                        loading.dismiss();

                        //calling method to parse json array
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });

        //Creating request queue
        dessertQueue = Volley.newRequestQueue(getContext());

        //Adding request to the queue
        dessertQueue.add(jsonArrayRequest);
    }

    //This method will parse json data
    private void parseData(JSONArray array){
        for(int i = 0; i < array.length(); i++) {
            ItemDessert itemDessert = new ItemDessert();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                itemDessert.setNameDessert(json.getString("NameDessert"));
                itemDessert.setCostDessert(json.getInt("CostDessert"));
                itemDessert.setImageDessert(json.getString("LinkDessert"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            lstDessert.add(itemDessert);
        }

        //Finally initializing our adapter
        adapterDessert = new ListDessertAdapter(lstDessert, getContext(), this);

//        Adding adapter to recycler view
        rvDessert.setAdapter(adapterDessert);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.button_actionbar, menu);

        final MenuItem itemSearch = menu.findItem(R.id.search_view_fragment);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);

        int searchEditId = android.support.v7.appcompat.R.id.search_src_text;
        EditText et = (EditText) searchView.findViewById(searchEditId);
        et.setTextColor(Color.WHITE);
        et.setHintTextColor(Color.WHITE);

        search(searchView);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterDessert.getFilter().filter(newText);
                return true;
            }
        });

    }


}
