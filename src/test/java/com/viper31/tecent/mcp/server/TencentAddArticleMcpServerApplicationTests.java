package com.viper31.tecent.mcp.server;

import com.viper31.tecent.mcp.server.api.ITencentService;
import com.viper31.tecent.mcp.server.api.dto.AddArticleRequest;
import com.viper31.tecent.mcp.server.api.dto.AddArticleResponse;
import com.viper31.tecent.mcp.server.mcp.TencentArticleService;
import com.viper31.tecent.mcp.server.mcp.dto.ArticleFunctionRequest;
import com.viper31.tecent.mcp.server.mcp.dto.ArticleFunctionResponse;
import com.viper31.tecent.mcp.server.mcp.utils.MarkdownToProseMirrorConverter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Slf4j
class TencentAddArticleMcpServerApplicationTests {

    @Autowired
    private ITencentService tencentService;

    @Test
    public void testAddArticle() {
        AddArticleRequest request = AddArticleRequest.builder()
                .title("发布测试")
                .content("")
                .columnIds(new ArrayList<>())
                .classifyIds(new ArrayList<>())
                .sourceType(1)
                .tagIds(Arrays.asList(18137))
                .longtailTag(new ArrayList<>())
                .plain("测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布测试文章发布")
                .pic("")
                .userSummary("发布测试")
                .banComment(0)
                .closeArticleTextLink(0)
                .zoneName("")
                .vlogIds(new ArrayList<>())
                .draftId(0L)
                .build();

        String cookies = "111_ga=GA1.2.694671329.1700707924; language=zh; _gcl_au=1.1.439815663.1747147681; ewpUid=93054339-52ca-435c-a4a1-254bea71823e; qcloud_uid=8rOcKJzpiq; qcstats_seo_keywords=%E5%93%81%E7%89%8C%E8%AF%8D-%E5%93%81%E7%89%8C%E8%AF%8D-%E7%99%BB%E5%BD%95; mfaRMId=b3352000ecd7df2f3f4f6596bcdb4325; qcloud_from=qcloud.outside.seo-1751610037815; qcommunity_identify_id=aiMLgXegP3awtlFEECkac; qcstats_ouin-100043260363=undefined; qcstats_utype-100043260363=; qcommunity_session=c03718bef8e63ef5fc9f86bb1858c937b10bf17de03f190bb3b661951f3ae0b2; qcmainCSRFToken=Sy4KztHiHlg; qcloud_visitId=ecd3a71dbc992e74fd2ab420244a48d5; uin=o100043260363; tinyid=144115391501392575; loginType=wx; intl=1; skey=6L8NsOYzII8M8vyyWPI3OYTUZm7eAiNGwYYDwPfeCdI_; lastLoginIdentity=79c5e2016db99d942234ae75ee5b7e34; refreshSession=1; appid=1368244176; moduleId=1368244176; systemTimeGap=-272; ownerUin=O100043260363G; opc_xsrf=7e976afbd9ea9bfd52eeba79cb6bf6f9%7C1752025412; saas_synced_session=100043260363%7C6L8NsOYzII8M8vyyWPI3OYTUZm7eAiNGwYYDwPfeCdI_; from=console_top_search; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22100043260363%22%2C%22first_id%22%3A%2218bfa177969f75-06b0a2f0e65d69-26031051-2073600-18bfa17796a293%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThiZmExNzc5NjlmNzUtMDZiMGEyZjBlNjVkNjktMjYwMzEwNTEtMjA3MzYwMC0xOGJmYTE3Nzk2YTI5MyIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjEwMDA0MzI2MDM2MyJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22100043260363%22%7D%2C%22%24device_id%22%3A%2218bfa177969f75-06b0a2f0e65d69-26031051-2073600-18bfa17796a293%22%7D; trafficParams=***%24%3Btimestamp%3D1752025641033%3Bfrom_type%3Dserver%3Btrack%3Dd90f65a3-92a7-4c94-a334-126508231c83%3B%24***; nick=AdoleScente";

        try {
            Call<AddArticleResponse> call = tencentService.addArticle(cookies, request);
            Response<AddArticleResponse> response = call.execute();
            if (response.isSuccessful()) {
                AddArticleResponse result = response.body();
                log.info("添加文章成功，文章ID: {}, 状态: {}", result.getArticleId(), result.getStatus());
            } else {
                log.error("添加文章失败，错误码: {}, 错误信息: {}", response.code(), response.errorBody().string());
            }
        } catch (Exception e) {
            log.error("调用添加文章接口异常", e);
        }

    }

    @Resource
    private TencentArticleService tencentArticleService;

    @Test
    public void test1() {
        ArticleFunctionRequest request = new ArticleFunctionRequest();
        request.setMarkdownContent("");
        request.setTitle("Java Lombok @Builder 跨包访问问题分析与解决");
        request.setUserSummary("本文简要分析了 Lombok @Builder 注解导致的跨包访问问题，并给出了解决方案。");
        ArticleFunctionResponse articleFunctionResponse = tencentArticleService.addArticle(request);
        System.out.println(articleFunctionResponse);
    }

    @Resource
    private MarkdownToProseMirrorConverter markdownToProseMirrorConverter;

    /**
     * 测试基本的Markdown转换功能
     * <p>
     * 验证各种Markdown语法元素是否能正确转换为ProseMirror格式
     */
    @Test
    public void testMarkdownConversion() {
        // 准备测试用的Markdown文本，包含多种Markdown语法
        String markdown = "# 标题1\n\n" +
                "这是一个**粗体**和*斜体*的测试。\n\n" +
                "## 标题2\n\n" +
                "- 无序列表项1\n" +
                "- 无序列表项2\n\n" +
                "1. 有序列表项1\n" +
                "2. 有序列表项2\n\n" +
                "> 这是一个引用\n\n" +
                "```java\n" +
                "// 这是一段Java代码\n" +
                "public class Test {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}\n" +
                "```\n\n" +
                "[这是一个链接](https://www.example.com)\n\n" +
                "---\n\n" +
                "这是最后一段。";

        // 执行转换
        String proseMirror = markdownToProseMirrorConverter.convert(markdown);

        // 验证结果
        assertNotNull(proseMirror, "转换结果不应为null");
        assertTrue(proseMirror.contains("\"type\":\"doc\""), "转换结果应包含文档类型");
        assertTrue(proseMirror.contains("\"type\":\"heading\""), "转换结果应包含标题");
        assertTrue(proseMirror.contains("\"type\":\"paragraph\""), "转换结果应包含段落");
        assertTrue(proseMirror.contains("\"type\":\"bold\""), "转换结果应包含粗体");
        assertTrue(proseMirror.contains("\"type\":\"italic\""), "转换结果应包含斜体");
        assertTrue(proseMirror.contains("\"type\":\"bullet_list\""), "转换结果应包含无序列表");
        assertTrue(proseMirror.contains("\"type\":\"ordered_list\""), "转换结果应包含有序列表");
        assertTrue(proseMirror.contains("\"type\":\"blockquote\""), "转换结果应包含引用");
        assertTrue(proseMirror.contains("\"type\":\"code_block\""), "转换结果应包含代码块");
        assertTrue(proseMirror.contains("\"type\":\"link\""), "转换结果应包含链接");
        assertTrue(proseMirror.contains("\"type\":\"horizontal_rule\""), "转换结果应包含分隔线");

        // 输出转换结果，方便查看
        System.out.println("Markdown转换为ProseMirror格式的结果：");
        System.out.println(proseMirror);
    }

}
