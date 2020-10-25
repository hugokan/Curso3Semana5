package com.quinteropro.petagram.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.quinteropro.petagram.pojo.Mascota;


public class RecyclerViewFragment extends Fragment {

    private static final String ARG_MASCOTALIST = "argMascotaList";

    ArrayList<Mascota> mascotasList;

    private RecyclerView mRecyclerView;
    private MascotaAdaptador mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    View mView;

    public static RecyclerViewFragment newInstance (ArrayList<Mascota> mascotasList){
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String mJson = gson.toJson(mascotasList);
        args.putString(ARG_MASCOTALIST,mJson);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerViewFragmentListener listener;

    public interface RecyclerViewFragmentListener {
        void onmascotasListChange(ArrayList<Mascota> sendMascotasList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_recycler_view, container, false);

        if (this.getArguments() != null){
            Gson gson = new Gson();
            String mJson = getArguments().getString(ARG_MASCOTALIST);
            Type mType = new TypeToken<ArrayList<Mascota>>() {}.getType();
            mascotasList = gson.fromJson(mJson,mType);
        }

        buildRecyclerView();

        // Inflate the layout for this fragment
        return mView;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof RecyclerViewFragmentListener){
            listener = (RecyclerViewFragmentListener) context;
        } else {
            throw  new RuntimeException(context.toString() + "must implement RecyclerViewFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public void updateLikecount(int position){
        mascotasList.get(position).updatebtncount();
        mAdapter.notifyItemChanged(position);

        ArrayList<Mascota> sendMascotasList = mascotasList;
        listener.onmascotasListChange(sendMascotasList);
    }

    public void buildRecyclerView(){

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rvMascotas);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MascotaAdaptador(mascotasList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MascotaAdaptador.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                updateLikecount(position);
            }
        });

    }
}