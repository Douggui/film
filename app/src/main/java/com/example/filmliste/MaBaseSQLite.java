package com.example.filmliste;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MaBaseSQLite extends SQLiteOpenHelper {



    public static final String BASE_NOM= "filmBase.db";
    public static  final  int BASE_VERSION= 11 ;
    public static final  String NOM_TABLE="film";
    public static final String COL0="Id";
    public static final String COL1="titre";
    public static final String COL2="description";
    public static final String COL3="duree";
    public static final String COL4="affichage";


    public  MaBaseSQLite(Context context){
        super(context,BASE_NOM,null,BASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String strSql="CREATE TABLE "+NOM_TABLE+" ("

                +COL0+" integer primary key autoincrement,"

                +COL1+" text not null,"

                +COL2+" text not null,"

                +COL3+" integer not null,"

                +COL4+" integer not null)";

        Log.d("dataBase", "strSql: "+strSql);
        db.execSQL(strSql);
        Log.d("dataBase", "création de la base de donnée ok: "+NOM_TABLE);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DataBase", "oldVersion: "+oldVersion);
        Log.d("DataBase", "newVersion: "+newVersion);

        String strSql="DROP TABLE IF EXISTS "+NOM_TABLE+ ";";

        Log.d("DataBase", "requête sql ds upgrade: "+strSql);
        db.execSQL(strSql);
        onCreate(db);
        Log.d("DataBase", "méthide upgrade call: "+NOM_TABLE);

    }

    public void insertionFilms(String titre , String description , Integer duree ,Integer affichage){
        titre=titre.replace("'","");
        description.replace("'","");

        String strSql="INSERT INTO "+NOM_TABLE+"("+COL1+","+COL2+","+COL3+","+COL4+")"
                +"values ('"+titre+"','"+description+"',"+duree+","+affichage+");";

        Log.d("database", "StrSql insert"+strSql);

        getWritableDatabase().execSQL(strSql);

        Log.d("DataBase", "insertionClients, insertion OK");
    }
public void suppressionItem(int id){

        String strSql="DELETE FROM '"+NOM_TABLE+"' WHERE "+COL0+"="+id;

        Log.d("database", "StrSql supprssion"+strSql);

        getWritableDatabase().execSQL(strSql);

    }

    public Cursor lireTable(){

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor monCurseur=db.rawQuery("select * from "+NOM_TABLE,null);

        return monCurseur;
    }

    public Cursor rechercheParduree(int duree){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor moncurseur=db.rawQuery("select * from " +NOM_TABLE+ " where " +COL3+ " =  " +duree,null);
        Log.d("requete nom", "rechercheParNom: "+"select * from " +NOM_TABLE+ " where " +COL3+ " = " +duree);

        return moncurseur;
    }

    public Cursor rechercheParTitre(String titre){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor moncurseur=db.rawQuery("select * from "+NOM_TABLE+" where " +COL1+ " like '%" +titre+"%' ",null);

        Log.d("requete nom", "rechercheParNom: "+"select * from "+NOM_TABLE+" where " +COL1+ " like '%" +titre+"%' ");

        return moncurseur;
    }
}

