package com.jerin.employeemanager.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jerin.employeemanager.R;
import com.jerin.employeemanager.models.Employee;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerin on 12-Aug-16.
 */
public class EmployeeListAdapter extends BaseAdapter {

    private List<Employee> allNotes;

    public Context context;
    ArrayList<Employee> searchList;
    private Context mContext;



    public EmployeeListAdapter(Context context, List<Employee> sNote) {


        this.mContext = context;
        this.allNotes = sNote;
        searchList = new ArrayList<>();
        searchList.addAll(allNotes);

    }


    @Override
    public int getCount() {
        return allNotes.size();
    }

    @Override
    public Employee getItem(int i) {
        return allNotes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        NotesViewHolder mNotesViewHolder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (view == null) {
            view = inflater.inflate(R.layout.employee_item, viewGroup, false);
            mNotesViewHolder = new NotesViewHolder(view);
            view.setTag(mNotesViewHolder);
        } else {
            mNotesViewHolder = (NotesViewHolder) view.getTag();
        }

        Employee employee = getItem(position);
       // mNotesViewHolder.description.setText(employee.getDescription());
        mNotesViewHolder.name.setText(employee.getName());
        mNotesViewHolder.designation.setText(employee.getDesignation());

        return view;
    }


    private class NotesViewHolder {
        private TextView name, designation;
        CardView rowCard;

        public NotesViewHolder(View item) {
            name = (TextView) item.findViewById(R.id.emp_name);
            designation = (TextView) item.findViewById(R.id.emp_designation);
            rowCard = (CardView) item.findViewById(R.id.row_card);

        }
    }


}
