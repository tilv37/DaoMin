package com.haramasu.daomin.service;

import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.dto.PostDTO;
import com.haramasu.daomin.entity.vos.ArchiveVO;
import com.haramasu.daomin.entity.vos.PostSummaryVO;
import com.haramasu.daomin.entity.vos.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    PostEntity addNewPost(PostDTO postDTO);

    PostEntity modifyPost(Integer postId,PostDTO postDTO);

    PostEntity getOneById(int postId);

    Page<PostEntity> getAllPost(int pageNo, int pageSize);

    Page<PostSummaryVO> getAllPostSummary(Pageable pageable);

    void deletePostById(Integer postId);

    Page<PostVO> loadHomePageData(int pageNo, int pageSize);

    List<ArchiveVO> findArchive();

    PostVO findByTitleEn(String titleEn);
}
