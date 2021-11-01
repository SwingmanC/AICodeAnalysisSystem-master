package org.nju.demo.utils.algorithm;

import org.nju.demo.entity.FViolation;

import java.util.List;

public interface Match {

    List<FViolation> mark1(List<FViolation> v1, List<FViolation> v2);

}
