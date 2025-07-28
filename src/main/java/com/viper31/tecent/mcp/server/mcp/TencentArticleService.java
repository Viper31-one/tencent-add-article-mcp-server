package com.viper31.tecent.mcp.server.mcp;

import com.alibaba.fastjson.JSON;
import com.viper31.tecent.mcp.server.api.ITencentService;
import com.viper31.tecent.mcp.server.api.dto.AddArticleRequest;
import com.viper31.tecent.mcp.server.api.dto.AddArticleResponse;
import com.viper31.tecent.mcp.server.mcp.config.TencentApiProperties;
import com.viper31.tecent.mcp.server.mcp.dto.ArticleFunctionRequest;
import com.viper31.tecent.mcp.server.mcp.dto.ArticleFunctionResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 腾讯云开发者社区文章服务
 * <p>
 * 该服务类负责处理与腾讯云开发者社区文章相关的业务逻辑，
 * 包括文章的发布、更新等操作。作为领域服务层的一部分，
 * 它通过端口适配器模式与基础设施层进行交互。
 *
 * @author Viper31
 * @date 2025/7/17 09:26
 */
@Service
@Slf4j
@EnableConfigurationProperties(TencentApiProperties.class)
public class TencentArticleService {

    @Resource
    private ITencentService tencentService;

    @Resource
    private TencentApiProperties tencentApiProperties;

    @Tool(description = "发布文章到腾讯云开发者社区")
    public ArticleFunctionResponse addArticle(ArticleFunctionRequest functionRequest) {
        try {
            log.info("接收到的参数: {}", functionRequest.toString());

            AddArticleRequest request = new AddArticleRequest();
            request.setTitle(functionRequest.getTitle());
            request.setPlain(functionRequest.getMarkdownContent());
            request.setContent(request.getContent());
            request.setColumnIds(new ArrayList<>());
            request.setClassifyIds(Arrays.asList(2));
            request.setSourceType(1);
            request.setTagIds(Arrays.asList(18126));
            request.setLongtailTag(Arrays.asList("mcp"));
            request.setPic("");
            request.setUserSummary("发布测试");
            request.setBanComment(0);
            request.setCloseArticleTextLink(0);
            request.setZoneName("");
            request.setVlogIds(new ArrayList<>());
            request.setDraftId(0L);
            log.info("腾讯云开发者社区发帖参数：{}", JSON.toJSONString(request));
            String cookies = tencentApiProperties.getCookie();
            Call<AddArticleResponse> call = tencentService.addArticle(cookies, request);
            Response<AddArticleResponse> response = call.execute();
            // 构建返回对象
            ArticleFunctionResponse articleFunctionResponse = new ArticleFunctionResponse();
            articleFunctionResponse.setStatus(-1);
            articleFunctionResponse.setArticleId(null);
            articleFunctionResponse.setUrl(null);
            if (response.isSuccessful()) {
                // 处理成功响应
                AddArticleResponse articleResponseDTO = response.body();
                if (null == articleResponseDTO) {
                    return null;
                }
                log.info("腾讯云开发者社区发布文章成功: {}", JSON.toJSONString(articleResponseDTO));

                articleFunctionResponse.setStatus(articleResponseDTO.getStatus());
                articleFunctionResponse.setArticleId(articleResponseDTO.getArticleId());
                articleFunctionResponse.setUrl("https://cloud.tencent.com/developer/article/" + articleResponseDTO.getArticleId());
                log.info("添加文章成功，文章ID: {}, 状态: {}", articleResponseDTO.getArticleId(), articleResponseDTO.getStatus());
                return articleFunctionResponse;
            }
            String msg = response.errorBody().string();
            log.error("添加文章失败，错误码: {}, 错误信息: {}", response.code(), msg);
            articleFunctionResponse.setMsg(msg);
            return articleFunctionResponse;
        } catch (Exception e) {
            log.error("调用添加文章接口异常", e);
        }
        return null;
    }

}
