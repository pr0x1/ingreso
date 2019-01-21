package com.a4app.develop.ingreso.modelo;

import android.os.Parcel;
import android.os.Parcelable;

//@Entity(tableName="transportador",primaryKeys = {"codigo","placa"})
public class Transportador implements Parcelable {
    //  @NonNull
    private String codigo;
    private String nombre;
    //  @NonNull
    private String placa;

    public Transportador() {
        // TODO Auto-generated constructor stub

    }
    // @Ignore
    public static final Parcelable.Creator<Transportador> CREATOR
            = new Creator<Transportador>() {
        public Transportador createFromParcel(Parcel in) {
            return new Transportador(in);
        }

        public Transportador[] newArray(int size) {
            return new Transportador[size];
        }
    };
    private Transportador(Parcel in){
        codigo = in.readString();
        nombre = in.readString();
        placa = in.readString();
    }
    public Transportador(String codigo, String nombre, String placa) {
        // TODO Auto-generated constructor stub
        this.codigo = codigo;
        this.nombre = nombre;
        this.placa = placa;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.codigo);
        parcel.writeString(this.nombre);
        parcel.writeString(this.placa);

    }
}
