/*




*/
package com.ediasnes.mysalarycalculator;

import java.util.ArrayList;
import java.util.List;

public class Salary{
    //Variable declarations
    double monthlySalary;
    String civilStatus;
    double nightDiff;
    double regularOT;
    double regularHolidayOT;
    double specialHolidayOT;
    double lateShiftAllow;
    double holidayAllow;
    double otherNonTaxAllow;
    
    private double deductions;
    private double SalaryLessDeductions;
    private double AdditionalTaxableIncome;
    private double TotalTaxableIncome;
    private double WithHoldingTax;
    private double SalaryLessTax;
    private double NonTaxIncome;
    
    private final double[] Zrange = {0,833,2500,5833,11667,20833,41667};
    private final double[] SMErange = {4167,5000,6667,10000,15833,25000,45833};
    private final double[] ME1S1range = {6250,7083,8750,12083,17917,27083,47917};
    private final double[] ME2S2range = {8333,9167,10833,14167,20000,29167,50000};
    private final double[] ME3S3range = {10417,11250,12917,16250,22083,31250,52083};
    private final double[] ME4S4range = {12500,13333,15000,18333,24167,33333,54167};
    private final double[] baseTax = {0.00,41.67,208.33,708.33,1875.00,4166.67,10416.67};
    private final double[] taxPercentage = {0.05,0.10,0.15,0.20,0.25,0.30,0.32};
    private double range;
    private double base;
    private double percentage;
    
    public Salary(){
        this.resetVariables();
        
    }
    
    private void resetVariables(){
        monthlySalary = 0;
        civilStatus = "";
        nightDiff = 0;
        regularOT = 0;
        regularHolidayOT = 0;
        specialHolidayOT = 0;
        lateShiftAllow = 0;
        holidayAllow = 0;
        otherNonTaxAllow = 0;
        
        deductions = 0;
        SalaryLessDeductions = 0;
        AdditionalTaxableIncome = 0;
        TotalTaxableIncome = 0;
    }
    
    public double getSSS(){
        double SSS = 0;
        if (monthlySalary >= 15750.00){
            SSS = 581.30;
        } else if (monthlySalary < 15750.00){
            SSS = 36.30;
            int x=0;
            for (double salary1 = 1249.99 ; salary1 < this.monthlySalary ; salary1 += 500){
                if (x < 2) {
                    SSS += 18.20;
                    x++;
                } else {
                    SSS += 18.10;
                    x = 0;
                }
            }
        } else if (monthlySalary < 1000){
            SSS = 0.00;
        }
        deductions += SSS;
        return SSS;
    }
    
    public double getPhilHealth(){
        double philHealth = 0;
        if (this.monthlySalary >= 35000.00){
            philHealth = 437.50;
        } else if (this.monthlySalary < 35000.00){
            philHealth = 112.50;
            for (double salary1 = 9999.99 ; salary1 < this.monthlySalary ; salary1 +=1000.00){
		philHealth += 12.50;
            }
        } else if (this.monthlySalary < 9000.00){
            philHealth = 100.00;
        }
        
        deductions += philHealth;
        return philHealth;
    }
    
    public double getPagIbig(){
        double pagIbig = 100;
        deductions += pagIbig;
        return pagIbig;
    }
    
    public double getDeductions(){
        return deductions;
    }
    
    public double getSalaryLessDeductions(){
        SalaryLessDeductions = monthlySalary-deductions;
        return SalaryLessDeductions;
    }
    
    public double getAdditionalTaxableIncome(){
        double perDayPay = monthlySalary/21.125;
        
        //From nightDiff
        double nightDiffAmount = perDayPay * (nightDiff/8) * 0.3;
        
        //From regular overtime
        double regOvertime = perDayPay * (regularOT/8) * 1.5;
        
        //From regular holiday overtime
        double holOvertime = perDayPay * (regularHolidayOT/8) * 2.6;
        
        //From special holiday overtime
        double specialHolOvertime = perDayPay * (specialHolidayOT/8) * 2;
        
        //From night shift allowance
        double taxLateShiftAllow = (lateShiftAllow*187)/300;
        
        AdditionalTaxableIncome = nightDiffAmount+regOvertime+holOvertime
                +specialHolOvertime+taxLateShiftAllow;
        
        return AdditionalTaxableIncome;
    }
    
    public double getTotalTaxableIncome(){
        TotalTaxableIncome = SalaryLessDeductions+AdditionalTaxableIncome;
        return TotalTaxableIncome;
    }
    
    public double getWithHoldingTax(){
        this.getTaxTableData();
        WithHoldingTax = ((TotalTaxableIncome-range)*percentage)+base;
        return WithHoldingTax;
    }

    private void getTaxTableData() {
        
        if("S/ME".equals(civilStatus)){
            for(int x=0;x<7;x++){
                if(TotalTaxableIncome>=SMErange[x]){
                    range = SMErange[x];
                    base = baseTax[x];
                    percentage = taxPercentage[x];
                }else{
                    break;
                }
            }
        } else if("ME1/S1".equals(civilStatus)){
            for(int x=0;x<7;x++){
                if(TotalTaxableIncome>=ME1S1range[x]){
                    range = SMErange[x];
                    base = baseTax[x];
                    percentage = taxPercentage[x];
                }else{
                    break;
                }
            }
        } else if("ME2/S2".equals(civilStatus)){
            for(int x=0;x<7;x++){
                if(TotalTaxableIncome>=ME2S2range[x]){
                    range = SMErange[x];
                    base = baseTax[x];
                    percentage = taxPercentage[x];
                }else{
                    break;
                }
            }
        } else if("ME3/S3".equals(civilStatus)){
            for(int x=0;x<7;x++){
                if(TotalTaxableIncome>=ME3S3range[x]){
                    range = SMErange[x];
                    base = baseTax[x];
                    percentage = taxPercentage[x];
                }else{
                    break;
                }
            }
        } else if("ME4/S4".equals(civilStatus)){
            for(int x=0;x<7;x++){
                if(TotalTaxableIncome>=ME4S4range[x]){
                    range = SMErange[x];
                    base = baseTax[x];
                    percentage = taxPercentage[x];
                }else{
                    break;
                }
            }
        } else if("Z".equals(civilStatus)){
            for(int x=0;x<7;x++){
                if(TotalTaxableIncome>=Zrange[x]){
                    range = SMErange[x];
                    base = baseTax[x];
                    percentage = taxPercentage[x];
                }else{
                    break;
                }
            }
        }
        
    }
    
    public double getSalaryLessTax(){
        SalaryLessTax = TotalTaxableIncome-WithHoldingTax;
        return SalaryLessTax;
    }
    
    public double getNonTaxIncome(){
        //From night shift allowance
        double nonTaxLateShiftAllow = (lateShiftAllow*113)/300;
        
        NonTaxIncome = nonTaxLateShiftAllow+otherNonTaxAllow; 
        return NonTaxIncome;
    }
    
    public double getNetIncome(){
        double NetIncome = SalaryLessTax+NonTaxIncome;
        return NetIncome;
    }
        
}