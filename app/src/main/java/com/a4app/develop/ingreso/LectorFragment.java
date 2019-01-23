package com.a4app.develop.ingreso;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.a4app.develop.ingreso.modelo.CentrosAlmacen;
import com.a4app.develop.ingreso.modelo.Lote;
import com.a4app.develop.ingreso.modelo.Transportador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class LectorFragment extends Fragment  {
    private OnFragmentInteractionListener mListener;
    private ILectorActivity mCallback;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CentrosAlmacen centrosAlmacen;
   // private Transportador  transportador;
    public LectorFragment() {

    }
    public static LectorFragment newInstance(CentrosAlmacen param1) {
        LectorFragment fragment = new LectorFragment();
        Bundle args = new Bundle();
        args.putParcelable("centroOrigen", param1);
       // args.putParcelable("transportador", param2);
        fragment.setArguments(args);
        return fragment;

    }
        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lector, container, false);
        final EditText textEtiqueta = (EditText) v.findViewById(R.id.fltextView);
        textEtiqueta.setInputType(InputType.TYPE_NULL);
        if ( getArguments().containsKey("centroOrigen")) {
            // A choice was made, so get the choice.
            centrosAlmacen = getArguments().getParcelable("centroOrigen");
            //transportador = getArguments().getParcelable("transportador");
            // Check the radio button choice.
        }

        textEtiqueta.addTextChangedListener(new TextWatcher() {
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
                  Lote lote  =  procesaLectura(textoLeido);
                  mCallback.onLoteCreated(lote);
                   Context context = getContext();
                   int duration = Toast.LENGTH_SHORT;
                   Toast toast = Toast.makeText(context, lote.getNumLote(), duration);
                   toast.show();
                   textEtiqueta.setText("");
               }


            }
        });

        return v;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ILectorActivity) {
            mCallback = (ILectorActivity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ILectorActivity");
        }
    }

  /*  @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/


    /**
     * Método que procesa la cadena leida de la etiqueta y crea el objeto Lote aqui se reliaza la logica para entender el
     * texto que viene de la etiqueta y se asignan alguas constantes.
     * @param lectura Cadena de texto proveniente de la etiqueta
     */
    public Lote procesaLectura(String lectura) {
        String Tag = "LectorFragment";
        Lote lote = new Lote();
        if (lectura != null) {
            String lecturaTransformada = transformaLectura(lectura);
            StringTokenizer token = new StringTokenizer(lecturaTransformada, "#");
            String texto = "";
            while (token.hasMoreTokens()) {
                // Lee centro origen;
                if(token.hasMoreTokens()){
                    texto = token.nextToken();
                }
               lote.setCentro(centrosAlmacen.getCentroOrigen());
                Log.i(Tag,"Centro origen"+lote.getCentro());


                // Lee Centro destino
                if(token.hasMoreTokens()){
                    texto = token.nextToken();
				}
                // Lee Número de pedido
                if(token.hasMoreTokens()){
                    texto = token.nextToken();

                }
                // Lee Posición del pedido
                if(token.hasMoreTokens()){
                    texto = token.nextToken();

                }

                // Lee Material
                if(token.hasMoreTokens()){
                    texto = token.nextToken();
                    if (texto.equals("*")) {
                        lote.setMaterial("");
                    } else {
                        lote.setMaterial(texto);
                    }
                }else{
                    lote.setMaterial("");
                }
                Log.i(Tag,"Material: " +texto);


                // Lee # de rollo
                if(token.hasMoreTokens()){
                    texto = token.nextToken();
                    if (texto.equals("*")) {
                        lote.setNumLote("");

                    } else {
                        lote.setNumLote(texto);

                    }
                }else{
                    lote.setNumLote(texto);

                }
                Log.i(Tag,"Rollo: " +texto);
                // Lee cantidad y validad en caso de excepción
                if(token.hasMoreTokens()){
                    texto = token.nextToken();
                    if (texto.equals("*")) {
                        lote.setCantidad(0);
                    } else {
                        try {
                            double cant = Double.parseDouble(texto);
                            lote.setCantidad(cant);
                        } catch (NumberFormatException ex) {
                            lote.setCantidad(0);
                        }
                    }
                }else{
                    lote.setCantidad(0);
                }
                Log.i(Tag,"Cantidad: " +texto);
                // Lee Unidad de medida
                if(token.hasMoreTokens()){
                    texto = token.nextToken();
                }
               // Log.i(Tag,"UM: " +texto);
                // Lee Cliente
                if(token.hasMoreTokens()){
                    texto = token.nextToken();

               }

            }
        }
        return lote;

    }

    /**
     * Metedo que se encargar de formatear en un texto estandar la cadena de texto que se lee desde la etiqueta, la extandariza
     * adicionando entre los caracteres"#" el caracter * para que el metodo {@link #procesaLectura(String)} pueda partir correctamente la cadena
     * @param a Cadena de texto en donde va  el texto leido en la etiqueta
     * @return Cadena de texto normalizad
     */
    private String transformaLectura(String a) {

        String lecturaSplit[] = a.split("((?<=#)|(?=#))");

        List<String> array = Arrays.asList(lecturaSplit);
        ArrayList<String> arrayListSplit = new ArrayList<String>(array);
        int ii = 0;
        for (int i = 1; i < arrayListSplit.size(); i++) {

            while (ii < i) {
                System.out.println(arrayListSplit.get(ii) + " : " + arrayListSplit.get(i));
                if (arrayListSplit.get(ii).equals("#") && arrayListSplit.get(i).equals("#")) {
                    arrayListSplit.add(i, "*");
                }
                ii++;

            }

        }
        StringBuilder lecturaTransformada = new StringBuilder();
        for (String string : arrayListSplit) {
            lecturaTransformada.append(string);
        }
        System.out.println(lecturaTransformada.toString());
        return lecturaTransformada.toString();

    }


}
