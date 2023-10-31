package sas.gasolinometro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ConsumptionRegistryRecyclerAdapter extends RecyclerView.Adapter<ConsumptionRegistryRecyclerAdapter.ViewHolder> {
    @NonNull
    @Override
    public ConsumptionRegistryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumption_register_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumptionRegistryRecyclerAdapter.ViewHolder holder, int position) {
        holder.consumptionItem.setText("Itme number " + position);
    }

    @Override
    public int getItemCount() {
        return 41;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView consumptionItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.consumptionItem = itemView.findViewById(R.id.consumption_item);
        }

    }
}
