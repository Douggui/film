package com.example.filmliste;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MonAdapter extends RecyclerView.Adapter<MonAdapter.MonViewHolder> {



    List<Items> mesItems;



    public MonAdapter(List<Items> mesItems) {

        this.mesItems = mesItems;

        Log.d("adapter", "MonAdapter: " + mesItems);
    }

    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*return null;*/
        Log.d("adapter", "onCreateViewHolder: ");

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.affichage_donnees, parent,false);
        return new MonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {
        Log.d("adapter", "onBindViewHolder: ");
        holder.titreV.append(mesItems.get(position).getTitre());
        holder.descriptionV.append(mesItems.get(position).getDescription());
        holder.dureeV.append(String.valueOf(mesItems.get(position).getDuree())+" mn");
        holder.imageV.setImageResource(mesItems.get(position).getImage());



    }

    @Override
    public int getItemCount() {
        Log.d("adapter", "getItemCount: " + mesItems.size());
        return mesItems.size();

    }

    public class MonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private ImageView imageV;
        private TextView titreV;
        private TextView descriptionV;
        private TextView dureeV;

        public MonViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("adapter", "MonViewHolder2: ");
            imageV = itemView.findViewById(R.id.img);
            Log.d("MonViewHolder", "image: " + imageV);
            titreV = itemView.findViewById(R.id.titre);
            Log.d("MonViewHolder", "titre: " + titreV);
            descriptionV = itemView.findViewById(R.id.description);
            dureeV = itemView.findViewById(R.id.duree);
            Log.d("MonViewHolder", "duree: "+dureeV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getLayoutPosition();
            int imgClick = mesItems.get(position).getImage();
            String titreClick = mesItems.get(position).getTitre();
            String descriptionClick = mesItems.get(position).getDescription();
            int dureeClick = mesItems.get(position).getDuree();
            int id=mesItems.get(position).getId();

            Log.d("click", "titre: " + titreClick);
            Log.d("click", "description: " + descriptionClick);
            Log.d("click", "img: " + imgClick);
            Log.d("click", "img: " + dureeClick);
            Log.d("click", "id: " + id);

            /*Toast.makeText(context, "" + titreClick + " " + imgClick + " " + String.valueOf(position), Toast.LENGTH_SHORT).show();*/



            Intent intent = new Intent(itemView.getContext(), MainActivity3.class);
            intent.putExtra("titre", titreClick);
            intent.putExtra("description", descriptionClick);
            intent.putExtra("duree", dureeClick);
            intent.putExtra("image", imgClick);
           itemView.getContext().startActivity(intent);

        }
    }
}
