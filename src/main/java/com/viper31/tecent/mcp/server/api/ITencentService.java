package com.viper31.tecent.mcp.server.api;

import com.viper31.tecent.mcp.server.api.dto.AddArticleRequest;
import com.viper31.tecent.mcp.server.api.dto.AddArticleResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 腾讯云开发者社区服务接口
 * <p>
 * 该接口定义了与腾讯云开发者社区API交互的方法。
 * 使用Retrofit框架实现HTTP请求，支持文章的发布等操作。
 * 所有请求都需要包含必要的认证信息和请求头。
 *
 * @author Viper31
 * @date 2025/7/9 15:35
 */
public interface ITencentService {

    /**
     * 发布文章到腾讯云开发者社区
     * <p>
     * 该方法通过HTTP POST请求将文章发布到腾讯云开发者社区。
     * 请求包含完整的HTTP头信息，模拟浏览器行为，确保请求能够被正确处理。
     * <p>
     * 请求头说明：
     * - accept: 指定接受的响应类型
     * - content-type: 指定请求体类型为JSON
     * - origin/referer: 指定请求来源
     * - user-agent: 指定客户端信息
     * - 其他安全相关头部
     *
     * @param cookie  用户认证Cookie，用于身份验证
     * @param request 文章发布请求，包含文章内容、标题等信息
     * @return 包含发布结果的响应对象
     */
    @POST("https://cloud.tencent.com/developer/api/article/addArticle")
    Call<AddArticleResponse> addArticle(@Header("Cookie") String cookie, @Body AddArticleRequest request);

}
