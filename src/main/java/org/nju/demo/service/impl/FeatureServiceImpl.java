package org.nju.demo.service.impl;

import org.nju.demo.dao.FeatureMapper;
import org.nju.demo.entity.Feature;
import org.nju.demo.entity.FeatureExample;
import org.nju.demo.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureMapper featureMapper;

    @Override
    public Feature getFeatureByVersionId(int versionId) {

        FeatureExample featureExample = new FeatureExample();
        FeatureExample.Criteria criteria = featureExample.createCriteria();

        criteria.andVersionIdEqualTo(versionId);

        List<Feature> featureList = featureMapper.selectByExample(featureExample);
        if (featureList.size() == 0) return null;
        else return featureList.get(0);
    }

    @Override
    public int addFeature(Feature feature) {
        return featureMapper.insert(feature);
    }
}
