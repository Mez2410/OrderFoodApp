package com.example.vuphi.order.OrderDrink;

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

public class FragmentDrink extends Fragment {

    RequestQueue drinkQueue;
    List<ItemDrink> lstDrink;
    RecyclerView rvDrink;
    RecyclerView.LayoutManager layoutManagerDrink;
    ListDrinkAdapter adapterDrink;
    UrlData urlData = new UrlData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drink, container, false);

        // thêm item vào Options Menu trong fragment Drink
        setHasOptionsMenu(true);

        rvDrink = (RecyclerView) view.findViewById(R.id.rv_drink);
        rvDrink.setHasFixedSize(true);

        layoutManagerDrink = new LinearLayoutManager(getContext());
        rvDrink.setLayoutManager(layoutManagerDrink);

        lstDrink = new ArrayList<>();

        getDataDrink();

        return view;
    }

    private void getDataDrink(){
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Loading Data", "Please wait...", false, false);

        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlData.URL_DRINK,
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
        drinkQueue = Volley.newRequestQueue(getContext());

        //Adding request to the queue
        drinkQueue.add(jsonArrayRequest);
    }

    //This method will parse json data
    private void parseData(JSONArray array){
        for(int i = 0; i < array.length(); i++) {
            ItemDrink itemDrink = new ItemDrink();
            JSONObject json;
            try {
                json = array.getJSONObject(i);
                itemDrink.setNameDrinks(json.getString("NameDrinks"));
                itemDrink.setCostDrinks(json.getInt("CostDrinks"));
                itemDrink.setImageDrinks(json.getString("LinkDrinks"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            lstDrink.add(itemDrink);
        }

        //Finally initializing our adapter
        adapterDrink = new ListDrinkAdapter(lstDrink, getContext(), this);

//        Adding adapter to recycler view
        rvDrink.setAdapter(adapterDrink);
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
                adapterDrink.getFilter().filter(newText);
                return true;
            }
        });

    }

}


