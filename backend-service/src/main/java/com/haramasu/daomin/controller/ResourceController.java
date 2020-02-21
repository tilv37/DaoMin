package com.haramasu.daomin.controller;

import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.service.ResourceService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/21/2020
 */
@RestController
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }


    @PostMapping("/upload")
    @ResponseBody
    public ResponseDTO<String> uploadFile(@RequestParam("file") MultipartFile file) {

        String fileName = resourceService.uploadFile(file);
        return ResponseDTO.success(fileName);
    }

    @GetMapping("/download/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws FileNotFoundException {

        Resource resource = resourceService.loadAsResource(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
}
