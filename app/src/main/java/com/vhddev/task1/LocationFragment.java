package com.vhddev.task1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment {

    List<Location> locationList;
    ViewGroup root;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.fragment_location,null);
        recyclerView = root.findViewById(R.id.location_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        locationList = new ArrayList<>();
        locationList = MainActivity.locations;
        LocationAdapter locationAdapter = new LocationAdapter(getContext(),locationList);
        recyclerView.setAdapter(locationAdapter);

        return root;
    }
}
