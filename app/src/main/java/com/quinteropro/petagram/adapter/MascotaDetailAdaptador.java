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

public class MascotaDetailAdaptador extends RecyclerView.Adapter<MascotaDetailAdaptador.MascotaDetailViewHolder>{

    private ArrayList<Mascota> mMascotasListDetail;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class MascotaDetailViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImgFotoDetail;
        public TextView mTvLikeCountCVDetail;
        public ImageButton mBtnLikeCountDetail;

        public MascotaDetailViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImgFotoDetail = (ImageView) itemView.findViewById(R.id.imgFotoDetail);
            mTvLikeCountCVDetail = (TextView)  itemView.findViewById(R.id.tvLikeCountCVDetail);
            mBtnLikeCountDetail  = (ImageButton)  itemView.findViewById(R.id.btnLikecountDetail);

        }
    }

    public MascotaDetailAdaptador(ArrayList<Mascota> mascotas) {
        mMascotasListDetail = mascotas;
    }

    @NonNull
    @Override
    public MascotaDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_detail, parent, false);
        return new MascotaDetailViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaDetailViewHolder MascotaDetailViewHolder, int position) {
        Mascota currentMascota = mMascotasListDetail.get(position);
        MascotaDetailViewHolder.mImgFotoDetail.setImageResource(currentMascota.getFoto());
        MascotaDetailViewHolder.mTvLikeCountCVDetail.setText(""+currentMascota.getNumeroLikes());
        MascotaDetailViewHolder.mBtnLikeCountDetail.setImageResource(currentMascota.getBtnLikecount());
    }

    @Override
    public int getItemCount() {
        return mMascotasListDetail.size();
    }

}