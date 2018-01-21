package hr.foi.myguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import hr.foi.database.entities.Termin;
import hr.foi.database.entities.Tour;

/**
 * Created by Mateo on 21.1.2018..
 */

public class TerminAdapter {
    private Context mCtx;
    private List<Termin> terminList;
    private TourAdapterListener tourAdapterListener;

    public TerminAdapter(Context mCtx, List<Termin> terminList) {
        this.mCtx = mCtx;
        this.terminList = terminList;
    }
}
