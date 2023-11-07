package sas.gasolinometro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ConsumptionRegistryRecyclerAdapter extends RecyclerView.Adapter<ConsumptionRegistryRecyclerAdapter.ViewHolder> implements DeleteConfirmDialog.RegisterDeleter {
    private final ViewUpdater viewUpdater;
    private final RegistryController registryController;
    private final ArrayList<ConsumptionRegister> consumptionRegistries;

    public ConsumptionRegistryRecyclerAdapter(ViewUpdater viewUpdater, RegistryController registryController) {
        this.viewUpdater = viewUpdater;
        this.registryController = registryController;
        this.consumptionRegistries = registryController.getConsumptionRegistries();
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
        holder.itemView.setOnLongClickListener(view -> {
            new DeleteConfirmDialog(this, holder).show(((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager(), "Delete");
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return this.consumptionRegistries.size();
    }

    @Override
    public void deleteRegister(int position) {
        this.registryController.deleteRegister(this.consumptionRegistries.get(position));
        this.consumptionRegistries.remove(this.consumptionRegistries.get(position));
        this.notifyDataSetChanged();
        this.registryController.setPreviousVehicleKms();
        this.viewUpdater.updateViews();
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
            this.registryData.get("lastLoad").setText(this.getGasLoadedText(consumptionRegister.getGasLoaded()));
            this.registryData.get("vehicleKms").setText(this.getCurrentVehicleKmsText(consumptionRegister.getCurrentVehicleKms()));
            this.registryData.get("registerDate").setText(this.getRegisterDateText(consumptionRegister.getDate()));
            this.registryData.get("registerHour").setText(this.getRegisterHourText(consumptionRegister.getDate()));
            this.registryData.get("consumption").setText(this.getConsumptionText(consumptionRegister.getConsumption()));
            this.registryData.get("kmsTraveled").setText(this.getKmsTraveledText(consumptionRegister.getKmsTraveled()));
        }

        private String getGasLoadedText(float gasLoaded) {
            return gasLoaded + "lts.";
        }

        private String getCurrentVehicleKmsText(float consumptionRegister) {
            return "Vehicle: " + consumptionRegister + " km";
        }

        private String getRegisterDateText(Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
            String formattedDate = dateFormat.format(date);
            return formattedDate;
        }

        private String getRegisterHourText(Date date) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("E hh:mm a", new Locale("es", "ES"));
            String formattedTime = timeFormat.format(date);
            return formattedTime;
        }

        private String getConsumptionText(float consumption) {
            return String.valueOf((float) Math.round(consumption * 1000) / 1000);
        }

        private String getKmsTraveledText(float kmsTraveled) {
            return String.valueOf((float) Math.round((kmsTraveled) * 100) / 100);
        }

        public CharSequence getDate() {
            return this.registryData.get("registerDate").getText();
        }

        public CharSequence getConsumption() {
            return this.registryData.get("consumption").getText();
        }

        public CharSequence getVehicleKms() {
            return this.registryData.get("vehicleKms").getText();
        }
    }
}
