package org.nju.demo.service;

import org.nju.demo.entity.Feature;

public interface FeatureService {

    Feature getFeatureByVersionId(int versionId);

    int addFeature(Feature feature);

}
