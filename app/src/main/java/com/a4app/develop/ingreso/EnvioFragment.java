package com.a4app.develop.ingreso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a4app.develop.ingreso.modelo.Lote;
import com.a4app.develop.ingreso.modelo.LoteAdapter;
import com.a4app.develop.ingreso.modelo.Respuesta;
import com.a4app.develop.ingreso.modelo.RollosService;
import com.a4app.develop.ingreso.modelo.SwipeToDeleteCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EnvioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EnvioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnvioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TableLayout tableLayout;
    private View vista;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



   private ArrayList<Lote> lotes;
   private RecyclerView rvRollos;
   private RecyclerView mAdapter;
   private LoteAdapter adapter;
   private LinearLayout rolloLayout;
   private Context context;

    public EnvioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EnvioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EnvioFragment newInstance(String param1, String param2) {
        EnvioFragment fragment = new EnvioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        vista = inflater.inflate(R.layout.fragment_envio, container, false);
        //tableLayout = (TableLayout) vista.findViewById(R.id.tablaRollo);
        rvRollos = (RecyclerView) vista.findViewById(R.id.rvTablaRollos);
        rolloLayout = (LinearLayout) vista.findViewById(R.id.tablaRollosLayaout);
        Button btonTransportar = (Button) vista.findViewById(R.id.btnTransportar);
        btonTransportar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if(validadConexion()) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.1.2.102:8080/apiTraslados/apiTraslados/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RollosService rollosService = retrofit.create(RollosService.class);

                    Call<List<Respuesta>> call = rollosService.enviaLotes(lotes);
                    call.enqueue(new Callback<List<Respuesta>>() {
                        @Override
                        public void onResponse(Call<List<Respuesta>> call, Response<List<Respuesta>> response) {
                            ArrayList<Respuesta> respuestas = (ArrayList<Respuesta>) response.body();
                            for (Respuesta a : respuestas
                            ) {
                                Log.i("ApiRestfull", a.getTipo());
                                Log.i("ApiRestfull", a.getMensaje());
                            }
                            Intent intent = new Intent(vista.getContext(), MensajesActivity.class);
                            intent.putParcelableArrayListExtra("respuestas", respuestas);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<List<Respuesta>> call, Throwable t) {
                            Toast toast = Toast.makeText(context, "Error conexión a SAP", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });
                }else{
                    Toast toast = Toast.makeText(context, "Error de Conexion a red", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
        lotes = new ArrayList<Lote>();
        adapter = new LoteAdapter(lotes);
        // Attach the adapter to the recyclerview to populate items
        rvRollos.setAdapter(adapter);
        // Set layout manager to position the items
        context = vista.getContext();
        rvRollos.setLayoutManager(new LinearLayoutManager(context));
        enableSwipeToDeleteAndUndo();
        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

/*
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public boolean PasaLote(Lote lote){

        if(!exiteLote(lote)) {
        populateTable(lote);
            return true;
        }
        return false;

    }

    public void populateTable(Lote lote){

        // Initialize contacts
           // Create adapter passing in the sample user data

            adapter.addItem(lote, 0);
            calculaKg();


    }
    public void calculaKg(){
        String kg = "";
        double kgd = 0;
        for (Lote lot:lotes
             ) {
            kgd = lot.getCantidad() +kgd;
        }
        kg = String.valueOf(kgd);
        TextView kilosText = (TextView) vista.findViewById(R.id.tvTotalKg);
        kilosText.setText(kg);

    }
    private void enableSwipeToDeleteAndUndo() {

        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(context) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Lote item = adapter.getLotes().get(position);

                adapter.removeItem(position);
                calculaKg();


                Snackbar snackbar = Snackbar
                        .make(rolloLayout, "Lote borrado de la lista.", Snackbar.LENGTH_LONG);
                snackbar.setAction("Deshacer", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        adapter.restoreItem(item, position);
                        rvRollos.scrollToPosition(position);
                        calculaKg();
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(rvRollos);
    }

    /**
     * Valida  si existe el rollo que es pasado por parámetro en la tabla de rollos.
     * @param rollo rollo a evaluar
     * @return True si el rollo a evaluar existe en a tabla, False si no el rollo a evaluar  no está en la tabla  de rollos
     */
    public boolean exiteLote(Lote rollo){
        for (Lote lote : lotes) {
            if( lote.getNumLote().equalsIgnoreCase(rollo.getNumLote())){
                return true;
            }
        }
        return false;
    }

    public boolean validadConexion() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

}
