package com.akinpelumi.crop2cashtest.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akinpelumi.crop2cashtest.R;
import com.akinpelumi.crop2cashtest.model.ExhibitModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExhibitHorizontalImageAdapter extends RecyclerView.Adapter<ExhibitHorizontalImageAdapter.ViewHolder>{
    private List<String> localDataSet;
    private final LayoutInflater mInflater;
    public static final String TAG = "ExhibitHorizontalImageAdapter";

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */

    public ExhibitHorizontalImageAdapter(Context context, List<String> dataSet) {
        localDataSet = dataSet;
        this.mInflater = LayoutInflater.from(context);
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = mInflater.inflate(R.layout.horizontal_image_view, viewGroup, false);
        ExhibitHorizontalImageAdapter.ViewHolder viewHolder = new ExhibitHorizontalImageAdapter.ViewHolder(view);
        Log.d(TAG,"CreateView");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExhibitHorizontalImageAdapter.ViewHolder holder, int position) {
        Picasso.get().load(localDataSet.get(position)).into(holder.imgView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgView;
        private final View view;

        public ViewHolder(View itemView) {
            super(itemView);

            imgView = (ImageView) itemView.findViewById(R.id.ivExhibit);
            this.view = itemView;
        }
    }

}
