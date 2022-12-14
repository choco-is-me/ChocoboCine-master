package com.example.cinemaapp.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Reservation;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationHolder> {

    private List<Reservation> reservationList = new ArrayList<>();
    private final Fragment context;

    public ReservationAdapter(Fragment context) {

        this.context = context;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public Fragment getContext() {
        return context;
    }

    class ReservationHolder extends RecyclerView.ViewHolder {

        Reservation reservationObject;

        TextView textTitle;
        TextView textStartingTime;
        TextView reservedSeats;
        ImageView qrCode;
        Dialog popup;

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        ReservationHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.film_title_text);
            textStartingTime = itemView.findViewById(R.id.starting_time_text);

            popup = new Dialog(Objects.requireNonNull(context.getActivity()));
            popup.setContentView(R.layout.popup_activity);

            reservedSeats = popup.findViewById(R.id.reserved_seats);
            qrCode = popup.findViewById(R.id.qr_code);

            Button popupButton = itemView.findViewById(R.id.detail_button);
            popupButton.setOnClickListener(v -> {
                reservedSeats.setText(getSeatsAsString(reservationObject.getPlaces()));
                openPopup();
            });
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void openPopup() {

            popup.show();

            ImageView close = popup.findViewById(R.id.close_popup);
            close.setOnClickListener(v -> popup.dismiss());
        }

        public String getSeatsAsString(List<Integer> seatList) {

            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Integer> iterator = seatList.iterator();

            while (iterator.hasNext()) {

                //to print correct number
                int seatPlusOne = iterator.next() + 1;

                stringBuilder.append(seatPlusOne);
                if (iterator.hasNext()) {

                    stringBuilder.append(", ");
                }
            }

            return stringBuilder.toString();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public ReservationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //create new View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_card, parent, false);

        return new ReservationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationHolder reservationHolder, int position) {

        reservationHolder.reservationObject = reservationList.get(position);

        reservationHolder.textTitle.setText(reservationHolder.reservationObject.getFilm().getTitle());

        Time time = reservationHolder.reservationObject.getStartTime();
        Date date = new Date(time.getTime());

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("kk:mm");
        reservationHolder.textStartingTime.setText(dateFormat.format(date));
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

}
