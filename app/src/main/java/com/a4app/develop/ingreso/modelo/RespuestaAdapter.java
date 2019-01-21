package com.a4app.develop.ingreso.modelo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a4app.develop.ingreso.R;

import java.util.List;

public class RespuestaAdapter extends
        RecyclerView.Adapter<RespuestaAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    private List<Respuesta> respuestas;

    // Pass in the contact array into the constructor
    public RespuestaAdapter(List<Respuesta> respuestas) {
        setRespuestas(respuestas);
    }

    @Override
    public RespuestaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_respuesta, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context,contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RespuestaAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Respuesta respuesta = getRespuestas().get(position);

        // Set item views based on your views and data model
        TextView textViewTipoMensaje = viewHolder.textTipo;
        textViewTipoMensaje.setText(respuesta.getTipo());
        TextView textViewMensaje = viewHolder.textMensaje;
        textViewMensaje.setText(respuesta.getMensaje());


    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return getRespuestas().size();
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> mContacts) {
        this.respuestas = mContacts;
    }
    public void removeItem(int position) {
        respuestas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, respuestas.size());
    }

    public void restoreItem(Respuesta item, int position) {
        respuestas.add(position, item);
        notifyItemInserted(position);
    }
    public void addItem(Respuesta item, int position) {
        respuestas.add(position, item);
        notifyItemInserted(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener */{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView textTipo;
        public TextView textMensaje;
        private Context context;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            textTipo = (TextView) itemView.findViewById(R.id.tvRtipError);
            textMensaje = (TextView) itemView.findViewById(R.id.tvRmensaje);

        }
        public ViewHolder(Context context, View itemView) {
            super(itemView);
            textTipo = (TextView) itemView.findViewById(R.id.tvRtipError);
            textMensaje = (TextView) itemView.findViewById(R.id.tvRmensaje);
            // Store the context
            this.context = context;

            // Attach a click listener to the entire row view
            //itemView.setOnClickListener(this);
        }
    /*    @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Contact contact = getmContacts().get(position) ;
                // We can access the data within the views
           //     Toast.makeText(context, nameTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        }*/

    }

}
