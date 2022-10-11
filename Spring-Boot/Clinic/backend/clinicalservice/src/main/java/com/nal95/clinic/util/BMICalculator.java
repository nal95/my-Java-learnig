package com.nal95.clinic.util;

import com.nal95.clinic.model.ClinicalData;

import java.util.List;

public class BMICalculator {
    public static void calculateBMI(List<ClinicalData> clinicalData, ClinicalData eachEntry ){
        if(eachEntry.getComponentName().equals("hw")){
            String[] heightAndWeight = eachEntry.getComponentValue().split("/");
            if (heightAndWeight.length>1){
                float heightInMetres = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
                float bmi = Float.parseFloat(heightAndWeight[1]) /(heightInMetres * heightInMetres);
                ClinicalData bmiData = new ClinicalData();
                bmiData.setComponentName("BMI");
                bmiData.setComponentValue(Float.toString(bmi));
                clinicalData.add(bmiData);
            }
        }

    }
}
