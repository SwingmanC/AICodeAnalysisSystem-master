package org.nju.demo.utils;

import org.nju.demo.entity.FViolation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortUtil {

    public static Map<String,Double> computeLikelihood(List<FViolation> violationList){
        Map<String,Integer> tViolations = new HashMap<>();
        Map<String,Integer> fViolations = new HashMap<>();
        Map<String,Double> res = new HashMap<>();
        for(FViolation violation:violationList){
            if (violation.getState().equals("true")){
//                System.out.println(tViolations.containsKey(violation.getType()));
                if (!tViolations.containsKey(violation.getType())) tViolations.put(violation.getType(),1);
                else tViolations.replace(violation.getType(),tViolations.get(violation.getType())+1);
            }
            else{
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
        return res;
    }

    public static Double computeVariance(Double likelihood,long n){
        return likelihood*(1-likelihood)/n;
    }

}
