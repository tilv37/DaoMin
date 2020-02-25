package com.haramasu.daomin;

import com.haramasu.daomin.service.PostService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Daomin3ApplicationTests {

    @Autowired
    PostService postService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addPost(){ }

    @Test
    public void markdown(){
        String text="> 部署常规流程\n" +
                "\n" +
                "1. 根据ticket查看响应的due time\n" +
                "\n" +
                "2. 发布announcement邮件\n" +
                "\n" +
                "   邮件关注时间，版本号，ticket号\n" +
                "\n" +
                "3. 登陆[微服务查看](https://daivb-release.app.corpintra.net/daivbng/#powertrain-service-app)服务的全称\n" +
                "\n" +
                "4. 登陆相应环境（如INT，PP）的Eruka搜索相关服务目前部署在哪台服务器\n" +
                "\n" +
                "5. SSH到dai2pgal服务器，查看响应配置(本次是INT下的REG服务)\n" +
                "\n" +
                "   ```\n" +
                "   cd DaiVBProd/\n" +
                "   cd deployment/\n" +
                "   su - d_int\n" +
                "   cd  d_int\n" +
                "   [d_int@dai2pgal CN ~] $ pwd \n" +
                "   /DaiVBProd/deployment/d_int\n" +
                "   [d_int@dai2pgal CN ~] $ pwd \n" +
                "   /DaiVBProd/deployment/d_int\n" +
                "   [d_int@dai2pgal CN config] $ ll *reg*\n" +
                "   [d_int@dai2pgal CN config] $ cat s_reg.properties\n" +
                "   -----------------------------\n" +
                "   查看文件中DEPLOY_SERVERS中服务器是否和eruka上的一致，如出现不一致，询问他人或者修改与eruka一致\n" +
                "   ```\n" +
                "\n" +
                "   6. 登陆[Jenkins列表](https://daivb-jira.app.corpintra.net/confluence/display/AZ/Deployment+Jobs}) 查看中国区的Jenkins\n" +
                "   7. 登陆Jenkins，选择相应的deploy，点击“ [Build with Parameters](https://sbs.t-systems.com/jenkins/view/All/job/daivb/view/deploy/job/daivb-ng-deploy-microservice-chn-dai/build?delay=0sec) ”\n" +
                "   8. 根据ticket上相关信息，填写表单\n" +
                "   9. 确认表单无误后提交";

        Parser parser = Parser.builder().build();
        Node parse = parser.parse(text);
        HtmlRenderer renderer= HtmlRenderer.builder().build();
        String render = renderer.render(parse);
        String text1 = Jsoup.parse(render).text();
        System.out.println(text1);


    }
}
