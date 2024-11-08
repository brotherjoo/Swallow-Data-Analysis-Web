package org.swallow.swallow_data_analysis.model;

import lombok.Data;
import org.swallow.swallow_data_analysis.model.Entity.SwallowTable;

@Data
public class ResponseSwallowTable {
    private String id;
    private String name;

    public ResponseSwallowTable(SwallowTable swallowTable) {
        id = swallowTable.getId();
        name = swallowTable.getName();
    }
}
