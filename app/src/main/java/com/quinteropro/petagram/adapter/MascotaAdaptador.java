package com.quinteropro.petagram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.quinteropro.petagram.pojo.Mascota;
import com.quinteropro.petagram.R;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    private ArrayList<Mascota> mMascotasList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImgFoto;
        public ImageButton mBtnLikeVote;
        public TextView mTvNombreCV;
        public TextView mTvLikeCountCV;
        public ImageButton mBtnLikeCount;

        public MascotaViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            mBtnLikeVote   = (ImageButton)  itemView.findViewById(R.id.btnLikeVote);
            mTvNombreCV    = (TextView)  itemView.findViewById(R.id.tvNombreCV);
            mTvLikeCountCV = (TextView)  itemView.findViewById(R.id.tvLikeCountCV);
            mBtnLikeCount  = (ImageButton)  itemView.findViewById(R.id.btnLikecount);

            mBtnLikeVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public MascotaAdaptador(ArrayList<Mascota> mascotas) {
        mMascotasList = mascotas;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {
        Mascota currentMascota = mMascotasList.get(position);
        mascotaViewHolder.mImgFoto.setImageResource(currentMascota.getFoto());
        mascotaViewHolder.mBtnLikeVote.setImageResource(currentMascota.getBtnLikevote());
        mascotaViewHolder.mTvNombreCV.setText(currentMascota.getNombre());
        mascotaViewHolder.mTvLikeCountCV.setText(""+currentMascota.getNumeroLikes());
        mascotaViewHolder.mBtnLikeCount.setImageResource(currentMascota.getBtnLikecount());
    }

    @Override
    public int getItemCount() {
        return mMascotasList.size();
    }

}
