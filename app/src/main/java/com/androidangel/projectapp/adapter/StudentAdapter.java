package com.androidangel.projectapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.androidangel.projectapp.ProfileActivity;
import com.androidangel.projectapp.R;
import com.androidangel.projectapp.UpdateActivity;
import com.androidangel.projectapp.data.Student;
import com.androidangel.projectapp.database.StudentDbHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> studentArrayList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    public StudentAdapter(List<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView studentNoTV, nameTv;
        public ImageView studentImageImgV;

        public ToggleButton toggleBtn;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            studentNoTV = v.findViewById(R.id.studentNoList);
            nameTv = v.findViewById(R.id.namelist);
            studentImageImgV = v.findViewById(R.id.imagelist);
            toggleBtn = v.findViewById(R.id.toggleBtn);
        }
    }

    public void add(int position, Student student){
        studentArrayList.add(position, student);
        notifyItemInserted(position);
    }
    public void remove(int position){
        studentArrayList.remove(position);
        notifyItemRemoved(position);
    }



    public StudentAdapter(List<Student> myStudent, Context context, RecyclerView recyclerView){
        studentArrayList = myStudent;
        this.mContext = context;
        mRecyclerV = recyclerView;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        final Student student = studentArrayList.get(position);
        holder.studentNoTV.setText("StudentNo : " + student.getStudentNumber());
        holder.nameTv.setText("Lastname : " + student.getName());

        Picasso.with(mContext).load(student.getImage()).into(holder.studentImageImgV);


        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or Delete Student?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToUpdateStudent(student.getId());

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StudentDbHelper dbHelper = new StudentDbHelper(mContext);
                        dbHelper.deleteStudentRecord(student.getId(), mContext);

                        studentArrayList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, studentArrayList.size());
                        notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return true;
            }
        });


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileActivity();
            }
        });
        holder.toggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                }else {
                }
            }
        });

    }

    private void goToProfileActivity() {
        Intent goToProfile = new Intent(mContext, ProfileActivity.class);
        mContext.startActivity(goToProfile);
    }

    private void goToUpdateStudent(long studentId) {
        Intent goToUpdate = new Intent(mContext, UpdateActivity.class);
        goToUpdate.putExtra("USER_ID", studentId);
        mContext.startActivity(goToUpdate);
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }
}

