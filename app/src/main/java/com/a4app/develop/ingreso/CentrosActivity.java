package com.a4app.develop.ingreso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a4app.develop.ingreso.modelo.CentrosAlmacen;

import java.util.StringTokenizer;

public class CentrosActivity extends AppCompatActivity {
    private CentrosAlmacen centrosAlmacen;
    private Context contexto;
    private EditText textCentros;
    private int contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        contador = 0;
        setContentView(R.layout.activity_centros);
        textCentros = (EditText) findViewById(R.id.etCentros);
        textCentros.setInputType(InputType.TYPE_NULL);
        contexto = this.getBaseContext();

        textCentros.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed

                String textoLeido  = s.toString();
                if(!textoLeido.equals("") && !textoLeido.isEmpty() && textoLeido != null) {
                centrosAlmacen =  procesaLecturaCentros(textoLeido);
                goCentrosActivity(centrosAlmacen);
                   CharSequence text = "Centro receptor: "+ centrosAlmacen.getDesCentroOrigen() + " leido" ;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(contexto, text, duration);
                    toast.show();
                    textCentros.setText("");
                }


            }
        });
    }
    public void goCentrosActivity(CentrosAlmacen centrosAlmacen){
        Intent i = new Intent(contexto, LecturaActivity.class);

        if (validaCampos(centrosAlmacen)) {
            i.putExtra("centrosAlm", centrosAlmacen);
            startActivity(i);
        } else{
            TextView centroMensaje = findViewById(R.id.tvCentroMensajes);
            centroMensaje.setText("Error al Leer etiqueta");
        }


    }
    public CentrosAlmacen procesaLecturaCentros(String lectura){
        if (lectura != null) {
            StringTokenizer token = new StringTokenizer(lectura, "#");
            centrosAlmacen = new CentrosAlmacen();
            String fragmento = "";
            contador = 0;
            //while (token.hasMoreTokens()) {
                // Lee centro origen;
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    centrosAlmacen.setCentroOrigen(fragmento);
                    contador++;
               }
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    centrosAlmacen.setDesCentroOrigen(fragmento);
                    contador++;
                }/*
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    centrosAlmacen.setAlmacenOrigen(fragmento);
                    contador++;
                }
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    centrosAlmacen.setDesAlmacenOrigen(fragmento);
                    contador++;
                }
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    centrosAlmacen.setCentroDestino(fragmento);
                    contador++;
                }
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    centrosAlmacen.setDesCentroDestino(fragmento);
                    contador++;
                }
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    centrosAlmacen.setAlmacenDestino(fragmento);
                    contador++;
                }
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    centrosAlmacen.setDesAlmacenDestino(fragmento);
                    contador++;
                }
*/

            //}
        }
        return centrosAlmacen;

    }
    private boolean validaCampos(CentrosAlmacen centrosAlmacen){
        if(centrosAlmacen!=null) {
            if(contador<1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }



}
