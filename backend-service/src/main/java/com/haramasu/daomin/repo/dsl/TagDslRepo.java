package com.haramasu.daomin.repo.dsl;

import com.haramasu.daomin.repo.TagRepo;
import com.haramasu.daomin.repo.impl.AbstractNativeQueryRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.models.auth.In;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 9/3/2019
 */
@Repository
public class TagDslRepo extends AbstractNativeQueryRepo {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private TagRepo tagRepo;

    @PersistenceContext
    EntityManager entityManager;

    public TagRepo getJpaInterface() {
        return this.tagRepo;
    }

    public Map<Integer, String> findTagsByPostIds(List<Integer> postIds) {
        String queryStr = "select ptr.post_id,te.tag_name from post_tag_relation as ptr left join tag_entity as te on te.id=ptr.tag_id where ptr.post_id in :postIds";
        Map<String,Object> params=new HashMap<>();
        params.put("postIds",postIds);
        Query nativeQuery = entityManager.createNativeQuery(queryStr);
        nativeQuery =setParams(nativeQuery,params);
        List<PostIdWithTag> resultList = nativeQuery.unwrap(NativeQuery.class).setResultTransformer(
                new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] tuple, String[] aliases) {
                        PostIdWithTag postIdWithTag=new PostIdWithTag();
                        postIdWithTag.setId((Integer) tuple[0]);
                        postIdWithTag.setTag((String)tuple[1]);
                        return postIdWithTag;
                    }

                    @Override
                    public List transformList(List collection) {
                        return collection;
                    }
                }
        ).getResultList();
        Map<Integer, List<PostIdWithTag>> collect = resultList.stream().collect(Collectors.groupingBy(PostIdWithTag::getId));
        Map<Integer, String> stringMap=new HashMap<>();
        collect.entrySet().stream().forEach(x->{
            List<PostIdWithTag> value = x.getValue();
            StringBuilder sb=new StringBuilder();
            for (PostIdWithTag postIdWithTag : value) {
                sb.append(postIdWithTag.tag+";");
            }
            stringMap.put(x.getKey(),sb.toString());
        });
        return stringMap;
    }

    class PostIdWithTag{
        private Integer id;
        private String tag;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}