package org.nju.demo.utils;

import org.nju.demo.entity.IssueBasic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortUtil {

    public static Map<String,Integer> countNum(List<IssueBasic> issueList,String t){
        Map<String,Integer> res = new HashMap<>();
        for(IssueBasic issue:issueList){
            if (issue.getState().equals(t)){
                if (!res.containsKey(issue.getPatternId())) res.put(issue.getPatternId(),1);
                else res.replace(issue.getPatternId(),res.get(issue.getPatternId())+1);
            }
        }
        return res;
    }

}
