package com.haramasu.daomin.util;

import com.haramasu.daomin.entity.dto.PostSummaryAndImageUrl;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.security.core.parameters.P;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/25/2020
 */
public class PostUtil {

    /**
     * 从markdown格式的文本中提取出来出文字
     * @param markdownText
     * @return
     */
    public static PostSummaryAndImageUrl getSummaryFromMarkdownText(String markdownText){
        PostSummaryAndImageUrl postSummaryAndImageUrl=new PostSummaryAndImageUrl();
        Parser parser = Parser.builder().build();
        Node node = parser.parse(markdownText);
        HtmlRenderer renderer= HtmlRenderer.builder().build();
        Document document = Jsoup.parse(renderer.render(node));
        postSummaryAndImageUrl.setSummary(document.text().substring(0,200));
        Element img = document.select("img").first();
        if(img!=null){
            String imageURL = img.absUrl("src");
            postSummaryAndImageUrl.setFirstImageUri(imageURL);
        }

        return postSummaryAndImageUrl;
    }
}
