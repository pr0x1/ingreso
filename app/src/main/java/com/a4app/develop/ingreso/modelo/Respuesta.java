package com.a4app.develop.ingreso.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Respuesta implements Parcelable {
    private String tipo;
    private String mensaje;

    public static final Creator<Respuesta> CREATOR
            = new Creator<Respuesta>() {
        public Respuesta createFromParcel(Parcel in) {
            return new Respuesta(in);
        }

        public Respuesta[] newArray(int size) {
            return new Respuesta[size];
        }
    };
    public Respuesta(String tipo, String mensaje){
        this.tipo = tipo;
        this.mensaje = mensaje;
    }
    private Respuesta(Parcel in){
         tipo = in.readString();
         mensaje = in.readString();
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        String a = this.tipo + ":" +this.mensaje;
        return a;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tipo);
        parcel.writeString(this.mensaje);
    }
}
