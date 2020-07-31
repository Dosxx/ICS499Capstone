package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.ViewHolder> {

    private static final String TAG = "DocumentListAdapter";
    private ArrayList<Document> docList = new ArrayList<>();
    private LayoutInflater inflater;
    private List<String> testList = new ArrayList<>();
    private ItemClickListener documentClickListener;


    public DocumentListAdapter(Context context, ArrayList<Document> docList) {
        inflater = LayoutInflater.from(context);
        this.docList = docList;
    }
    // data is passed into the constructor
    public DocumentListAdapter(Context context, List<String> data) {
        this.inflater = LayoutInflater.from(context);
        this.testList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recylerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String document = testList.get(position);
        holder.myTextView.setText(document);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return testList.size();
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return testList.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
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

//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            long documentID = getItem(position).getDocumentID();
//            String documentName = getItem(position).getDocumentName();
//            String documentCreateDate = getItem(position).getCreatedDate();
//            String documentLastEdit = getItem(position).getLastEditDate();
////            File documentFile = getItem(position).getFile();
//            final Document currentDocument = getItem(position);
//
//            LayoutInflater inflater = LayoutInflater.from(myContext);
//            convertView = inflater.inflate(myResource, parent, false);
//
////            TextView tvStudyID = convertView.findViewById(R.id.list_study_id);
////            TextView tvStudyName = convertView.findViewById(R.id.list_study_name);
////            Button btnViewStudyActivity = convertView.findViewById(R.id.view_study_btn);
////
////            tvStudyID.setText(studyID);
////            tvStudyName.setText(studyName);
////            btnViewStudyActivity.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent viewStudyIntent = new Intent(myContext, ViewStudyActivity.class);
////                    viewStudyIntent.putExtra("studyID", currentStudy.getStudyID());
////                    myContext.startActivity(viewStudyIntent);
////                }
////            });
//
//            return convertView;
//        }


    // inflates the row layout from xml when needed


    // binds the data to the TextView in each row

}
