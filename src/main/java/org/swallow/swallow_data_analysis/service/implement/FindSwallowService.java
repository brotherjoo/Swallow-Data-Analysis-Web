package org.swallow.swallow_data_analysis.service.implement;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.swallow.swallow_data_analysis.model.Entity.Swallow;
import org.swallow.swallow_data_analysis.model.Entity.SwallowTable;
import org.swallow.swallow_data_analysis.model.Post;
import org.swallow.swallow_data_analysis.model.ResponseSwallow;
import org.swallow.swallow_data_analysis.model.ResponseSwallowTable;
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
  public Post findTable(String tableName) {
    Optional<SwallowTable> swallowTableOptional = swallowTableRepository.findSwallowTableByName(
        tableName);

    if (swallowTableOptional.isEmpty()) {
      throw new NotFoundSwallowTableException("Not found table");
    }

    SwallowTable swallowTable = swallowTableOptional.get();
    List<Swallow> swallows = swallowRepository.findSwallowBySwallow(swallowTable);
    List<ResponseSwallow> responseSwallows = swallows.stream().map(ResponseSwallow::new).toList();

    return new Post(new ResponseSwallowTable(swallowTable), responseSwallows);
  }

  @Override
  public List<Post> findTableAll() {
    List<Post> posts = new LinkedList<>();
    List<SwallowTable> swallowTables = swallowTableRepository.findAll();

    for (SwallowTable swallowTable : swallowTables) {
      Post post = new Post();
      List<Swallow> swallows = swallowRepository.findSwallowBySwallow(swallowTable);

      List<ResponseSwallow> responseSwallows = swallows.stream().map(ResponseSwallow::new).toList();

      ResponseSwallowTable responseSwallowTable = new ResponseSwallowTable(swallowTable);

      post.setSwallow(responseSwallows);
      post.setSwallowTable(responseSwallowTable);

      posts.add(post);
    }

    return posts;
  }
}
