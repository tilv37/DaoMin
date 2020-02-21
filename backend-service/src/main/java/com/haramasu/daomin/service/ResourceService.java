package com.haramasu.daomin.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Path;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/21/2020
 */
public interface ResourceService {

    void init();

    String uploadFile(MultipartFile file);

    Path load(String fileName);

    Resource loadAsResource(String fileName) throws FileNotFoundException;
}
