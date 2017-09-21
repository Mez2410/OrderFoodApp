package com.example.vuphi.order.OrderMainDish;

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

public class FragmentMainDish extends Fragment {

    RequestQueue queue;
    List<ItemMainDish> mFoodList;
    RecyclerView rvFood;
    RecyclerView.LayoutManager layoutManager;
    private ListMainDishAdapter adapter;
    UrlData urlData = new UrlData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        setHasOptionsMenu(true);

        rvFood = (RecyclerView) view.findViewById(R.id.rv_food);
        rvFood.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        rvFood.setLayoutManager(layoutManager);

        mFoodList = new ArrayList<>();

        getData();

        // Lấy dữ liệu (bill_code) từ màn hình TableActivity truyền qua
//        Intent getBillCode = getActivity().getIntent();
//        Bundle bundle = getBillCode.getBundleExtra("BillOrder");
//        int billCode = bundle.getInt("BillCode");
//        Toast.makeText(getActivity(), String.valueOf(billCode), Toast.LENGTH_SHORT).show();

        return view;
    }

    private void getData() {
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Loading Data", "Please wait...", false, false);

        //Creating a json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlData.URL_FOOD,
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
        queue = Volley.newRequestQueue(getContext());

        //Adding request to the queue
        queue.add(jsonArrayRequest);
    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            ItemMainDish itemMainDish = new ItemMainDish();
            JSONObject json;
            try {
                json = array.getJSONObject(i);
                itemMainDish.setFoodName(json.getString("NameFood"));
                itemMainDish.setCostFood(json.getInt("CostFood"));
                itemMainDish.setImageFood(json.getString("LinkFood"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            mFoodList.add(itemMainDish);
        }

        //Finally initializing our adapter
        adapter = new ListMainDishAdapter(mFoodList, getContext(), FragmentMainDish.this);

//        Adding adapter to recycler view
        rvFood.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, final MenuInflater inflater) {

        inflater.inflate(R.menu.button_actionbar, menu);

        final MenuItem itemSearch = menu.findItem(R.id.search_view_fragment);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);

        int searchEditId = android.support.v7.appcompat.R.id.search_src_text;
        EditText et = (EditText) searchView.findViewById(searchEditId);
        et.setTextColor(Color.WHITE);
        et.setHintTextColor(Color.WHITE);

        search(searchView);

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

    }

}
