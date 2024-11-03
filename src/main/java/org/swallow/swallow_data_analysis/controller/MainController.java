package org.swallow.swallow_data_analysis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.swallow.swallow_data_analysis.model.*;
import org.swallow.swallow_data_analysis.repository.SwallowRepository;
import org.swallow.swallow_data_analysis.repository.SwallowTableRepository;
import org.swallow.swallow_data_analysis.service.FileConvertSystem;
import org.swallow.swallow_data_analysis.service.FileInputSystem;
import org.swallow.swallow_data_analysis.storage.NotFoundSwallowTableException;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class MainController {

    private final FileInputSystem fileInputSystem;
    private final FileConvertSystem fileConvertSystem;
    private final SwallowTableRepository swallowTableRepository;
    private final SwallowRepository swallowRepository;

    @Autowired
    public MainController(
            FileInputSystem fileInputSystem,
            FileConvertSystem fileConvertSystem,
            SwallowTableRepository swallowTableRepository,
            SwallowRepository swallowRepository) {
        this.fileConvertSystem = fileConvertSystem;
        this.fileInputSystem = fileInputSystem;
        this.swallowRepository = swallowRepository;
        this.swallowTableRepository = swallowTableRepository;

        fileInputSystem.init();
    }

    @GetMapping("/")
    public String[] check(@Value("${spring.text.header.list}") String[] list) {
        return list;
    }

    @PostMapping("/swallow")
    public ResponseEntity<Object> swallowRegisterController(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title) throws FileNotFoundException {
        String fileRoot = fileInputSystem.store(file);
        List<Swallow> swallowList = fileConvertSystem.readLine(fileRoot);

        swallowList = fileConvertSystem.preprocessing(swallowList);

        SwallowTable swallowTable = new SwallowTable();
        swallowTable.setName(title);
        swallowTableRepository.save(swallowTable);

        swallowList.forEach(x -> x.setSwallow(swallowTable));
        swallowRepository.saveAll(swallowList);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/swallow/{tableName}")
    public Post swallowFindOneController(@PathVariable String tableName) {
         Optional<SwallowTable> swallowTableOptional = swallowTableRepository.findSwallowTableByName(tableName);

         if (!swallowTableOptional.isPresent())
             throw new NotFoundSwallowTableException("not found table");

         Post post = new Post();
         SwallowTable swallowTable = swallowTableOptional.get();
         List<Swallow> swallows = swallowRepository.findSwallowBySwallow(swallowTable);
         List<SwallowPojo> swallowPojos = swallows.stream().map(SwallowPojo::new).toList();

         post.setSwallow(swallowPojos);
         post.setSwallowTable(new SwallowTablePojo(swallowTable));

         return post;
    }

    @GetMapping("/swallow/all")
    public List<Post> swallowFindController() {
        List<Post> posts = new LinkedList<>();
        List<SwallowTable> swallowTables = swallowTableRepository.findAll();

        for (SwallowTable swallowTable: swallowTables) {
            Post post = new Post();
            List<Swallow> swallows = swallowRepository.findSwallowBySwallow(swallowTable);

            List<SwallowPojo> swallowPojos = swallows.stream().map(SwallowPojo::new).toList();

            SwallowTablePojo swallowTablePojo = new SwallowTablePojo(swallowTable);

            post.setSwallow(swallowPojos);
            post.setSwallowTable(swallowTablePojo);

            posts.add(post);
        }

        return posts;
    }
}
