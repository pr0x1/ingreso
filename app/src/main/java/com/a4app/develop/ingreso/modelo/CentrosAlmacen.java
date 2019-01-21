package com.a4app.develop.ingreso.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/*import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;*/

//@Entity(primaryKeys = {"centroOrigen","almacenOrigen","centroDestino","almacenDestino"},tableName = "centrosAlmacen")
public class CentrosAlmacen implements Parcelable {
    //@NonNull
    private String centroOrigen;
    private String desCentroOrigen;
    //@NonNull
    private String almacenOrigen;
    private String desAlmacenOrigen;
    //@NonNull
    private String centroDestino;
    private String desCentroDestino;
    //@NonNull
    private String almacenDestino;
    private String desAlmacenDestino;
   // @Ignore
    public static final Parcelable.Creator<CentrosAlmacen> CREATOR
            = new Creator<CentrosAlmacen>() {
        public CentrosAlmacen createFromParcel(Parcel in) {
            return new CentrosAlmacen(in);
        }

        public CentrosAlmacen[] newArray(int size) {
            return new CentrosAlmacen[size];
        }
    };
    private CentrosAlmacen(Parcel in) {
        centroOrigen = in.readString();
        desCentroOrigen = in.readString();
        almacenOrigen  = in.readString();
        desAlmacenOrigen = in.readString();
        centroDestino  = in.readString();
        desCentroDestino = in.readString();
        almacenDestino = in.readString();
        desAlmacenDestino = in.readString();

    }
    public CentrosAlmacen(String centroOringen, String desCentroOrigen, String almacenOrigen, String desAlmacenOrigen,
                          String centroDestino, String desCentroDestino, String almacenDestino, String desAlmacenDestino){
        this.centroOrigen = centroOringen;
        this.desCentroOrigen = desCentroOrigen;
        this.almacenOrigen = almacenOrigen;
        this.desAlmacenOrigen = desAlmacenOrigen;
        this.centroDestino = centroDestino;
        this.desCentroDestino = desCentroDestino;
        this.almacenDestino = almacenDestino;
        this.desAlmacenDestino = desAlmacenDestino;

    }
    public CentrosAlmacen(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.centroOrigen);
        parcel.writeString(this.desCentroOrigen);

        parcel.writeString(this.almacenOrigen);
        parcel.writeString(this.desAlmacenOrigen);

        parcel.writeString(this.centroDestino);
        parcel.writeString(this.desCentroDestino);

        parcel.writeString(this.almacenDestino);
        parcel.writeString(this.desAlmacenDestino);

    }

    public String getCentroOrigen() {
        return centroOrigen;
    }

    public void setCentroOrigen(String centroOringen) {
        this.centroOrigen = centroOrigen;
    }

    public String getAlmacenOrigen() {
        return almacenOrigen;
    }

    public void setAlmacenOrigen(String almacenOrigen) {
        this.almacenOrigen = almacenOrigen;
    }

    public String getCentroDestino() {
        return centroDestino;
    }

    public void setCentroDestino(String centroDestino) {
        this.centroDestino = centroDestino;
    }

    public String getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(String almacenDestino) {
        this.almacenDestino = almacenDestino;
    }

    public String getDesCentroOrigen() {
        return desCentroOrigen;
    }

    public void setDesCentroOrigen(String desCentroOrigen) {
        this.desCentroOrigen = desCentroOrigen;
    }

    public String getDesAlmacenOrigen() {
        return desAlmacenOrigen;
    }

    public void setDesAlmacenOrigen(String desAlmacenOrigen) {
        this.desAlmacenOrigen = desAlmacenOrigen;
    }

    public String getDesCentroDestino() {
        return desCentroDestino;
    }

    public void setDesCentroDestino(String desCentroDestino) {
        this.desCentroDestino = desCentroDestino;
    }

    public String getDesAlmacenDestino() {
        return desAlmacenDestino;
    }

    public void setDesAlmacenDestino(String desAlmacenDestino) {
        this.desAlmacenDestino = desAlmacenDestino;
    }

    @Override
    public String toString() {
        String texto = "De " + getDesCentroOrigen() + " : " +getDesAlmacenOrigen() + " a " + getDesCentroDestino() + " : " + getDesAlmacenDestino();
        return texto;
    }
}
