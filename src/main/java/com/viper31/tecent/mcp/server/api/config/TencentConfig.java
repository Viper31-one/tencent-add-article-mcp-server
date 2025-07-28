package com.viper31.tecent.mcp.server.api.config;

import com.viper31.tecent.mcp.server.api.ITencentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Viper31
 * @date 2025/7/9 16:12
 */
@Configuration
public class TencentConfig {

    @Bean
    public ITencentService tencentService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cloud.tencent.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(ITencentService.class);
    }

}
