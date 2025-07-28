# 贡献指南

感谢您对腾讯云开发者社区 MCP 服务器项目的关注！我们欢迎所有形式的贡献。

## 🤝 如何贡献

### 报告 Bug

如果您发现了一个 Bug，请通过以下步骤报告：

1. 检查是否已经有相关的 Issue
2. 如果没有，请创建一个新的 Issue
3. 使用 Bug 报告模板，并提供以下信息：
   - 详细的 Bug 描述
   - 重现步骤
   - 期望行为
   - 实际行为
   - 环境信息（操作系统、Java 版本等）

### 功能请求

如果您有新的功能想法，请：

1. 检查是否已经有相关的 Issue
2. 如果没有，请创建一个新的 Issue
3. 详细描述您想要的功能
4. 说明为什么需要这个功能
5. 提供使用场景示例

### 代码贡献

#### 开发环境设置

1. **Fork 项目**
   ```bash
   git clone https://github.com/your-username/tencent-add-article-mcp-server.git
   cd tencent-add-article-mcp-server
   ```

2. **创建分支**
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **安装依赖**
   ```bash
   mvn clean install
   ```

4. **运行测试**
   ```bash
   mvn test
   ```

#### 开发规范

1. **代码风格**
   - 遵循 Java 编码规范
   - 使用 4 个空格缩进
   - 类名使用 PascalCase
   - 方法名和变量名使用 camelCase
   - 常量使用 UPPER_SNAKE_CASE

2. **注释规范**
   - 所有公共方法必须有 JavaDoc 注释
   - 复杂的业务逻辑需要添加行内注释
   - 使用中文注释，便于理解

3. **提交规范**
   ```bash
   git commit -m "feat: 添加新功能"
   git commit -m "fix: 修复Bug"
   git commit -m "docs: 更新文档"
   git commit -m "style: 代码格式调整"
   git commit -m "refactor: 代码重构"
   git commit -m "test: 添加测试"
   git commit -m "chore: 构建工具调整"
   ```

4. **测试要求**
   - 新功能必须包含单元测试
   - 测试覆盖率不低于 80%
   - 所有测试必须通过

#### 提交 Pull Request

1. **推送分支**
   ```bash
   git push origin feature/your-feature-name
   ```

2. **创建 Pull Request**
   - 在 GitHub 上创建 Pull Request
   - 使用提供的 PR 模板
   - 详细描述您的更改
   - 关联相关的 Issue

3. **代码审查**
   - 等待维护者审查
   - 根据反馈进行修改
   - 确保所有 CI 检查通过

## 📋 开发指南

### 项目结构

```
src/main/java/com/viper31/tecent/mcp/server/
├── api/                    # API 接口层
│   ├── dto/               # 数据传输对象
│   └── config/            # 配置类
├── mcp/                   # MCP 服务层
│   ├── dto/               # MCP 数据传输对象
│   ├── config/            # MCP 配置
│   └── utils/             # 工具类
└── TencentAddArticleMcpServerApplication.java  # 主启动类
```

### 添加新的 MCP 工具

1. **创建 DTO 类**
   ```java
   @Data
   @Builder
   @NoArgsConstructor
   @AllArgsConstructor
   public class NewFunctionRequest {
       private String param1;
       private String param2;
   }
   ```

2. **创建响应类**
   ```java
   @Data
   public class NewFunctionResponse {
       private Integer status;
       private String message;
   }
   ```

3. **在服务类中添加方法**
   ```java
   @Tool(description = "新功能描述")
   public NewFunctionResponse newFunction(NewFunctionRequest request) {
       // 实现逻辑
   }
   ```

### 配置管理

- 敏感信息（如 Cookie）不要直接写在代码中
- 使用配置文件或环境变量
- 提供示例配置文件

### 日志规范

```java
@Slf4j
public class YourService {
    public void yourMethod() {
        log.debug("调试信息");
        log.info("一般信息");
        log.warn("警告信息");
        log.error("错误信息", exception);
    }
}
```

## 🧪 测试指南

### 单元测试

```java
@SpringBootTest
class YourServiceTest {
    
    @Autowired
    private YourService yourService;
    
    @Test
    void testYourMethod() {
        // 测试逻辑
    }
}
```

### 集成测试

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class YourIntegrationTest {
    
    @Test
    void testApiEndpoint() {
        // 测试 API 端点
    }
}
```

## 📚 文档规范

### 代码文档

- 所有公共类和方法必须有 JavaDoc
- 使用中文注释
- 包含参数说明和返回值说明

### README 更新

- 新功能需要更新 README.md
- 更新 API 文档
- 添加使用示例

## 🚀 发布流程

### 版本号规范

使用语义化版本号：`主版本.次版本.修订版本`

- 主版本：不兼容的 API 修改
- 次版本：向下兼容的功能性新增
- 修订版本：向下兼容的问题修正

### 发布步骤

1. 更新版本号
2. 更新 CHANGELOG.md
3. 创建 Release
4. 发布到 Maven Central（如果需要）

## 📞 联系方式

- 项目维护者：Viper31
- 邮箱：[your-email@example.com]
- 项目地址：[https://github.com/your-username/tencent-add-article-mcp-server]

## 🙏 致谢

感谢所有为这个项目做出贡献的开发者！

---

如果您有任何问题或建议，请随时联系我们。 