package com.cu.fbguide.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.cu.fbguide.Activity.WebActivity;
import com.cu.fbguide.Model.FB;
import com.cu.fbguide.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.myatminsoe.mdetect.MDetect;
import me.myatminsoe.mdetect.MMTextView;
import me.myatminsoe.mdetect.Rabbit;

public class FBAdapter extends RecyclerView.Adapter<FBAdapter.ViewHolder> {
    Context context;
    ArrayList<FB> fbArrayList;

    public FBAdapter(Context context, ArrayList<FB> fbArrayList) {
        this.context = context;
        this.fbArrayList = fbArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fb_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (MDetect.INSTANCE.isUnicode()){
            holder.name.setText(Rabbit.zg2uni(fbArrayList.get(position).getName()));
        } else {
            holder.name.setText(fbArrayList.get(position).getName());
        }

    }

    @Override
    public int getItemCount() {
        return fbArrayList.size();
    }

    public Filter getFilter() {
        return searchFilter;
    }
    private Filter searchFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<FB> filterList=new ArrayList<>();
            if(charSequence==null || charSequence.length()==0 || charSequence.equals("")){
                filterList.addAll(fbArrayList);
            }else {
                String filterPattern=charSequence.toString().toLowerCase().trim();
                for(FB fb:fbArrayList){
                    if(fb.getName().toLowerCase().contains(filterPattern)){
                        filterList.add(fb);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            fbArrayList.clear();
            fbArrayList.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name=itemView.findViewById(R.id.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, WebActivity.class);
                    intent.putExtra("Name",fbArrayList.get(getAdapterPosition()).getName());
                    intent.putExtra("Url",fbArrayList.get(getAdapterPosition()).getUrl());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
