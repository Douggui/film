package com.example.filmliste;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class Swipe extends ItemTouchHelper.SimpleCallback {
private int position;
    private Context context ;
    private List<Items> items;
    private MonAdapter adapter;
    private MaBaseSQLite bdd;
    private RecyclerView recyclerView;


    public Swipe(int dragDirs, int swipeDirs, Context context, List<Items> items, MonAdapter adapter, MaBaseSQLite bdd,RecyclerView recyclerView) {
        super(dragDirs, swipeDirs);
        this.context = context;
        this.items = items;
        this.adapter = adapter;
        this.bdd=bdd;
        this.recyclerView=recyclerView;
    }




    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

         position=viewHolder.getAdapterPosition();

          int id=items.get(position).getId();
          String titre=items.get(position).getTitre();
          Toast.makeText(context, "id :"+id, Toast.LENGTH_SHORT).show();

        Snackbar.make( recyclerView,titre+" supprimé",Snackbar.LENGTH_SHORT).setBackgroundTint(Color.WHITE).setTextColor(Color.MAGENTA).setAction("Annuler", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            items.add(position,items.get(position));
            adapter.notifyItemInserted(position);

            }
        }).show();

        items.remove(position);
        adapter.notifyItemRemoved(position);

         bdd.suppressionItem(id);



        Toast.makeText(context, titre+" supprimé de la bdd", Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        new RecyclerViewSwipeDecorator.Builder(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive).addSwipeLeftBackgroundColor(Color.MAGENTA).addSwipeRightBackgroundColor(Color.GREEN).addSwipeLeftActionIcon(R.drawable.supp).addSwipeRightActionIcon(R.drawable.archiv1).create().decorate();


    }

}
