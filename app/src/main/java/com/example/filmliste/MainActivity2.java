package com.example.filmliste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView monRecyclerView;
    private MaBaseSQLite bddFilm;
    private Cursor monCursor;
    private List<Items> mesItems;
    private List<Items> mesItems1;
    private MonAdapter monAdapter;
    private MonAdapter monAdapter1;
    private SearchView search;
    Cursor cursor1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        monRecyclerView=findViewById(R.id.monRecyclerView);
        search=findViewById(R.id.search);


        Log.d("img", "onCreate: "+R.drawable.imgfilm1);

        bddFilm=new MaBaseSQLite(this);

        bddFilm.insertionFilms("a travers ma fênetre","Raquel est obsédée depuis longtemps par son voisin",90,R.drawable.imgfilm1);
        bddFilm.insertionFilms("la fracture","Dans une farandole tragi-comique, Catherine Corsini ",90,R.drawable.imgfilm2);
        bddFilm.insertionFilms("red notice","Le film mélange astucieusement tous les ingrédients ",90,R.drawable.imgfilm3);
        bddFilm.insertionFilms("the lost daughter"," l’adaptation d’un roman d’Elena Ferrante ",90,R.drawable.imgfilm1);
        bddFilm.insertionFilms("la fracture","Dans une farandole tragi-comique, Catherine Corsini ",90,R.drawable.imgfilm2);
        bddFilm.insertionFilms("red notice","Le film mélange astucieusement tous les ingrédients ",90,R.drawable.imgfilm3);
        bddFilm.insertionFilms("the lost daughter"," l’adaptation d’un roman d’Elena Ferrante,  ",90,R.drawable.imgfilm1);
        bddFilm.insertionFilms("la fracture","Dans une farandole tragi-comique, Catherine Corsini ",90,R.drawable.imgfilm2);
        bddFilm.insertionFilms("red notice","Le film mélange astucieusement tous les ingrédients ",90,R.drawable.imgfilm3);
        bddFilm.insertionFilms("the lost daughter"," l’adaptation d’un roman d’Elena Ferrante,  ",90,R.drawable.imgfilm1);
        bddFilm.insertionFilms("la fracture","Dans une farandole tragi-comique, Catherine",90,R.drawable.imgfilm2);
        bddFilm.insertionFilms("red notice","Le film mélange astucieusement tous les ingrédients des",90,R.drawable.imgfilm3);
        bddFilm.insertionFilms("the lost daughter"," l’adaptation d’un roman d’Elena Ferrante, ",90,R.drawable.imgfilm1);

        mesItems=new ArrayList<>();

        monCursor=bddFilm.lireTable();

      if(monCursor.getCount()==0){

            Toast.makeText(getApplicationContext(), "il n'y a pas de données à afficher ", Toast.LENGTH_SHORT).show();

        }else{

            monCursor.moveToFirst();
            while (!monCursor.isAfterLast()){


                mesItems.add(new Items( monCursor.getInt(0),monCursor.getString(1),monCursor.getString(2),monCursor.getInt(3),monCursor.getInt(4)));
                Log.d("item", "listitem "+mesItems);
                monCursor.moveToNext();


            }
            monCursor.close();

            monAdapter=new MonAdapter(mesItems);
          Log.d("mainActivity2", "onCreate: "+monAdapter);
          int orientation=getResources().getConfiguration().orientation;//objet orientation detecte l'orientation
          Log.d("mainActivity2", "onCreate: "+orientation);
          if(Configuration.ORIENTATION_LANDSCAPE==orientation){
             monRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));//si paysage choix de la grille
              Log.d("mainActivity2", "gridLayout ");
          }else {
              monRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));//sinon choix de la  liste
              Log.d("mainActivity2", "linearLayout ");
          }

          monRecyclerView.setAdapter(monAdapter);
          Swipe  swipe=new Swipe(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,this,mesItems,monAdapter,bddFilm,monRecyclerView);
         /* bddFilm.suppressionItem(monAdapter.mesItems.get);*/


          new ItemTouchHelper(swipe).attachToRecyclerView(monRecyclerView);



        }

      search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

          @Override
          public boolean onQueryTextSubmit(String s) {
              return false;
          }

          @Override
          public boolean onQueryTextChange(String s) {

              String valeur=search.getQuery().toString();
              Log.d("mainActivity", "onQueryTextChange: "+s);
              mesItems1=new ArrayList<>();


               cursor1=bddFilm.rechercheParTitre(s);

              if(cursor1.getCount()==0){
                  Toast.makeText(getApplicationContext(), "il n'y a pas de données à afficher ", Toast.LENGTH_SHORT).show();
              }else{

                  cursor1.moveToFirst();

                  while (!cursor1.isAfterLast()) {

                     mesItems1.add(new Items(cursor1.getInt(0),cursor1.getString(1),cursor1.getString(2),cursor1.getInt(3),cursor1.getInt(4)));
                      Log.d("mainActivity", "mesItems.add :"+mesItems);
                      cursor1.moveToNext();


                  }
                      cursor1.close();

                  monAdapter=new MonAdapter(mesItems1);
                      int orientation=getResources().getConfiguration().orientation;//objet orientation detecte l'orientation
                      Log.d("search", "onCreate: "+orientation);
                      if(Configuration.ORIENTATION_LANDSCAPE==orientation){
                          monRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));//si paysage choix de la grille
                          Log.d("search", "gridLayout ");
                      }else {
                          monRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));//sinon choix de la  liste
                          Log.d("search", "linearLayout ");
                      }

                      monRecyclerView.setAdapter(monAdapter);

                  }

              return false;
          }
      });






    }
}