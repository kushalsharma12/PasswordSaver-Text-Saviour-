package com.problemsolvers.textsaviour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SaviourAdapter extends RecyclerView.Adapter<SaviourAdapter.SaviourViewHolder> {

    private List<Saviour> saviours = new ArrayList<>();
    private OnItemClickListener listener;





    @NonNull
    @Override
    public SaviourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saviour_item, parent, false);
        return new SaviourViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SaviourViewHolder holder, int position) {
        Saviour currentSaviour = saviours.get(position);
        holder.platform.setText(currentSaviour.getPlatformName());
        holder.email.setText(currentSaviour.getPassword());
        holder.password.setText(currentSaviour.getPassword());

    }

    @Override
    public int getItemCount() {
        return saviours.size();
    }

    public void setSaviours(List<Saviour> saviours) {
        this.saviours = saviours;
        notifyDataSetChanged();
    }
    public Saviour getSuviourAt(int position){
        return saviours.get(position);
    }

    public class SaviourViewHolder extends RecyclerView.ViewHolder {
        private TextView platform;
        private TextView email;
        private TextView password;


        public SaviourViewHolder(@NonNull View itemView) {
            super(itemView);

            platform = itemView.findViewById(R.id.platform);
            email = itemView.findViewById(R.id.Email);
            password = itemView.findViewById(R.id.Password);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(saviours.get(position));
                    }
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Saviour saviour);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
