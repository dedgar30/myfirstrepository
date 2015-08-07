/*




*/
package com.ediasnes.mysalarycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import java.text.DecimalFormat;

public class MainActivity extends Activity
{
    //Variable declaration
    Toast errmsg;
    Salary mySalary;
    Boolean appStarted;
    DecimalFormat myFormat = new DecimalFormat("###########0.00");
        //Setup the basic screen varaibles
    EditText monthlySalary;
    Spinner civilStatus;
    List<String> categories;
    ArrayAdapter<String> dataAdapter;
        //Setup the overtime variables
    EditText nightDiff;
    EditText regularOT;
    EditText regularHolidayOT;
    EditText specialHolidayOT;
        //Setup the allowance variables
    EditText lateShiftAllow;
    EditText holidayAllow;
    EditText otherNonTaxAllow;
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        appStarted=true; //Set to true to indicate that activity is created
        mySalary = new Salary(); //Initialize salary object
        
        this.setBasicScreen(null);
        appStarted=false; //Set to false after first showing of basic screen is done
    }

    public void setBasicScreen(View v){
        //If method is called via button click, retrieve stored values
        if(appStarted!=true){
            if(v.getId()==R.id.back1BTN){
                //Save value from overtime screen
                if(!nightDiff.getText().toString().isEmpty()){
                    mySalary.nightDiff = Double.parseDouble(nightDiff.getText().toString());
                } else mySalary.nightDiff = 0;
                if(!regularOT.getText().toString().isEmpty()){
                    mySalary.regularOT = Double.parseDouble(regularOT.getText().toString());
                } else mySalary.regularOT = 0;
                if(!regularHolidayOT.getText().toString().isEmpty()){
                    mySalary.regularHolidayOT = Double.parseDouble(regularHolidayOT.getText().toString());
                } else mySalary.regularHolidayOT = 0;
                if(!specialHolidayOT.getText().toString().isEmpty()){
                    mySalary.specialHolidayOT = Double.parseDouble(specialHolidayOT.getText().toString());
                } else mySalary.specialHolidayOT = 0;
            }
        }
        
        //Set up the basic screen
        setContentView(R.layout.main);
        //Set up monthly salary view
        monthlySalary = (EditText) findViewById(R.id.monthlySalaryEDT);
        
        //Set up choices for the spinner
            //Civil status spinner
        civilStatus = (Spinner) findViewById(R.id.civilStatusSPN);
            //Create arraylist for the civil status items
        categories = new ArrayList<String>();
        categories.add("S/ME");
        categories.add("ME1/S1");
        categories.add("ME2/S2");
        categories.add("ME3/S3");
        categories.add("ME4/S4");
            //Create an array adapter for use in civil status spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
            //Creates dropdown layout for the spinner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //attach the adapter to the spinner
        civilStatus.setAdapter(dataAdapter);
        
        //If method is called via button click, retrieve stored values
        if(appStarted!=true){
            if(v.getId()==R.id.back1BTN){
                //Restore value on basic screen
                monthlySalary.setText(String.valueOf(mySalary.monthlySalary));
                civilStatus.setSelection(dataAdapter.getPosition(mySalary.civilStatus));
            }
        }
    }
    
    public void setOTScreen(View v) {
        //This validation checks if this method is called on basic screen
        if(v.getId()==R.id.next1BTN){
            /*This will check if monthly salary is entered as this is required
            and if its empty, it exits method with error message*/
            if(monthlySalary.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter you monthly salary.", Toast.LENGTH_SHORT).show();
                return;
            }
            //This stores the values of monthly salary and civil status on Salary object
            mySalary.monthlySalary = Double.parseDouble(monthlySalary.getText().toString());
            mySalary.civilStatus = civilStatus.getSelectedItem().toString();
        }
        
        //Save values on allowance screen
        if(v.getId()==R.id.back2BTN){
            if(!lateShiftAllow.getText().toString().isEmpty()){
                mySalary.lateShiftAllow = Double.parseDouble(lateShiftAllow.getText().toString());
            } else mySalary.lateShiftAllow = 0;
            if(!holidayAllow.getText().toString().isEmpty()){
                mySalary.holidayAllow = Double.parseDouble(holidayAllow.getText().toString());
            } else mySalary.holidayAllow = 0;
            if(!otherNonTaxAllow.getText().toString().isEmpty()){
                mySalary.otherNonTaxAllow = Double.parseDouble(otherNonTaxAllow.getText().toString());
            } else mySalary.otherNonTaxAllow = 0;
        }
        
        //Setup the overtime screen
        setContentView(R.layout.otscreen);
        //This initialize view for overtime variables
        nightDiff = (EditText) findViewById(R.id.nightDiffEDT);
        regularOT = (EditText) findViewById(R.id.regOTEDT);
        regularHolidayOT = (EditText) findViewById(R.id.regularHolOTEDT);
        specialHolidayOT = (EditText) findViewById(R.id.specialHolOTEDT);
        
        //Restore values once called back on overtime screen if saved value is not 0
        if(mySalary.nightDiff!=0){
            nightDiff.setText(String.valueOf(mySalary.nightDiff));
        }
        if(mySalary.regularOT!=0){
            regularOT.setText(String.valueOf(mySalary.regularOT));
        }
        if(mySalary.regularHolidayOT!=0){
            regularHolidayOT.setText(String.valueOf(mySalary.regularHolidayOT));
        }
        if(mySalary.specialHolidayOT!=0){
            specialHolidayOT.setText(String.valueOf(mySalary.specialHolidayOT));
        }
        
        
        
    }
    
    public void setAllowanceScreen(View v){
        //Saves values from overtime screen when going to allowance screen
        if(v.getId()==R.id.next2BTN){
            if(!nightDiff.getText().toString().isEmpty()){
                mySalary.nightDiff = Double.parseDouble(nightDiff.getText().toString());
            } else mySalary.nightDiff = 0;
            if(!regularOT.getText().toString().isEmpty()){
                mySalary.regularOT = Double.parseDouble(regularOT.getText().toString());
            } else mySalary.regularOT = 0;
            if(!regularHolidayOT.getText().toString().isEmpty()){
                mySalary.regularHolidayOT = Double.parseDouble(regularHolidayOT.getText().toString());
            } else mySalary.regularHolidayOT = 0;
            if(!specialHolidayOT.getText().toString().isEmpty()){
                mySalary.specialHolidayOT = Double.parseDouble(specialHolidayOT.getText().toString());
            } else mySalary.specialHolidayOT = 0;
        }
        
        //Setup the allowance screen
        setContentView(R.layout.allowancescreen);
        //This initialize view for allowance screen
        lateShiftAllow = (EditText) findViewById(R.id.lateShiftEDT);
        holidayAllow = (EditText) findViewById(R.id.holidayAllowanceEDT);
        otherNonTaxAllow = (EditText) findViewById(R.id.otherAllowanceEDT);
        
        //Restore values once called back on allowance screen if saved value is not 0
        if(mySalary.lateShiftAllow!=0){
            lateShiftAllow.setText(String.valueOf(mySalary.lateShiftAllow));
        }
        if(mySalary.holidayAllow!=0){
            holidayAllow.setText(String.valueOf(mySalary.holidayAllow));
        }
        if(mySalary.otherNonTaxAllow!=0){
            otherNonTaxAllow.setText(String.valueOf(mySalary.otherNonTaxAllow));
        }
        
        
    }
    
    public void calculateSalary(View v){
        //Stores values from allowance screen
        if(!lateShiftAllow.getText().toString().isEmpty()){
            mySalary.lateShiftAllow = Double.parseDouble(lateShiftAllow.getText().toString());
        } else mySalary.lateShiftAllow = 0;
        if(!holidayAllow.getText().toString().isEmpty()){
            mySalary.holidayAllow = Double.parseDouble(holidayAllow.getText().toString());
        } else mySalary.holidayAllow = 0;
        if(!otherNonTaxAllow.getText().toString().isEmpty()){
            mySalary.otherNonTaxAllow = Double.parseDouble(otherNonTaxAllow.getText().toString());
        } else mySalary.otherNonTaxAllow = 0;
        
        //Display salary summary
        setContentView(R.layout.salarysummary);
        String space = "    -";
        mySalary.resetPrivateVariables();
            //Display basic salary
        TextView basicSalary = (TextView) findViewById(R.id.basicSalaryTXT);
        basicSalary.setText(String.valueOf(myFormat.format(mySalary.monthlySalary)) + 
                space + "Basic salary");
            //Display SSS
        TextView sss = (TextView) findViewById(R.id.SSSTXT);
        sss.setText(space + String.valueOf(myFormat.format(mySalary.getSSS())) + space + "SSS");
            //Display philHealth
        TextView philHealth = (TextView) findViewById(R.id.philhealthTXT);
        philHealth.setText(space + String.valueOf(myFormat.format(mySalary.getPhilHealth())) + 
                space + "PhilHealth");
            //Display pag-ibig
        TextView pagibig = (TextView) findViewById(R.id.pagibigTXT);
        pagibig.setText(space + String.valueOf(myFormat.format(mySalary.getPagIbig())) + 
                space + "Pag-ibig");
            //Display salary less deduction
        TextView salaryLessDeduction = (TextView) findViewById(R.id.salaryLessDeductionTXT);
        salaryLessDeduction.setText(String.valueOf(myFormat.format(mySalary.getSalaryLessDeductions())) + 
                space + "Salary less deduction");
            //Display other taxable income
        TextView otherTaxIncome = (TextView) findViewById(R.id.otherTaxIncomeTXT);
        otherTaxIncome.setText(space + String.valueOf(myFormat.format(mySalary.getAdditionalTaxableIncome())) + 
                space + "Other taxable income");
            //Display Total taxable income
        TextView totalTaxIncome = (TextView) findViewById(R.id.totalTaxIncomeTXT);
        totalTaxIncome.setText(String.valueOf(myFormat.format(mySalary.getTotalTaxableIncome())) + 
                space + "Total taxable income");
            //Display withholding tax
        TextView withholdingTax = (TextView) findViewById(R.id.withHoldingTaxTXT);
        withholdingTax.setText(space + String.valueOf(myFormat.format(mySalary.getWithHoldingTax())) + 
                space + "Withholding tax");
            //Display salary less tax
        TextView salaryLessTax = (TextView) findViewById(R.id.salaryLessTaxTXT);
        salaryLessTax.setText(String.valueOf(myFormat.format(mySalary.getSalaryLessTax())) + 
                space + "Salary less tax");
            //Display other nontax income
        TextView nonTaxIncome = (TextView) findViewById(R.id.nonTaxIncomeTXT);
        nonTaxIncome.setText(space + String.valueOf(myFormat.format(mySalary.getNonTaxIncome())) + 
                space + "Non-tax income");
            //Display net income
        TextView netIncome = (TextView) findViewById(R.id.netIncomeTXT);
        netIncome.setText(String.valueOf(myFormat.format(mySalary.getNetIncome())) + 
                space + "Net income");
        
        
    }
    
    public void restart(View v){
        
    }
}
