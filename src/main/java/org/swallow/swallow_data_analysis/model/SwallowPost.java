package org.swallow.swallow_data_analysis.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SwallowPost extends Post {

  private ResponseSwallowTable swallowTable;
  private List<ResponseSwallow> swallow;

  public SwallowPost(ResponseSwallowTable swallowTable) {
    this.swallowTable = swallowTable;
  }

  public SwallowPost(ResponseSwallowTable swallowTable, List<ResponseSwallow> swallow) {
    this.swallowTable = swallowTable;
    this.swallow = swallow;
  }

  @Override
  public String toString() {
    return "table name: " + swallowTable.getName();
  }
}
