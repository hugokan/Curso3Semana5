package com.quinteropro.petagram.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

public class Mascota implements Parcelable, Comparable<Mascota> {
    private int foto;
    private int btnLikevote;
    private String nombre;
    private int numeroLikes;
    private int btnLikecount;

    public Mascota(int foto, int btnLikevote, String nombre, int numeroLikes, int btnLikecount) {
        this.foto = foto;
        this.btnLikevote = btnLikevote;
        this.nombre = nombre;
        this.numeroLikes = numeroLikes;
        this.btnLikecount = btnLikecount;
    }

    public void updatebtncount() {
        setNumeroLikes(getNumeroLikes() + 1);
    }

    public Mascota(Parcel in) {
        foto = in.readInt();
        btnLikevote = in.readInt();
        nombre = in.readString();
        numeroLikes = in.readInt();
        btnLikecount = in.readInt();
    }

    public static final Creator<Mascota> CREATOR = new Creator<Mascota>() {
        @Override
        public Mascota createFromParcel(Parcel in) {
            return new Mascota(in);
        }

        @Override
        public Mascota[] newArray(int size) {
            return new Mascota[size];
        }
    };

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getBtnLikevote() {
        return btnLikevote;
    }

    public void setBtnLikevote(int btnLikevote) {
        this.btnLikevote = btnLikevote;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(int numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public int getBtnLikecount() {
        return btnLikecount;
    }

    public void setBtnLikecount(int btnLikecount) {
        this.btnLikecount = btnLikecount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(foto);
        dest.writeInt(btnLikevote);
        dest.writeString(nombre);
        dest.writeInt(numeroLikes);
        dest.writeInt(btnLikecount);

    }

    @Override
    public int compareTo(@NotNull Mascota o) {
        return o.numeroLikes- this.numeroLikes;
    }
}

