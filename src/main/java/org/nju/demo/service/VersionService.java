package org.nju.demo.service;


import org.nju.demo.entity.AVersion;

import java.util.List;

public interface VersionService {

    List<AVersion> getVersionsByProjectId(String projectId);

    List<AVersion> getVersionsByVersionName(String versionName);

    AVersion getVersion(String versionId);

    int addVersion(AVersion version);

    int updateVersion(AVersion version);
}
