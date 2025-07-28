package com.viper31.tecent.mcp.server;

import com.alibaba.fastjson.JSONObject;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Viper31
 * @date 2025/7/16 16:47
 */
@SpringBootTest
@Slf4j
public class ClientStdio {

    public static void main(String[] args) {

        var stdioParams = ServerParameters.builder("C:\\jdk-21.0.5\\bin\\java.exe")
                .args("-Dfile.encoding=UTF-8",
                        "-jar",
                        "C:\\Users\\YYMY\\.m2\\res\\com\\viper31\\tecent\\mcp\\server\\tencent-add-article-mcp-server\\0.0.3-SNAPSHOT\\tencent-add-article-mcp-server-0.0.3-SNAPSHOT.jar"
                        )
                .build();

        var transport = new StdioClientTransport(stdioParams);
        var client = McpClient.sync(transport).build();

        client.initialize();

        // List and demonstrate tools
        McpSchema.ListToolsResult toolsList = client.listTools();
        System.out.println("Available Tools = " + toolsList);
        Map<String, String> result = new HashMap<>(3);
        result.put("title", "MCP入门基础知识：让AI真正“动”起来的万能接口");
        result.put("markdownContent", "# MCP入门基础知识：让AI真正“动”起来的万能接口\n\n## 一、什么是MCP？\n\nMCP，全称 **Model Context Protocol（模型上下文协议）**，是由Anthropic于2024年底提出并开源的全新开放标准。它的使命很简单：\n\n> **让AI模型像插上“USB接口”一样，轻松接入各种外部数据和工具，真正实现“知行合一”。**\n\n想象一下，过去的AI大模型就像一位博学的学者，但只能“纸上谈兵”，无法查阅最新资料、操作数据库、发邮件、甚至控制智能家居。而有了MCP，AI就像获得了“万能遥控器”，可以随时调用外部资源、执行实际操作，成为真正的“超级助手”。\n\n---\n\n## 二、MCP的核心能力\n\nMCP的设计非常巧妙，主要包括四大能力：\n\n1. **Tools（工具）**：\n   - AI可以通过MCP调用外部的“工具函数”，比如查天气、发邮件、数据库查询、文件操作等。\n2. **Resources（资源）**：\n   - 让AI访问外部数据资源，如本地文件、数据库、网页等，获取实时上下文。\n3. **Prompts（提示模板）**：\n   - 预设的交互流程或模板，帮助AI更好地理解和执行任务。\n4. **Sampling（采样）**：\n   - 支持MCP服务器主动请求AI模型进行推理，适用于复杂的多轮交互。\n\n> **一句话总结：MCP让AI不仅能“看世界”，还能“动手做事”！**\n\n---\n\n## 三、MCP的工作原理\n\nMCP采用**客户端-服务器架构**：\n- **MCP Host（宿主）**：通常是AI应用（如Claude Desktop、Cursor等），内置MCP客户端。\n- **MCP Server（服务器）**：由开发者实现，负责对接各种外部工具/数据源，并通过MCP协议暴露给AI。\n\n**连接方式**：\n- 本地可用Stdio（标准输入输出），远程可用SSE（Server-Sent Events）等方式通信。\n\n**动态发现**：\n- AI应用可自动发现本地/网络上的MCP服务器及其能力，无需手动集成，极大提升扩展性。\n\n---\n\n## 四、MCP的优势与应用场景\n\n### 1. **标准化，极简集成**\n- 过去每接一个新API/工具都要写一堆适配代码，有了MCP，只需实现一次，所有支持MCP的AI都能用。\n\n### 2. **生态繁荣，工具丰富**\n- 2025年初，已有上千个MCP服务器（如Google Drive、Slack、Git、数据库、浏览器等）可直接接入。\n\n### 3. **AI能力大升级**\n- 让AI从“只会聊天”变成“能查能改能操作”，比如：\n  - 智能助理自动整理日程、发邮件\n  - 编程助手直接操作代码仓库\n  - 企业AI自动处理工单、查询数据库\n\n### 4. **安全可控，权限灵活**\n- MCP支持细粒度权限控制和日志审计，企业可放心集成。\n\n---\n\n## 五、MCP与传统方案的对比\n\n| 方案           | 集成难度 | 扩展性 | 标准化 | 适用范围 |\n|----------------|----------|--------|--------|----------|\n| 传统API对接    | 高       | 差     | 无     | 单一工具 |\n| OpenAI插件     | 中       | 一般   | 半开放 | 平台限定 |\n| LangChain工具  | 中       | 一般   | 框架内 | 需自实现 |\n| **MCP协议**    | **低**   | **强** | **开放** | **全平台** |\n\n---\n\n## 六、MCP的实际体验与注意事项\n\n### 1. **如何快速上手？**\n- 选一个支持MCP的AI客户端（如Cursor、Claude Desktop等）\n- 在[官方MCP服务器仓库](https://github.com/modelcontextprotocol/servers)或[MCP市场](https://mcp.so/)找到你需要的MCP服务器（如数据库、文件、浏览器等）\n- 按说明配置好，AI即可自动发现并调用这些工具\n\n### 2. **开发自己的MCP服务器**\n- MCP官方提供了多语言SDK（如Python、Java），开发者可快速将自家API/工具“包装”为MCP服务器，供AI调用\n\n### 3. **安全性建议**\n- 只开放必要的工具和权限，防止AI误操作或被恶意利用\n- 配合日志、权限管理等措施，保障数据安全\n\n---\n\n## 七、MCP的未来展望\n\nMCP正成为AI工具集成的“行业标准”，未来有望：\n- 支持更多AI平台（如OpenAI、百度、阿里等）\n- 推出官方MCP服务器注册中心，便于发现和认证\n- 支持更丰富的交互模式（如流式、主动推送等）\n- 企业级安全与合规能力持续增强\n\n> **MCP的出现，让AI真正成为“能干活的超级助手”，推动AI从“会说”到“会做”，开启智能新时代！**\n\n---\n\n## 八、常见问题FAQ\n\n**Q1：MCP和RAG、Function Call有什么区别？**\n- RAG主要用于“查资料”，MCP不仅能查还能“动手”；Function Call是各家自定义，MCP是统一标准。\n\n**Q2：MCP安全吗？**\n- 只要合理配置权限、做好日志审计，MCP本身是安全的。建议企业环境下配合MCP Guardian等安全工具。\n\n**Q3：我可以用MCP做什么？**\n- 只要你能想到的AI+工具场景，几乎都能用MCP实现，比如自动化办公、智能客服、数据分析、智能家居等。\n\n---\n\n## 九、结语\n\nMCP是AI时代的“万能接口”，让AI真正走进现实、服务生活和工作。无论你是开发者、产品经理，还是AI爱好者，掌握MCP，未来可期！\n\n---\n\n> **推荐资源：**\n> - [MCP官方文档](https://modelcontextprotocol.io/)\n> - [MCP服务器仓库](https://github.com/modelcontextprotocol/servers)\n> - [MCP市场](https://mcp.so/)\n> - [MCP Guardian安全工具](https://github.com/modelcontextprotocol/guardian)\n\n---\n\n*本文由AI助手整理，欢迎交流与指正。*");
        result.put("userSummary", "本文以通俗易懂的方式介绍了MCP（Model Context Protocol）协议的基础知识、核心能力、工作原理、优势应用、与传统方案的对比、实际体验、未来展望及常见问题，帮助读者快速理解MCP为何成为AI集成领域的新标准。");

        McpSchema.CallToolResult addArticle = client.callTool(new McpSchema.CallToolRequest("addArticle",
                Map.of("functionRequest", result)));
        System.out.println("addArticle: " + JSONObject.toJSONString(addArticle));

        client.closeGracefully();
    }

}
