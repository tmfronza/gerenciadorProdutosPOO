package com.senai.projeto01.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.senai.projeto01.database.contract.ProdutoContract;

public class DataBaseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.produto";
    private static final int DATABASE_VERSION = 1;

    public DataBaseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProdutoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ProdutoContract.removerTabela());
        db.execSQL(ProdutoContract.criarTabela());
    }
}
