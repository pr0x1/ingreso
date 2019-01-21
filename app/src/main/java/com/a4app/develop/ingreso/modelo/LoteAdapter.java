package com.a4app.develop.ingreso.modelo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.a4app.develop.ingreso.R;

import java.util.List;

public class LoteAdapter extends
        RecyclerView.Adapter<LoteAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    private List<Lote> lotes;

    // Pass in the contact array into the constructor
    public LoteAdapter(List<Lote> lotes) {
        setmContacts(lotes);
    }

    @Override
    public LoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context,contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(LoteAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Lote contact = getLotes().get(position);

        // Set item views based on your views and data model
        TextView textViewRollo = viewHolder.textRollo;
        textViewRollo.setText(contact.getNumLote());
        TextView textViewMaterial = viewHolder.textMaterial;
        textViewMaterial.setText(contact.getMaterial());
        TextView textViewPeso = viewHolder.textPeso;
        textViewPeso.setText(String.valueOf(contact.getCantidad()));

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return getLotes().size();
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setmContacts(List<Lote> mContacts) {
        this.lotes = mContacts;
    }
    public void removeItem(int position) {
        lotes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, lotes.size());
    }

    public void restoreItem(Lote item, int position) {
        lotes.add(position, item);
        notifyItemInserted(position);
    }
    public void addItem(Lote item, int position) {
        lotes.add(position, item);
        notifyItemInserted(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener */{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView textRollo;
        public TextView textMaterial;
        public TextView textPeso;
        public Button messageButton;
        public TextView tvName;
        public TextView tvHometown;
        private Context context;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            textRollo = (TextView) itemView.findViewById(R.id.Lote1);
            textMaterial = (TextView) itemView.findViewById(R.id.material1);
            textPeso = (TextView) itemView.findViewById(R.id.peso1);
        }
        public ViewHolder(Context context, View itemView) {
            super(itemView);
            textRollo = (TextView) itemView.findViewById(R.id.Lote1);
            textMaterial = (TextView) itemView.findViewById(R.id.material1);
            textPeso = (TextView) itemView.findViewById(R.id.peso1);
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
