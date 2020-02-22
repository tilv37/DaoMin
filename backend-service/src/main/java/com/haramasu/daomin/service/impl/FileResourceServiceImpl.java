package com.haramasu.daomin.service.impl;

import cn.hutool.core.io.FileUtil;
import com.haramasu.daomin.config.StorageConfig;
import com.haramasu.daomin.exception.StorageException;
import com.haramasu.daomin.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/21/2020
 */
@Service
public class FileResourceServiceImpl implements ResourceService {

    private final Path rootPath;

    @Autowired
    public FileResourceServiceImpl(StorageConfig storageConfig) {
        this.rootPath = Paths.get(storageConfig.getLocation());
    }


    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootPath);
        }catch (Exception ex){
            throw new StorageException("文件存储服务初始化失败",ex);
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if(file.isEmpty()){
            throw new StorageException("上传的文件为空，无法存储:"+filename);
        }
        try(InputStream inputStream=file.getInputStream()){
            Files.copy(inputStream,this.rootPath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("文件存储失败:"+filename,e);
        }
        return filename;
    }

    @Override
    public Path load(String fileName) {
        return this.rootPath.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String fileName) throws FileNotFoundException {

        try {
            Path file=load(fileName);
            Resource resource=new UrlResource(file.toUri());
            if(resource.exists() && resource.isReadable()){
                return resource;
            }else {
                throw new FileNotFoundException("找不到文件:"+fileName);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("文件无法读取:"+fileName);
        }
    }

    @Override
    public byte[] loadAsByteResource(String fileName) throws FileNotFoundException {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
            return bytes;
        }catch (Exception ex){
            throw new FileNotFoundException("图片无法读取:"+fileName);
        }
    }
}
