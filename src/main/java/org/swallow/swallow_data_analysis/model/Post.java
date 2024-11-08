package org.swallow.swallow_data_analysis.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Post {

    private ResponseSwallowTable swallowTable;
    private List<ResponseSwallow> swallow;

    public Post(ResponseSwallowTable swallowTable) {
        this.swallowTable = swallowTable;
    }

    @Override
    public String toString() {
        return "table name: " + swallowTable.getName();
    }
}
