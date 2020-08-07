package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "DocumentListAdapter";
//    private ArrayList<Document> docList;
    private List<Document> docList;
    private List<Document> filteredDocList;
//    private ArrayList<Document> filteredDocList;
    private LayoutInflater inflater;
    private ItemClickListener documentClickListener;


    public DocumentListAdapter(Context context, List<Document> docList) {
        inflater = LayoutInflater.from(context);
        this.docList = docList;
        filteredDocList = new ArrayList<>(docList);
    }
    // data is passed into the constructor
//    public DocumentListAdapter(Context context, List<String> data) {
//        this.inflater = LayoutInflater.from(context);
//        this.testList = data;
//    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recylerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Document document = docList.get(position);
        holder.documentThumbnail.setImageBitmap(getScaledBitmap(holder.documentThumbnail,
                document.getFilePath()));
        holder.documentDescription.setText(document.toString());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return docList.size();
    }

    // convenience method for getting data at click position
    Document getItem(int id) {
        return docList.get(id);
    }

    @Override
    public Filter getFilter() {
        return dfcFilter;
    }

    private Filter dfcFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Document> filteredList = new ArrayList<>();
            System.out.println(charSequence);
            if(charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(filteredDocList);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Document doc : filteredDocList){
                    if(doc.getDocumentName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(doc);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            docList.clear();
            /**
             * testing statement below
             */
            System.out.println("HERE WE ARE IN THE CODE");
            docList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView documentDescription;
        final ImageView documentThumbnail;

        ViewHolder(View itemView) {
            super(itemView);
            documentThumbnail = itemView.findViewById(R.id.documentListViewImage);
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

    /* image scaling functions */
    private Bitmap getScaledBitmap(ImageView imageView, String currentImagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        imageView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width = imageView.getMeasuredWidth();
        int height = imageView.getMeasuredHeight();
        System.out.println(String.format("THE WIDTH IS: %S and HEIGHT IS %S",width,height));
        int scaleFactor = Math.min(
                options.outWidth / width,
                options.outHeight / height
        );
        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        options.inPurgeable = true;
        return BitmapFactory.decodeFile(currentImagePath, options);
    }
}
