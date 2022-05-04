package com.akinpelumi.crop2cashtest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akinpelumi.crop2cashtest.R;
import com.akinpelumi.crop2cashtest.callback.ISingleExhibitSelectionCallback;
import com.akinpelumi.crop2cashtest.model.ExhibitModel;

import java.util.List;


public class ExhibitAdapter extends RecyclerView.Adapter<ExhibitAdapter.ViewHolder>{
    private List<ExhibitModel> localDataSet;
    private ExhibitHorizontalImageAdapter exhibitImages;
    private Context mContext;
    private final LayoutInflater mInflater;
    public static final String TAG = "ExhibitAdapter";
    private ISingleExhibitSelectionCallback mSingleExhibitSelectionCallBack;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
//    public TransactionsRecyclerAdapter(List<Datum> dataSet) {
//        localDataSet = dataSet;
//    }

    public ExhibitAdapter(Context context, List<ExhibitModel> dataSet, ISingleExhibitSelectionCallback singleExhibitSelectionCallBack) {
        this.localDataSet = dataSet;
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mSingleExhibitSelectionCallBack = singleExhibitSelectionCallBack;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = mInflater.inflate(R.layout.exhibit_card_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.d(TAG,"CreateView");
        return viewHolder;
//        View view = LayoutInflater.from(mContext)
//                .inflate(R.layout.exhibit_card_view, viewGroup, false);
//
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExhibitAdapter.ViewHolder holder, int position) {
        ExhibitModel currentitem = localDataSet.get(position);
        //holder.imageView.setImageResource();
        holder.imageRecyclerView.setAdapter(new ExhibitHorizontalImageAdapter(mContext, localDataSet.get(position).images));
        holder.imageRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.imageRecyclerView.setHasFixedSize(true);

        holder.titleTitleView.setText(currentitem.title);

        //set click callback
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingleExhibitSelectionCallBack.onSelect(currentitem);
            }
        });
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
        private final TextView titleTitleView;
        private final RecyclerView imageRecyclerView;
        private final View view;

        public ViewHolder(@NonNull View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            titleTitleView = (TextView) view.findViewById(R.id.xhibitTitle);
            imageRecyclerView = (RecyclerView) view.findViewById(R.id.xhibitImageRecycler);
            this.view = view;
        }


        public TextView getxhibitTitleTextView() {
            return titleTitleView;
        }
        public RecyclerView getxhibitImageView() {
            return imageRecyclerView;
        }

        //public TextView getAmountTextView() {
//            return amountTextView;
//        }

        public View getView(){
            return view;
        }
    }

}
