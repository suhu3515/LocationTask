package com.vhddev.task1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private Context mCtx;

    private List<Location> locationList;

    public LocationAdapter(Context mCtx, List<Location> locationList) {
        this.mCtx = mCtx;
        this.locationList = locationList;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        locationList = MainActivity.locations;
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_location,null);

        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        Location location = locationList.get(position);

        holder.textViewLocLong.setText(location.getLongg());
        holder.textViewLocId.setText(location.getId());
        holder.textViewLocLat.setText(location.getLat());

        holder.locationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = mCtx;

                Intent mapIntent = new Intent(context, MapViewActivity.class);
                mapIntent.putExtra("map_lat", locationList.get(holder.getLayoutPosition()).getLat());
                mapIntent.putExtra("map_longg", locationList.get(holder.getLayoutPosition()).getLongg());
                context.startActivity(mapIntent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return locationList.size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder{

        TextView textViewLocId, textViewLocLat, textViewLocLong;
        CardView locationCardView;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewLocId = itemView.findViewById(R.id.txt_locId);
            textViewLocLat = itemView.findViewById(R.id.txt_locLatt);
            textViewLocLong = itemView.findViewById(R.id.txt_locLong);
            locationCardView = itemView.findViewById(R.id.location_cardView);

        }
    }

}
