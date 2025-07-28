# 腾讯云开发者社区 MCP 服务器

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-green.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📖 项目简介

腾讯云开发者社区 MCP (Model Context Protocol) 服务器是一个基于 Spring Boot 的 MCP 服务实现，专门用于与腾讯云开发者社区进行交互。该项目提供了发布文章到腾讯云开发者社区的功能，支持 Markdown 格式的内容转换和自动发布。

## ✨ 主要功能

- 🔥 **文章发布**: 支持将 Markdown 格式的文章发布到腾讯云开发者社区
- 📝 **格式转换**: 自动将 Markdown 转换为 ProseMirror 格式，适配腾讯云编辑器
- 🏷️ **标签管理**: 支持文章分类、标签、专栏等管理功能
- 🔐 **身份认证**: 通过 Cookie 进行用户身份验证
- 📊 **状态反馈**: 提供详细的发布状态和错误信息反馈
- 🚀 **MCP 协议**: 完全兼容 MCP 协议，可与支持 MCP 的 AI 助手集成

## 🛠️ 技术栈

- **Java 21**: 使用最新的 Java LTS 版本
- **Spring Boot 3.5.3**: 现代化的 Java 应用框架
- **Spring AI MCP Server**: 官方 MCP 服务器实现
- **Retrofit 2**: 类型安全的 HTTP 客户端
- **Lombok**: 减少样板代码
- **CommonMark**: Markdown 解析和渲染
- **FastJSON**: 高性能 JSON 处理

## 📦 项目结构

```
tencent-add-article-mcp-server/
├── src/main/java/com/viper31/tecent/mcp/server/
│   ├── TencentAddArticleMcpServerApplication.java  # 主启动类
│   ├── api/                                        # API 接口层
│   │   ├── ITencentService.java                   # 腾讯云服务接口
│   │   ├── dto/                                   # 数据传输对象
│   │   │   ├── AddArticleRequest.java            # 发布文章请求
│   │   │   └── AddArticleResponse.java           # 发布文章响应
│   │   └── config/                                # 配置类
│   └── mcp/                                       # MCP 服务层
│       ├── TencentArticleService.java            # 文章服务实现
│       ├── dto/                                   # MCP 数据传输对象
│       ├── config/                                # MCP 配置
│       └── utils/                                 # 工具类
│           └── MarkdownToProseMirrorConverter.java # Markdown 转换器
├── src/main/resources/
│   └── application.yml                           # 应用配置文件
├── pom.xml                                       # Maven 配置
└── README.md                                     # 项目文档
```

## 🚀 快速开始

### 环境要求

- Java 21 或更高版本
- Maven 3.8+ 
- 腾讯云开发者社区账号

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/Viper31-one/tencent-add-article-mcp-server.git
   cd tencent-add-article-mcp-server
   ```

2. **配置认证信息**
   
   在 `src/main/resources/application.yml` 中配置你的腾讯云开发者社区 Cookie：
   ```yaml
   tencent:
     api:
       cookie: "你的腾讯云开发者社区Cookie"
   ```

3. **编译项目**
   ```bash
   mvn clean compile
   ```

4. **运行服务**
   ```bash
   mvn spring-boot:run
   ```

### 获取 Cookie

1. 登录 [腾讯云开发者社区](https://cloud.tencent.com/developer)
2. 打开浏览器开发者工具 (F12)
3. 在 Network 标签页中找到任意请求
4. 复制请求头中的 `Cookie` 值

## 📚 API 文档

### MCP 工具函数

#### `addArticle` - 发布文章到腾讯云开发者社区

**描述**: 将 Markdown 格式的文章发布到腾讯云开发者社区

**参数**:
- `title` (string, 必需): 文章标题
- `markdownContent` (string, 必需): Markdown 格式的文章内容
- `userSummary` (string, 必需): 文章摘要

**返回值**:
```json
{
  "status": 0,
  "articleId": 123456,
  "url": "https://cloud.tencent.com/developer/article/123456",
  "msg": "发布成功"
}
```

**状态码说明**:
- `0`: 发布成功
- `-1`: 发布失败
- 其他: 具体错误码

### 使用示例

#### 通过 MCP 客户端调用

```python
# 使用 MCP 客户端调用
result = mcp_client.call_tool("addArticle", {
    "title": "我的技术文章",
    "markdownContent": "# 标题\n\n这是文章内容...",
    "userSummary": "这是一篇关于技术的文章"
})
```

#### 直接 HTTP 调用

```bash
curl -X POST http://localhost:8080/mcp/addArticle \
  -H "Content-Type: application/json" \
  -d '{
    "title": "测试文章",
    "markdownContent": "# 测试\n\n这是测试内容",
    "userSummary": "测试摘要"
  }'
```

## 🔧 配置说明

### application.yml 配置项

```yaml
spring:
  application:
    name: tencent-add-article-mcp-server
  ai:
    mcp:
      server:
        name: tencent-add-article-mcp-server
        version: 0.0.3
        stdio: true

tencent:
  api:
    cookie: "你的腾讯云开发者社区Cookie"
```

### 环境变量配置

你也可以通过环境变量来配置：

```bash
export TENCENT_API_COOKIE="你的Cookie"
```

## 🎯 功能特性详解

### Markdown 转换

项目内置了强大的 Markdown 到 ProseMirror 格式转换器，支持：

- ✅ 标题 (H1-H6)
- ✅ 段落和文本
- ✅ 有序和无序列表
- ✅ 代码块和行内代码
- ✅ 引用块
- ✅ 粗体和斜体
- ✅ 链接
- ✅ 分割线

### 文章管理

- **分类管理**: 支持将文章分配到不同分类
- **标签系统**: 支持多标签管理
- **专栏功能**: 支持将文章添加到专栏
- **SEO 优化**: 支持长尾标签设置
- **评论控制**: 可控制是否开启评论
- **链接控制**: 可控制文章中的文本链接

### 错误处理

- 完善的异常处理机制
- 详细的错误日志记录
- 友好的错误信息反馈
- 网络异常重试机制

## 🔍 调试和日志

### 日志配置

项目使用 Spring Boot 的日志系统，日志文件位置：
```
data/log/tencent-add-article-mcp-server.log
```

### 调试模式

启用调试模式：
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dlogging.level.com.viper31=DEBUG"
```

## 🤝 贡献指南

我们欢迎所有形式的贡献！

### 如何贡献

1. Fork 本项目
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开一个 Pull Request

### 开发环境设置

1. 确保你的开发环境满足要求
2. 克隆项目到本地
3. 配置必要的环境变量
4. 运行测试确保一切正常

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🙏 致谢

- [Spring Boot](https://spring.io/projects/spring-boot) - 优秀的 Java 应用框架
- [Spring AI](https://spring.io/projects/spring-ai) - AI 集成框架
- [Retrofit](https://square.github.io/retrofit/) - 类型安全的 HTTP 客户端
- [CommonMark](https://commonmark.org/) - Markdown 解析库

## 📞 联系方式

- 项目维护者: Viper31
- 邮箱: [your-email@example.com]
- 项目地址: [https://github.com/Viper31-one/tencent-add-article-mcp-server]

## 🔄 更新日志

### v0.0.3-SNAPSHOT (当前版本)
- ✨ 新增 Markdown 到 ProseMirror 格式转换
- ✨ 完善文章发布功能
- ✨ 添加详细的错误处理
- ✨ 优化日志记录
- 📝 完善项目文档

### v0.0.2
- 🚀 初始版本发布
- ✨ 基础 MCP 服务器功能
- ✨ 腾讯云开发者社区 API 集成

---

⭐ 如果这个项目对你有帮助，请给我们一个星标！ 