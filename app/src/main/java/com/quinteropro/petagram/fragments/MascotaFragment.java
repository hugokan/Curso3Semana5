package com.quinteropro.petagram.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.quinteropro.petagram.R;
import com.quinteropro.petagram.adapter.MascotaAdaptador;
import com.quinteropro.petagram.adapter.MascotaDetailAdaptador;
import com.quinteropro.petagram.pojo.Mascota;


public class MascotaFragment extends Fragment {

    private static final String ARG_MASCOTADETAILLIST = "argMascotaDetailList";

    ArrayList<Mascota> mascotasList;

    private RecyclerView mRecyclerView;
    private MascotaDetailAdaptador mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    View mView;

    public static MascotaFragment newInstanceDetail (ArrayList<Mascota> mascotasList){
        MascotaFragment fragment = new MascotaFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String mJson = gson.toJson(mascotasList);
        args.putString(ARG_MASCOTADETAILLIST,mJson);
        fragment.setArguments(args);
        return fragment;
    }

    private MascotaFragment.MascotaFragmentListener listener;


    public interface MascotaFragmentListener {
        void onMascotaDetailListChange(ArrayList<Mascota> sendMascotasList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.fragment_mascota, container, false);

        if (this.getArguments() != null){
            Gson gson = new Gson();
            String mJson = getArguments().getString(ARG_MASCOTADETAILLIST);
            Type mType = new TypeToken<ArrayList<Mascota>>() {}.getType();
            mascotasList = gson.fromJson(mJson,mType);
        }

        buildRecyclerView();

        return mView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MascotaFragmentListener){
            listener = (MascotaFragmentListener) context;
        } else {
            throw  new RuntimeException(context.toString() + "must implement MascotaFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void buildRecyclerView(){

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rvMascotaDetail);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getActivity(),3);
        ((GridLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MascotaDetailAdaptador(mascotasList);
        mRecyclerView.setAdapter(mAdapter);

    }

}