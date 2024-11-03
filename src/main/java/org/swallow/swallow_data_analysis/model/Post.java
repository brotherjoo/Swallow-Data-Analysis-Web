package org.swallow.swallow_data_analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Post {

    private SwallowTablePojo swallowTable;
    private List<SwallowPojo> swallow;

    public Post(SwallowTablePojo swallowTable) {
        this.swallowTable = swallowTable;
    }

    @Override
    public String toString() {
        return "table name: " + swallowTable.getName();
    }
}
