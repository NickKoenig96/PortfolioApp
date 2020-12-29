package com.example.portfolio_app_v2;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {
    private List<Portfolio> allPortfolioItems;
    private Context context;

    public PortfolioAdapter(Context ctx, List<Portfolio> portfolios){
        this.allPortfolioItems = portfolios;
        this.context = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfolio_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(allPortfolioItems.get(position).getTitle());

            String imageString = (allPortfolioItems.get(position).getImage());
            byte[] imageAsBytes = Base64.decode(imageString.getBytes(), Base64.DEFAULT);
            holder.portfolioImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0 , imageAsBytes.length));

        //expicit intent
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putSerializable("portfolioData",allPortfolioItems.get(position));
                    Intent i = new Intent(context,Portfolio_item.class);
                    i.putExtras(b);
                    v.getContext().startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return allPortfolioItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView portfolioImage;
            TextView title;
            TextView count;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                portfolioImage = itemView.findViewById(R.id.projectImage);
                title = itemView.findViewById(R.id.Title);
        }
    }
}
