package org.swallow.swallow_data_analysis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.swallow.swallow_data_analysis.model.SwallowPost;

public interface MainControllerDocs {

  @Operation(summary = "등록 API", description = "파일을 받아 전처리 이후 데이터 베이스에 저장")
  @Parameters(value = {
      @Parameter(name = "title", description = "저장되는 테이블의 제목"),
      @Parameter(name = "file", description = "저장되는 파일", required = true)
  })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "데이터가 저장되었습니다.", content = @Content(schema = @Schema(name = "ok")))
  })
  ResponseEntity<Object> swallowRegisterController(MultipartFile file, String title);

  @Operation(summary = "사용 API", description = "테이블의 이름을 이용해 테이블을 불러 옴")
  @Parameter(name = "tableName", description = "불러오고자 하는 테이블의 이름")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "데이터를 불러왔습니다.", content = @Content(schema = @Schema(implementation = SwallowPost.class)))
  })
  ResponseEntity<SwallowPost> swallowFindOneController(@PathVariable String tableName);

  @Operation(summary = "사용 API", description = "테이블을 모두 불러 옴")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "데이터를 불러왔습니다.", content = @Content(schema = @Schema(implementation = SwallowPost.class)))
  })
  ResponseEntity<List<SwallowPost>> swallowFindController();

}
