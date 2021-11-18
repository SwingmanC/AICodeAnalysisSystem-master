package org.nju.demo.utils;

import org.nju.demo.entity.FViolation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortUtil {

    public static Map<String,Integer> countNum(List<FViolation> violationList,String t){
        Map<String,Integer> res = new HashMap<>();
        for(FViolation violation:violationList){
            if (violation.getState().equals(t)){
                if (!res.containsKey(violation.getType())) res.put(violation.getType(),1);
                else res.replace(violation.getType(),res.get(violation.getType())+1);
            }
        }
        return res;
    }

    public static Map<String,Double> computeLikelihood(List<FViolation> violationList){
        Map<String,Integer> tViolations = new HashMap<>();
        Map<String,Integer> fViolations = new HashMap<>();
        Map<String,Double> res = new HashMap<>();
        for(FViolation violation:violationList){
            if (violation.getState().equals("True")){
//                System.out.println(tViolations.containsKey(violation.getType()));
                if (!tViolations.containsKey(violation.getType())) tViolations.put(violation.getType(),1);
                else tViolations.replace(violation.getType(),tViolations.get(violation.getType())+1);
            }
            else if (violation.getState().equals("False")){
//                System.out.println(fViolations.containsKey(violation.getType()));
                if (!fViolations.containsKey(violation.getType())) fViolations.put(violation.getType(),1);
                else fViolations.replace(violation.getType(),fViolations.get(violation.getType())+1);
            }
        }
        for(Map.Entry<String, Integer> entry : tViolations.entrySet()){
            int trueNum = entry.getValue();
            int falseNum = 0;
            if (fViolations.containsKey(entry.getKey())) falseNum = fViolations.get(entry.getKey());
            double likelihood = trueNum*1.0 / (trueNum + falseNum);
            res.put(entry.getKey(),likelihood);
        }
        for(Map.Entry<String,Integer> entry : fViolations.entrySet()){
            if (tViolations.containsKey(entry.getKey())) continue;
            else res.put(entry.getKey(),0.0);
        }
        return res;
    }

    public static Double computeVariance(Double likelihood,long n){
        return likelihood*(1-likelihood)/n;
    }

}
