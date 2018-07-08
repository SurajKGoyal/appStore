package com.surajgoyal.store2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Store extends AppCompatActivity {

    RecyclerView itemList;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        ContentAdapter adapter = new ContentAdapter(getApplicationContext());


        itemList = (RecyclerView)findViewById(R.id.item_list);
        itemList.setAdapter(adapter);
        itemList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        itemList.setLayoutManager(mLayoutManager);

    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView name;
        public Button button;
        ;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
            icon = (ImageView) itemView.findViewById(R.id.icon);
            name = (TextView) itemView.findViewById(R.id.name);
            button = (Button) itemView.findViewById(R.id.button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                  //  Intent intent = new Intent(context, DetailActivity.class);
                 //   intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    Pair<View, String> p1 = Pair.create((View) icon, "iconTransition");
                    Pair<View, String> p2 = Pair.create((View)name, "nameTransition");
                    Pair<View, String> p3 = Pair.create((View)button, "buttonTransition");
                    //type cast the context to activity for makeSceneTransitionAnimation();
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity)context,p1,p2,p3);
                    Intent intent = new Intent(context, DetailActivity.class);
                    context.startActivity(intent, options.toBundle());
                }
            });

        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Card in RecyclerView.
        private static final int LENGTH = 10;

        private final String[] games;


        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            games = resources.getStringArray(R.array.games);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //  holder.icon.getDrawable();
            holder.name.setText(games[position % games.length]);


        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }




}
