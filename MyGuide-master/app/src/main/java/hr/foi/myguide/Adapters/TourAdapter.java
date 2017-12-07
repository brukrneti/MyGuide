package hr.foi.myguide.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import hr.foi.database.entities.Tour;
import hr.foi.myguide.R;

/**
 * Created by Mateo on 7.12.2017..
 */

public class TourAdapter extends  RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private Context mCtx;
    private List<Tour> tourList;

    public TourAdapter(Context mCtx, List<Tour> tourList) {
        this.mCtx = mCtx;
        this.tourList = tourList;
    }

    public void updateToursList(List<Tour> updatedList) {
        tourList.clear();
        tourList.addAll(updatedList);
        this.notifyDataSetChanged();
    }


    @Override
    public TourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view= inflater.inflate(R.layout.list_layout, null);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TourViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        holder.textViewTitle.setText(tour.getNaziv());
        holder.textViewDesc.setText(tour.getOpis());
     //   holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(tour.getImg_path()));
        holder.textViewImgPath.setText(tour.getImg_name());
//        Drawable drawable = LoadImageFromWebOperations("http://forum.roda.hr/images/customavatars/avatar10164_2.gif");
//        imgView.setBackgroundDrawable(drawable);
//        try {
//            URL thumb_u = new URL(tour.getImg_path());
//            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
//            holder.imageView.setImageDrawable(thumb_d);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    class TourViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle, textViewDesc, textViewImgPath;
        public TourViewHolder(View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewImgPath = itemView.findViewById(R.id.textViewImgPath);
        }
    }
}
