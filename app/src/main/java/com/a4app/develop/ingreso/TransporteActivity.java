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
import com.a4app.develop.ingreso.modelo.Transportador;

import java.util.StringTokenizer;

public class TransporteActivity extends AppCompatActivity {
    private EditText textoTransportador;
    private TextView textoCentros;
    private Context contexto;
    private String codigoTransportador;
    private String nombreTransportador;
    private String placa;
    private CentrosAlmacen centroOrigen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transporte);
        contexto = getBaseContext();
        textoTransportador = (EditText) findViewById(R.id.etEmpreTransport);
        textoTransportador.setInputType(InputType.TYPE_NULL);
        textoCentros = (TextView) findViewById(R.id.tvTCentrosLeidos);
        Intent intent = getIntent();
        centroOrigen = intent.getParcelableExtra("centrosAlm");
        textoCentros.setText(centroOrigen.toString());
        textoTransportador.addTextChangedListener(new TextWatcher() {
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
                    procesaLecturaTransporte(textoLeido);
                    goLecturaActivity();
                    CharSequence text = "Cambiado!After";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(contexto, text, duration);
                    toast.show();
                    textoTransportador.setText("");
                }


            }
        });

    }




    public void goLecturaActivity(){
        Intent i = new Intent(contexto, LecturaActivity.class);
        if (validaCampos()) {
            Transportador transportador = new Transportador();
            transportador.setCodigo(getCodigoTransportador());
            transportador.setNombre(getNombreTransportador());
            transportador.setPlaca(getPlaca());
            i.putExtra("transportador", transportador);
            i.putExtra("centrosAlm",centroOrigen);
            startActivity(i);
        } else{
            textoCentros = findViewById(R.id.tvTCentrosLeidos);
            textoCentros.setText("Error al Leer etiqueta");
        }
    }
    public void procesaLecturaTransporte(String lectura){
        if (lectura != null) {
            StringTokenizer token = new StringTokenizer(lectura, "#");
            String fragmento = "";
            while (token.hasMoreTokens()) {
                // Lee nombre código Transportador
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    setCodigoTransportador(fragmento);
                }
                // Lee nombre Transportador
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    setNombreTransportador(fragmento);
                }
                if (token.hasMoreTokens()) {
                    fragmento = token.nextToken();
                    setPlaca(fragmento);
                }


            }
        }

    }

    private boolean validaCampos(){
        if((getCodigoTransportador().equals("")|| getNombreTransportador() == null)){
            return false;
        }

        return true;

    }


    public String getCodigoTransportador() {
        return codigoTransportador;
    }

    public void setCodigoTransportador(String codigoTransportador) {
        this.codigoTransportador = codigoTransportador;
    }

    public String getNombreTransportador() {
        return nombreTransportador;
    }

    public void setNombreTransportador(String nombreTransportador) {
        this.nombreTransportador = nombreTransportador;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
