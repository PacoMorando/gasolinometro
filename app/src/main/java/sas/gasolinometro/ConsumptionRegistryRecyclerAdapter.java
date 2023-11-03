package sas.gasolinometro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsumptionRegistryRecyclerAdapter extends RecyclerView.Adapter<ConsumptionRegistryRecyclerAdapter.ViewHolder> {
    private final ArrayList<ConsumptionRegister> consumptionRegistries;

    public ConsumptionRegistryRecyclerAdapter(ArrayList<ConsumptionRegister> consumptionRegistries) {
        this.consumptionRegistries = consumptionRegistries;
    }

    @NonNull
    @Override
    public ConsumptionRegistryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumption_register_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumptionRegistryRecyclerAdapter.ViewHolder holder, int position) {
        holder.setViewHolder(this.consumptionRegistries.get(position));
    }

    @Override
    public int getItemCount() {
        return this.consumptionRegistries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final HashMap<String, TextView> registryData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.registryData = new HashMap<>();
            this.registryData.put("lastLoad", itemView.findViewById(R.id.last_load));
            this.registryData.put("vehicleKms", itemView.findViewById(R.id.vehicle_kms));
            this.registryData.put("registerDate", itemView.findViewById(R.id.register_date));
            this.registryData.put("registerHour", itemView.findViewById(R.id.register_hour));
            this.registryData.put("consumption", itemView.findViewById(R.id.consumption));
            this.registryData.put("kmsTraveled", itemView.findViewById(R.id.kms_traveled));
        }

        public void setViewHolder(ConsumptionRegister consumptionRegister) {
            this.registryData.get("lastLoad").setText(consumptionRegister.getLastLoad());
            this.registryData.get("vehicleKms").setText(consumptionRegister.getCurrentVehicleKms());
            this.registryData.get("registerDate").setText(consumptionRegister.getRegisterDate());
            this.registryData.get("registerHour").setText(consumptionRegister.getRegisterHour());
            this.registryData.get("consumption").setText(consumptionRegister.getConsumption());
            this.registryData.get("kmsTraveled").setText(consumptionRegister.getKmsTraveled());
        }
    }
}
