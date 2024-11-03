package org.swallow.swallow_data_analysis.model;

import lombok.Data;

@Data
public class SwallowTablePojo {
    private String id;
    private String name;

    public SwallowTablePojo(SwallowTable swallowTable) {
        id = swallowTable.getId();
        name = swallowTable.getName();
    }
}
