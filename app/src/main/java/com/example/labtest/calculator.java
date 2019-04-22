package com.example.labtest;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.DBmodel;
import sqliteDB.DBCalculator;

public class calculator extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText editSubjectCode, edtSubjectName, edtcreditHour;
    TextView textViewGrade;
    Spinner spinnerGrade;
    String username;
    DBmodel dbmodel;
    DBCalculator calculator;
    ArrayList<DBmodel> dBmodels;
    Double subjectcredit;
    String subjectgrade;
    public  Double totalCredit=0.00;
    public Double gradepointer=0.00;
    public  Double subjectpoint=0.00;
    public  Double totalpointer=0.00;
    public Double cgpa=0.00;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        sharedPreferences=getApplicationContext().getSharedPreferences("Calculator",0);
        username=sharedPreferences.getString("username",null);

        Toast.makeText(getApplicationContext(),"Welcome "+username,Toast.LENGTH_SHORT).show();
        editSubjectCode=findViewById(R.id.editSubjectCode);
        edtSubjectName=findViewById(R.id.edtSubjectName);
        edtcreditHour=findViewById(R.id.edtcreditHour);
        textViewGrade=findViewById(R.id.textViewGrade);
        spinnerGrade=findViewById(R.id.spinnerGrade);
        calculator=new DBCalculator(getApplicationContext());

    }

    public void fnSave(View vw)
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Do you want to add This?");
        alertBuilder.setPositiveButton(getString(R.string.btnPositive),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        totalpointer = 0.00;
                        totalCredit = 0.00;
                        dbmodel = new DBmodel();
                        dbmodel.setStrSubjectCode(editSubjectCode.getText().toString());
                        dbmodel.setStrSubjectName(edtSubjectName.getText().toString());
                        dbmodel.setStrCreditHour(Integer.parseInt(edtcreditHour.getText().toString()));
                        dbmodel.setStrGrade(String.valueOf(spinnerGrade.getSelectedItem()));

                        calculator.fnInsertCalculator(dbmodel);
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                        dBmodels = (ArrayList<DBmodel>) calculator.fnGetAllCalculator();
                        for (DBmodel sub : dBmodels) {
                            subjectcredit = Double.valueOf(sub.getStrCreditHour());
                            subjectgrade = sub.getStrGrade();
                            totalCredit += subjectcredit;

                            if (subjectgrade.equalsIgnoreCase("A")) {
                                gradepointer = 4.00;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("A-"))
                            {
                                gradepointer = 3.70;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("B+"))
                            {
                                gradepointer = 3.30;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("B"))
                            {
                                gradepointer = 3.00;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("B-"))
                            {
                                gradepointer = 2.70;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("C+"))
                            {
                                gradepointer = 2.30;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("C"))
                            {
                                gradepointer = 2.00;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("C-"))
                            {
                                gradepointer = 1.70;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("D+"))
                            {
                                gradepointer = 1.30;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("D"))
                            {
                                gradepointer = 1.00;
                                subjectpoint = subjectcredit * gradepointer;
                            } else if (subjectgrade.equalsIgnoreCase("E"))
                            {
                                gradepointer = 0.00;
                                subjectpoint = subjectcredit * gradepointer;
                            }

                            totalpointer += subjectpoint;
                        }

                        cgpa = totalpointer / totalCredit;
                        textViewGrade.setText("Your Current CGPA : " + cgpa);


                    }
                }
        );
                alertBuilder.setNegativeButton(getString(R.string.btnNegative),
                        new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

                alertBuilder.show();

    }


}


