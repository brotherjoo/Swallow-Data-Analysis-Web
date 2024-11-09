package org.swallow.swallow_data_analysis.service;

import java.util.List;
import org.swallow.swallow_data_analysis.model.Post;

public interface FindEntityService {

  Object findTable(String tableName);

  List<Post> findTableAll();
}
