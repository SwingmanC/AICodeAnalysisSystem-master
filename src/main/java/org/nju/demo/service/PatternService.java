package org.nju.demo.service;

import org.nju.demo.entity.Pattern;

public interface PatternService {

    Pattern getPatternByPatternName(String patternName);

    int updatePattern(Pattern pattern);

    long countByCategoryId(int categoryId);

}
