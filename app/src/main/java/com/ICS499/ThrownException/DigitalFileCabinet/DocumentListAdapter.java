package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.ViewHolder> {

    private static final String TAG = "DocumentListAdapter";
    private ArrayList<Document> docList;
    private LayoutInflater inflater;
//    private List<String> testList = new ArrayList<>();
    private ItemClickListener documentClickListener;


    public DocumentListAdapter(Context context, ArrayList<Document> docList) {
        inflater = LayoutInflater.from(context);
        this.docList = docList;
    }
    // data is passed into the constructor
//    public DocumentListAdapter(Context context, List<String> data) {
//        this.inflater = LayoutInflater.from(context);
//        this.testList = data;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recylerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String document = testList.get(position);
        Document document = docList.get(position);
        holder.documentName.setText(document.getDocumentName());
        holder.documentDescription.setText(document.toString());
    }

    // total number of rows
    @Override
    public int getItemCount() {
//        return testList.size();
        return docList.size();
    }

    // convenience method for getting data at click position
    Document getItem(int id) {
//        return testList.get(id);
        return docList.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView documentName, documentDescription;

        ViewHolder(View itemView) {
            super(itemView);
            documentName = itemView.findViewById(R.id.documentNameTextView);
            documentDescription = itemView.findViewById(R.id.doc_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (documentClickListener != null) documentClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.documentClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
