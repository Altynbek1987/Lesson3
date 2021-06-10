package com.example.lesson3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TaskModel> list= new ArrayList<>();
    private LayoutInflater inflater;
    MainActivity activity;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TaskAdapter(Context context, MainActivity activity){
        //this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.activity = activity;
    }

    public void addTask(TaskModel model){
        list.add(model);
        notifyDataSetChanged();
    }

    public void deleteTask(TaskModel model, int position){
        list.remove(model);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_holder, parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
       // holder.textTitle.setText(list.get(position));
         holder.onBind(list.get(position));
//        holder.textTitle.setText(list.get(position).getTitle());
//        holder.textDescription.setText(list.get(position).getDescription());
//        holder.txtDate.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setList(List<TaskModel> tasks) {
         list.clear();
        list.addAll(tasks);
        notifyDataSetChanged();
    }
    public void deleteTack(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescription , txtDate;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.holder_txt_title);
            textDescription = itemView.findViewById(R.id.holder_txt_description);
            txtDate = itemView.findViewById(R.id.item_date_txt);
        }

        public void onBind(TaskModel model) {
            textTitle.setText(model.getTitle());
            textDescription.setText(model.getDescription());
            txtDate.setText(model.getDate());
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog dialog = new AlertDialog.Builder(itemView.getContext()).create();
                    dialog.setTitle("Внимание!");
                    dialog.setMessage("Выхотите удалить?");
                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteTack(getAdapterPosition());

                        }
                    });
                    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
