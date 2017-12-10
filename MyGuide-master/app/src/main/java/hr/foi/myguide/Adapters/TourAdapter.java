package hr.foi.myguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import hr.foi.database.entities.Tour;
import hr.foi.myguide.MyTour;
import hr.foi.myguide.R;

/**
 * Created by Mateo on 7.12.2017..
 */

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private Context mCtx;
    private List<Tour> tourList;
    private TourAdapterListener tourAdapterListener;

    public TourAdapter(Context mCtx, List<Tour> tourList) {
        this.mCtx = mCtx;
        this.tourList = tourList;
    }

    public void updateToursList(List<Tour> updatedList) {
        tourList.clear();
        tourList.addAll(updatedList);
        this.notifyDataSetChanged();
    }

    public void setListener(TourAdapterListener listener) {
        this.tourAdapterListener = listener;
    }

    @Override
    public TourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TourViewHolder holder, final int position) {
        Tour tour = tourList.get(position);
        holder.textViewTitle.setText(tour.getNaziv());
        holder.textViewDesc.setText(tour.getOpis());
        holder.textViewPrice.setText(tour.getCijena().toString() + "kn per person");
        holder.textViewTourId.setText(tour.getId_tura().toString());
//        holder.id_layoutRVview.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(mCtx, "Long clicked", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
        holder.id_layoutRVview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tourAdapterListener.itemClicked(position);
            }
        });
        Picasso.with(mCtx)
                .load(tour.getImg_path())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        //public Item currentItem;

        public ViewHolder(View v) {
            super(v);
            view = v;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // item clicked
                }
            });
        }
    }

    class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewTitle, textViewDesc, textViewPrice, textViewTourId;
        RelativeLayout id_layoutRVview;

        public TourViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            //imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewTourId = itemView.findViewById(R.id.textViewTourId);
            id_layoutRVview = itemView.findViewById(R.id.id_layoutRV);
            //textViewImgPath = itemView.findViewById(R.id.textViewImgName)
        }

        @Override
        public void onClick(View v) {

            Integer tourId = Integer.parseInt(textViewTourId.getText().toString());
            Tour instanceToEdit;

            for (int i = 0; i < tourList.size(); i++) {
                if (tourList.get(i).id_tura == tourId) {
                    instanceToEdit = tourList.get(i);
                    Intent intent = new Intent(mCtx, MyTour.class);
                    intent.putExtra("tourToEdit", instanceToEdit);
                    mCtx.startActivity(intent);
                }
            }
        }
    }
}
