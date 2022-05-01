package com.example.la2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleRecyclerVievAdapter extends RecyclerView.Adapter<ScheduleRecyclerVievAdapter.ViewHolder> {
    List<ScheduleClass> scheduleClassList;
    Context context;
    DataBaseHelper dataBaseHelper;

    public ScheduleRecyclerVievAdapter(List<ScheduleClass> scheduleClassList, Context context) {
        this.scheduleClassList = scheduleClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_schedule_del, parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleRecyclerVievAdapter.ViewHolder holder, int position) {
        holder.tv_ScheduleID.setText(String.valueOf(scheduleClassList.get(position).getId()));
        holder.tv_ScheduleName.setText("Имя: " + scheduleClassList.get(position).getStudentName());
        holder.tv_ScheduleDay.setText("День: " + scheduleClassList.get(position).getDay());
        holder.tv_ScheduleTime.setText("Время: " + scheduleClassList.get(position).getTime());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Удалить занятие c id: " + holder.tv_ScheduleID.getText() + " из расписания?")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataBaseHelper = new DataBaseHelper(context);
                                dataBaseHelper.DeleteOneSchedule(Integer.parseInt((String) holder.tv_ScheduleID.getText()));
                                Intent intent = new Intent(context, ScheduleDelete.class);
                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Удаление");
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ScheduleName, tv_ScheduleDay, tv_ScheduleTime,tv_ScheduleID;
        com.google.android.material.card.MaterialCardView parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ScheduleName = itemView.findViewById(R.id.tv_ScheduleName);
            tv_ScheduleDay = itemView.findViewById(R.id.tv_ScheduleDay);
            tv_ScheduleTime = itemView.findViewById(R.id.tv_ScheduleTime);
            tv_ScheduleID = itemView.findViewById(R.id.tv_ScheduleID);
            parentLayout = itemView.findViewById(R.id.card_Schedule);


        }
    }
}
