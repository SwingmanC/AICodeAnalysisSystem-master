package org.nju.demo.service;

import org.nju.demo.entity.PatternInfo;
import org.nju.demo.entity.PatternInfoWithBLOBs;
import org.nju.demo.entity.PatternLk;
import org.nju.demo.entity.VersionPatternRel;

import java.util.List;

public interface PatternService {

    PatternInfoWithBLOBs getPatternInfoByPatternLkId(String patternLkId);

    PatternInfoWithBLOBs getPatternInfo(String patternInfoId);

    PatternLk getPatternLk(String patternLkId);

    PatternLk getPatternByPatternName(String patternName);

    List<PatternLk> getPatternLkList();

    List<PatternLk> getFalsePatternList();

    List<PatternLk> getPatternListByKeyword(String keyword);

    int addRelation(VersionPatternRel versionPatternRel);

    int updatePatternLikelihood(PatternLk pattern);

    int updatePatternInfo(PatternInfoWithBLOBs patternInfo);

    int addPatternInfo(PatternInfoWithBLOBs patternInfo);

    int addPatternLk(PatternLk patternLk);
}
