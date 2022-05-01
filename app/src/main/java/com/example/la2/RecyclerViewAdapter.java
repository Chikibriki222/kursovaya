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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Student> studentList;
    Context context;
    DataBaseHelper dataBaseHelper;

    public RecyclerViewAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_student, parent,false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }
//локализовать!!!



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_id.setText(String.valueOf(studentList.get(position).getId()));
        holder.tv_studname.setText("Имя: "+studentList.get(position).getName());
        holder.tv_studsurname.setText("Фамилия: "+studentList.get(position).getSurname());
        holder.tv_studage.setText(String.valueOf("Возраст: "+studentList.get(position).getAge()));
        holder.tv_studparentname.setText("Родитель: "+studentList.get(position).getParentName());
        holder.tv_studparentphone.setText("Телефон: " +String.valueOf(studentList.get(position).getParentPhone())); // может не работать( пытался задать лонг в поле для стринги)
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Удалить ученика c id: "+ holder.tv_id.getText())
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataBaseHelper = new DataBaseHelper(context);
                                dataBaseHelper.DeleteOneStudent(Integer.parseInt((String) holder.tv_id.getText()));
                                Intent intent = new Intent(context, Students.class);
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
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_studname;
        TextView tv_studsurname;
        TextView tv_studage;
        TextView tv_studparentname;
        TextView tv_studparentphone;
        TextView tv_id;
        com.google.android.material.card.MaterialCardView parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_studname = itemView.findViewById(R.id.tv_studname);
            tv_studsurname = itemView.findViewById(R.id.tv_studsurname);
            tv_studage = itemView.findViewById(R.id.tv_studage);
            tv_studparentname = itemView.findViewById(R.id.tv_studparentname);
            tv_studparentphone = itemView.findViewById(R.id.tv_studparentphone);
            tv_id = itemView.findViewById(R.id.tv_id);
            parentLayout = itemView.findViewById(R.id.card_student);

        }
    }
}
