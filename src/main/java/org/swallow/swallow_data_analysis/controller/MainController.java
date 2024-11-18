package org.swallow.swallow_data_analysis.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.FileNotFoundException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.swallow.swallow_data_analysis.model.SwallowPost;
import org.swallow.swallow_data_analysis.service.EntityResisterService;
import org.swallow.swallow_data_analysis.service.FindEntityService;

@Slf4j
@RestController
@Tag(name = "제비 API", description = "제비 데이터의 등록과 확인, 삭제를 할 수 있는 API 입니다.")
@RequestMapping("/api")
public class MainController implements MainControllerDocs {

  private final EntityResisterService entityResisterService;
  private final FindEntityService findEntityService;

  @Autowired
  public MainController(EntityResisterService entityResisterService,
      FindEntityService findEntityService) {
    this.entityResisterService = entityResisterService;
    this.findEntityService = findEntityService;
  }

  @GetMapping("/")
  public String[] check(@Value("${spring.text.header.list}") String[] list) {
    return list;
  }

  @PostMapping("/swallow")
  public ResponseEntity<Object> swallowRegisterController(
      @RequestParam("file") MultipartFile file,
      @RequestParam("title") String title) {

    try {
      entityResisterService.registerEntity(file, title);
    } catch (FileNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found file");
    }

    return ResponseEntity.status(HttpStatus.CREATED).body("ok");
  }

  @GetMapping("/swallow/{tableName}")
  public ResponseEntity<SwallowPost> swallowFindOneController(@PathVariable String tableName) {
    SwallowPost swallowPost = (SwallowPost) findEntityService.findTable(tableName);

    return ResponseEntity.status(HttpStatus.OK).body(swallowPost);
  }

  @GetMapping("/swallow/all")
  public ResponseEntity<List<SwallowPost>> swallowFindController() {
    List<SwallowPost> swallowPosts = findEntityService.findTableAll();

    return ResponseEntity.status(HttpStatus.OK).body(swallowPosts);
  }
}
