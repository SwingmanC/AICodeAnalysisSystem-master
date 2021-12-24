package org.nju.demo.utils.algorithm.impl;

import org.nju.demo.entity.IssueBasic;
import org.nju.demo.utils.algorithm.Match;

public class ExactMatch implements Match {

    @Override
    public int mark(IssueBasic issue1, IssueBasic issue2) {
        String snippet1 = issue1.getSnippet().trim();
        String snippet2 = issue2.getSnippet().trim();
        if (issue1.getPatternId().equals(issue2.getPatternId())&&
            issue1.getPriority().equals(issue2.getPriority())&&
            issue1.getFilePath().equals(issue2.getFilePath())&&
            issue1.getTargetFunction().equals(issue2.getTargetFunction())&&
            snippet1.equals(snippet2)) return 0;
        else return 1;
    }

}
