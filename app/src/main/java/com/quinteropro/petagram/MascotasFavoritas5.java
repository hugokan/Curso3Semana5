package com.quinteropro.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import com.quinteropro.petagram.adapter.MascotaAdaptador;
import com.quinteropro.petagram.pojo.Mascota;

public class MascotasFavoritas5 extends AppCompatActivity {

    ArrayList<Mascota> mascotasFavoritasList;
    ArrayList<Mascota> mascotasFavoritasTop5;


    private RecyclerView mRecyclerViewFavorita;
    private RecyclerView.Adapter mAdapterFavorita;
    private RecyclerView.LayoutManager mLayoutManagerFavorita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas5);

        androidx.appcompat.widget.Toolbar miActionBarmascotaFavorita = (androidx.appcompat.widget.Toolbar) findViewById(R.id.miActionBarmascotaFavorita);
        setSupportActionBar (miActionBarmascotaFavorita);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mascotasFavoritasList = getIntent().getParcelableArrayListExtra("MascotasFavoritas");

        mascotasLikeTop5();

        buildRecyclerViewFavoritas();

    }

    public void buildRecyclerViewFavoritas(){

        mRecyclerViewFavorita = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);
        mRecyclerViewFavorita.setHasFixedSize(true);

        mLayoutManagerFavorita = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManagerFavorita).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewFavorita.setLayoutManager(mLayoutManagerFavorita);

        mAdapterFavorita = new MascotaAdaptador(mascotasFavoritasTop5);
        mRecyclerViewFavorita.setAdapter(mAdapterFavorita);

    }

    public void mascotasLikeTop5(){

        //Toast.makeText(this,Integer.toString(mascotasFavoritasList.size()), Toast.LENGTH_LONG).show();
        Collections.sort(mascotasFavoritasList);
        mascotasFavoritasTop5 = new ArrayList<Mascota>();

        for (Mascota e: mascotasFavoritasList){
            if (mascotasFavoritasTop5.size() < 5){
                mascotasFavoritasTop5.add(e);
            }
        }
    }

}