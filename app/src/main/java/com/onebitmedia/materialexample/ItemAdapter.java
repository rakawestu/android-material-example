package com.onebitmedia.materialexample;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rakawm
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int TYPE_SAMPLE = 101;
    public static final int TYPE_OPTION = 102;

    private List<Item> items;
    private ItemListener listener;

    public ItemAdapter() {
        this.items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_OPTION) {
            return new OptionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false));
        } else if(viewType==TYPE_SAMPLE) {
            return new SampleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sample, parent, false));
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = items.get(position);
        if(holder.getItemViewType()==TYPE_OPTION) {
            OptionViewHolder optionViewHolder = (OptionViewHolder)holder;
            optionViewHolder.title.setText(item.getTitle());
            optionViewHolder.description.setText(item.getDescription());
            optionViewHolder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemAccept(item);
                    }
                }
            });
            optionViewHolder.refuse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemRefuse(item);
                    }
                }
            });
        } else if(holder.getItemViewType()==TYPE_SAMPLE) {
            SampleViewHolder sampleViewHolder = (SampleViewHolder)holder;
            sampleViewHolder.title.setText(item.getTitle());
            sampleViewHolder.description.setText(item.getDescription());
            sampleViewHolder.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null) {
                        listener.onItemDetail(item);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position).getType().equals(Item.TYPE_OPTION)) {
            return TYPE_OPTION;
        } else if(items.get(position).getType().equals(Item.TYPE_SAMPLE)){
            return TYPE_SAMPLE;
        } else {
            return 0;
        }
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    class OptionViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        Button accept, refuse;
        CardView container;

        public OptionViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            accept = (Button)itemView.findViewById(R.id.accept);
            refuse = (Button)itemView.findViewById(R.id.refuse);
            container = (CardView)itemView.findViewById(R.id.container);
        }
    }

    class SampleViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        Button detail;
        CardView container;

        public SampleViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            detail = (Button)itemView.findViewById(R.id.detail);
            container = (CardView)itemView.findViewById(R.id.container);
        }
    }

    interface ItemListener {
        void onItemAccept(Item item);
        void onItemRefuse(Item item);
        void onItemDetail(Item item);
    }
}
