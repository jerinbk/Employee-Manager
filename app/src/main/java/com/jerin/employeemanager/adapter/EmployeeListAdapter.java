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
 * Created by Jerin on 04-Nov-17.
 */
public class EmployeeListAdapter extends BaseAdapter {

    private List<Employee> allEmployees;
    private Context mContext;

    public EmployeeListAdapter(Context context, List<Employee> sNote) {


        this.mContext = context;
        this.allEmployees = sNote;


    }


    @Override
    public int getCount() {
        return allEmployees.size();
    }

    @Override
    public Employee getItem(int i) {
        return allEmployees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        EmployeeViewHolder mEmployeeViewHolder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (view == null) {
            view = inflater.inflate(R.layout.employee_item, viewGroup, false);
            mEmployeeViewHolder = new EmployeeViewHolder(view);
            view.setTag(mEmployeeViewHolder);
        } else {
            mEmployeeViewHolder = (EmployeeViewHolder) view.getTag();
        }

        Employee employee = getItem(position);
        mEmployeeViewHolder.name.setText(employee.getName());
        mEmployeeViewHolder.designation.setText(employee.getDesignation());

        return view;
    }


    private class EmployeeViewHolder {
        private TextView name, designation;
        CardView rowCard;

        public EmployeeViewHolder(View item) {
            name = (TextView) item.findViewById(R.id.emp_name);
            designation = (TextView) item.findViewById(R.id.emp_designation);
            rowCard = (CardView) item.findViewById(R.id.row_card);

        }
    }


}
