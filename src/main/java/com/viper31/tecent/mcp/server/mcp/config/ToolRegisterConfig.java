package com.viper31.tecent.mcp.server.mcp.config;

import com.viper31.tecent.mcp.server.mcp.TencentArticleService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Viper31
 * @date 2025/7/17 10:42
 */
@Configuration
public class ToolRegisterConfig {

    @Bean
    public ToolCallbackProvider TencentArticleServiceTools(TencentArticleService tencentArticleService) {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(tencentArticleService)
                .build();
    }

}
