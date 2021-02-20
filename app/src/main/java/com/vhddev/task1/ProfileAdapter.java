package com.vhddev.task1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private Context mCtx;

    private List<Profile> profileList;

    public ProfileAdapter(Context mCtx, List<Profile> profileList) {
        this.mCtx = mCtx;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        profileList = MainActivity.profiles;
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_profile, null);

        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {

        Profile profile = profileList.get(position);

        holder.textViewEmpId.setText(profile.getId());
        holder.textViewEmpName.setText(profile.getName());
        holder.textViewEmpContact.setText(profile.getContact());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                Context context = mCtx;

                intent = new Intent(context, ProfileViewActivity.class);
                intent.putExtra("user_id", profileList.get(holder.getLayoutPosition()).getId());
                intent.putExtra("user_name", profileList.get(holder.getLayoutPosition()).getName());
                intent.putExtra("user_contact", profileList.get(holder.getLayoutPosition()).getContact());
                intent.putExtra("user_catid", profileList.get(holder.getLayoutPosition()).getCategoryid());
                intent.putExtra("user_desc", profileList.get(holder.getLayoutPosition()).getDescription());
                intent.putExtra("user_addr", profileList.get(holder.getLayoutPosition()).getAddress());
                intent.putExtra("user_code", profileList.get(holder.getLayoutPosition()).getEmpcode());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return profileList.size();

    }


    class ProfileViewHolder extends RecyclerView.ViewHolder{

        private final Context context;

        TextView textViewEmpId, textViewEmpName, textViewEmpContact;
        CardView cardView;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            textViewEmpId = itemView.findViewById(R.id.txt_empId);
            textViewEmpName = itemView.findViewById(R.id.txt_empName);
            textViewEmpContact = itemView.findViewById(R.id.txt_empContact);
            cardView = itemView.findViewById(R.id.profile_cardView);

        }

    }

}
