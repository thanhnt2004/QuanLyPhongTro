package com.example.quanlyphongtro;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<Room> roomList;
    private OnRoomClickListener listener;

    public interface OnRoomClickListener {
        void onRoomClick(Room room, int position);
        void onRoomLongClick(Room room, int position);
    }

    public RoomAdapter(List<Room> roomList, OnRoomClickListener listener) {
        this.roomList = roomList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        if (room == null) return;

        holder.tvRoomName.setText(room.getTenPhong());
        
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.tvRoomPrice.setText("Giá: " + formatter.format(room.getGiaThue()) + " VND");
        
        holder.tvRoomStatus.setText(room.getTinhTrang());

        if ("Còn trống".equalsIgnoreCase(room.getTinhTrang())) {
            holder.tvRoomStatus.setTextColor(Color.GREEN);
        } else if ("Đã thuê".equalsIgnoreCase(room.getTinhTrang())) {
            holder.tvRoomStatus.setTextColor(Color.RED);
        } else {
            holder.tvRoomStatus.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onRoomClick(room, position);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) {
                listener.onRoomLongClick(room, position);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return roomList != null ? roomList.size() : 0;
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomName, tvRoomPrice, tvRoomStatus;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomName = itemView.findViewById(R.id.tv_room_name);
            tvRoomPrice = itemView.findViewById(R.id.tv_room_price);
            tvRoomStatus = itemView.findViewById(R.id.tv_room_status);
        }
    }
}
