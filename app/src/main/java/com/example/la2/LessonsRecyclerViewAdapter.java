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

public class LessonsRecyclerViewAdapter extends RecyclerView.Adapter<LessonsRecyclerViewAdapter.ViewHolder>{

    List<Lesson> lessonList;
    Context context;
    DataBaseHelper dataBaseHelper;

    public LessonsRecyclerViewAdapter(List<Lesson> lessonList, Context context) {
        this.lessonList = lessonList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_lesson, parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_LessonID.setText(String.valueOf(lessonList.get(position).getId()));
        holder.tv_lessonName.setText("Имя: "+lessonList.get(position).getStudentName());
        holder.tv_Date.setText("Дата: "+lessonList.get(position).getDate());
        holder.tv_Time.setText("Время: "+lessonList.get(position).getTime());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Удалить занятие c id: "+ holder.tv_LessonID.getText())
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataBaseHelper = new DataBaseHelper(context);
                                dataBaseHelper.DeleteOneLesson(Integer.parseInt((String) holder.tv_LessonID.getText()));
                                Intent intent = new Intent(context, Lessons.class);
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
        return lessonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_lessonName;
        TextView tv_Date;
        TextView tv_Time;
        TextView tv_LessonID;
        com.google.android.material.card.MaterialCardView parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_lessonName = itemView.findViewById(R.id.tv_LessonName);
            tv_Date = itemView.findViewById(R.id.tv_LessonDate);
            tv_Time = itemView.findViewById(R.id.tv_LessonTime);
            tv_LessonID = itemView.findViewById(R.id.tv_LessonID);
            parentLayout = itemView.findViewById(R.id.card_Lesson);


        }
    }

}
