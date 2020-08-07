package com.ICS499.ThrownException.DigitalFileCabinet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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
    private List<Document> docList;
    private List<Document> docListFiltered;
    private LayoutInflater inflater;
    private ItemClickListener documentClickListener;

    /* The adapter constructor*/
    public DocumentListAdapter(Context context, List<Document> docList) {
        inflater = LayoutInflater.from(context);
        this.docList = docList;
        docListFiltered = new ArrayList<>(docList);
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View recyclerView = inflater.inflate(R.layout.recylerview_layout, parent, false);
        return new ViewHolder(recyclerView);
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
                filteredList.addAll(docListFiltered);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Document doc : docListFiltered){
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
            Log.d(TAG,"PUBLISHING FILTERED LIST");
            docList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    // ViewHolder class stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView documentThumbnail;
        final TextView documentDescription;

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

    // parent activity will implement this interface to respond to click events
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
        int scaleFactor = Math.min(
                options.outWidth / width,
                options.outHeight / height
        );
        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        return BitmapFactory.decodeFile(currentImagePath, options);
    }
}
