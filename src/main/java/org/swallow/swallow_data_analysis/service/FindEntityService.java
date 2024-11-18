package org.swallow.swallow_data_analysis.service;

import java.util.List;
import org.swallow.swallow_data_analysis.model.SwallowPost;

public interface FindEntityService {

  Object findTable(String tableName);

  List<SwallowPost> findTableAll();
}
