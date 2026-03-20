\BMI 计算器 — Java 版
一个支持\*\*图形界面\*\*和\*\*命令行\*\*的双模式 BMI 计算器。输入姓名、身高、体重和日期，自动计算 BMI 并给出健康建议，所有记录都会保存到本地文件中，方便查看历史。
\---
\✨ 功能特点
\- ✅ \*\*两种使用方式\*\*：JavaFX 图形界面 或 命令行，满足不同场景
\- ✅ \*\*智能计算\*\*：自动计算 BMI，并根据中国标准给出健康建议（偏瘦/正常/偏胖/肥胖）
\- ✅ \*\*历史记录\*\*：每次计算结果自动保存到 `records.txt`，可随时查看
\- ✅ \*\*界面美观\*\*：图形界面采用粉色系风格，按钮有悬停效果，使用 CSS 样式
\- ✅ \*\*健壮性\*\*：处理了输入异常、日期错误、文件读写失败等情况
!![BMI计算器界面](guishow.png)
\---
\🛠 技术栈
\- Java 11+  
\- JavaFX（图形界面）  
\- 文件 I/O（数据持久化）  
\- CSS（界面样式）
\---
\🚀 如何运行
\1. 图形界面版本（JavaFX）
> ⚠️ \*\*注意\*\*：如果你的 JDK 不自带 JavaFX（如 JDK 11+），需要先配置 JavaFX 运行时库。
\方法一：使用 IntelliJ IDEA（推荐）
1\. 将项目导入 IDEA  
2\. 添加 JavaFX 库：`File` → `Project Structure` → `Libraries` → 添加 JavaFX SDK 的 `lib` 目录  
3\. 在 `VM options` 中添加：--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
4\. 运行 `BMICalculatorGUI 类
\方法二：命令行编译运行
```bash
\编译
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml BMICalculatorGUI.java
\运行
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml BMICalculatorGUI
```
\2. 命令行版本（无需 JavaFX）
\编译
javac Hello.java
\运行
java Hello
 📌 未来计划
- [ ] 添加图表展示 BMI 变化趋势
- [ ] 支持导出历史记录为 CSV 文件
 👩‍💻 作者
momomo714  
持续学习中  
GitHub: [momomo714](https://github.com/momomo714)]

