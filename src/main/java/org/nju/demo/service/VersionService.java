package org.nju.demo.service;


import org.nju.demo.entity.AVersion;

import java.util.List;

public interface VersionService {

    List<AVersion> getVersionsByProjectId(int projectId);

    AVersion getVersion(int id);

    int addVersion(AVersion version);

    int updateVersion(AVersion version);

    int deleteVersionById(int id);
}
