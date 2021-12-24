package org.nju.demo.service;

import org.nju.demo.entity.PatternInfo;
import org.nju.demo.entity.PatternInfoWithBLOBs;
import org.nju.demo.entity.PatternLk;

import java.util.List;

public interface PatternService {

    PatternInfo getPatternInfoByPatternName(String patternName);

    PatternInfoWithBLOBs getPatternInfo(String patternId);

    PatternLk getPatternLikelihood(String patternId);

    PatternInfoWithBLOBs getPattern(int id);

    PatternLk getPatternLk(int id);

    List<PatternLk> getPatternLkList();

    List<PatternInfo> getPatternInfoList();

    int updatePatternLikelihood(PatternLk pattern);

    int addPatternInfo(PatternInfoWithBLOBs patternInfo);

    int addPatternLk(PatternLk patternLk);
}
