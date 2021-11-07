package org.nju.demo.service;

import org.nju.demo.entity.FViolation;

import java.util.List;

public interface FViolationService {

    List<FViolation> getFViolationsByVersionId(int versionId);

    List<FViolation> getTrueFViolations(int versionId);

    FViolation getFViolation(int id);

    int addFViolation(FViolation violation);

    int updateFViolation(FViolation violation);

}
