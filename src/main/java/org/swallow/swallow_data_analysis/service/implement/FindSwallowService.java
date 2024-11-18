package org.swallow.swallow_data_analysis.service.implement;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.swallow.swallow_data_analysis.model.Entity.Swallow;
import org.swallow.swallow_data_analysis.model.Entity.SwallowTable;
import org.swallow.swallow_data_analysis.model.ResponseSwallow;
import org.swallow.swallow_data_analysis.model.ResponseSwallowTable;
import org.swallow.swallow_data_analysis.model.SwallowPost;
import org.swallow.swallow_data_analysis.repository.SwallowRepository;
import org.swallow.swallow_data_analysis.repository.SwallowTableRepository;
import org.swallow.swallow_data_analysis.service.FindEntityService;
import org.swallow.swallow_data_analysis.storage.NotFoundSwallowTableException;

@Service
public class FindSwallowService implements FindEntityService {

  private final SwallowRepository swallowRepository;
  private final SwallowTableRepository swallowTableRepository;

  public FindSwallowService(SwallowRepository swallowRepository,
      SwallowTableRepository swallowTableRepository) {
    this.swallowRepository = swallowRepository;
    this.swallowTableRepository = swallowTableRepository;
  }

  @Override
  public SwallowPost findTable(String tableName) {
    Optional<SwallowTable> swallowTableOptional = swallowTableRepository.findSwallowTableByName(
        tableName);

    if (swallowTableOptional.isEmpty()) {
      throw new NotFoundSwallowTableException("Not found table");
    }

    SwallowTable swallowTable = swallowTableOptional.get();
    List<Swallow> swallows = swallowRepository.findSwallowBySwallow(swallowTable);
    List<ResponseSwallow> responseSwallows = swallows.stream().map(ResponseSwallow::new).toList();

    return new SwallowPost(new ResponseSwallowTable(swallowTable), responseSwallows);
  }

  @Override
  public List<SwallowPost> findTableAll() {
    List<SwallowPost> swallowPosts = new LinkedList<>();
    List<SwallowTable> swallowTables = swallowTableRepository.findAll();

    for (SwallowTable swallowTable : swallowTables) {
      SwallowPost swallowPost = new SwallowPost();
      List<Swallow> swallows = swallowRepository.findSwallowBySwallow(swallowTable);

      List<ResponseSwallow> responseSwallows = swallows.stream().map(ResponseSwallow::new).toList();

      ResponseSwallowTable responseSwallowTable = new ResponseSwallowTable(swallowTable);

      swallowPost.setSwallow(responseSwallows);
      swallowPost.setSwallowTable(responseSwallowTable);

      swallowPosts.add(swallowPost);
    }

    return swallowPosts;
  }
}
