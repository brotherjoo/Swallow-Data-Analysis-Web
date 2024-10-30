package org.swallow.swallow_data_analysis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swallow.swallow_data_analysis.formula.Formlua;
import org.swallow.swallow_data_analysis.model.Swallow;

import java.util.List;

@Service
public class SwallowFileConvertService implements FileConvertSystem {

    private final Formlua formlua;

    @Autowired
    public SwallowFileConvertService(Formlua formlua) {
        this.formlua = formlua;
    }

    @Override
    public List<Swallow> ReadFile(String filename) {
        //구현
        return List.of();
    }

    @Override
    public void MakeCsvFile(String filename) {

    }
}
