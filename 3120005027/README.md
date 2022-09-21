# 目录

[toc]



## 需求
1. 题目：论文查重

描述如下：

设计一个论文查重算法，给出一个原文文件和一个在这份原文上经过了增删改的抄袭版论文的文件，在答案文件中输出其重复率。

原文示例：今天是星期天，天气晴，今天晚上我要去看电影。
抄袭版示例：今天是周天，天气晴朗，我晚上要去看电影。
要求输入输出采用文件输入输出，规范如下：

从命令行参数给出：论文原文的文件的绝对路径。
从命令行参数给出：抄袭版论文的文件的绝对路径。
从命令行参数给出：输出的答案文件的绝对路径。
我们提供一份样例，使用方法是：orig.txt是原文，其他orig_add.txt等均为抄袭版论文。

注意：答案文件中输出的答案为浮点型，精确到小数点后两位
## 1.前言
1.1 

项目地址：[GitHub](https://github.com/wakakaJC/-3120005027.git)

1.2 开发环境

+ 编译语言：Java 15
+ 工具：IntelliJ IDEA 2021.1.3 
+ 项目构建工具：Maven
+ 单元测试：Junit_4.12
+ 性能分析工具：JProfiler_11.4
+ 依赖的外部jar包：Ansj分词器
## 2.接口的设计与实现
2.1 TxtIOUtils
+ 包含两个方法
+ 分别为写入，读取
2.2 MySimHash
1. 通过ansj把文件里的内容进行切分
2. 计算hash，加权
3. 合并，降维
4. 计算海明距离
5. 通过海明距离计算出相似度
2.3 main
1. 从命令行输入的路径名读取对应的文件，将文件的内容转化为对应的字符串
2. 通过调用MySimHash工具类计算出相似值
3. 把相似度写入最后的结果文件中

## 3.性能分析

![](C:\Users\JC_insistent\AppData\Roaming\Typora\typora-user-images\image-20220920154015569.png)

![image-20220920154023132](C:\Users\JC_insistent\AppData\Roaming\Typora\typora-user-images\image-20220920154023132.png)

## 4.测试
1. MainTest测试：

   ![](C:\Users\JC_insistent\AppData\Roaming\Typora\typora-user-images\image-20220920154048605.png)

   ![image-20220920154138883](C:\Users\JC_insistent\AppData\Roaming\Typora\typora-user-images\image-20220920154138883.png)

2. MySimHashTest测试：

   + 测试文本1：

     ```
     "芭芭拉:你了不起，你清高，你现在想改我了。好，真好，你了不起啊！" +
             "你当初设计的这个水环，你有没有想过帮我优化一下啊？我跟你讲，我现在不想改了！你知不知道我之前受什么样" +
             "的欺负？你知不知道我受什么样的欺负？就因为我帮角色上水，我控自己，我内鬼！这什么破水环破水环破水环啊？！\n" +
             "我就是要一步一步一步、一步一步一步地追到最高。我要做芭天后！我不要再让人家欺负我。我受不了了，我不要让别人" +
             "欺负我，我要做芭天后！我要做一个，做一个最高的芭天后，3.0绽放大C芭天后！"
     ```

   + 测试文本2：

     ```
     "刻晴:你了不起，你清高，你现在还坚持用我大世界。好，真好，你真爱我！" +
             "你开服抽出我到现在，你有没有想过别人怎么看你的！我跟你讲，我忍不下去！你知不知道我心里有多么难受？" +
             "你知不知道我受什么样的欺负？就因为我雷系刮痧，我打若陀，我打不动！这什么破属性破属性破属性啊？！\n" +
             "我就是要一步一步一步、一步一步一步地追到最高。我要去须弥！我不要再让人家欺负我。我受不了了，我不要让别人" +
             "笑话你，我要做璃月雷神！我要做一个，做一个没人看不起，3.0激化大C璃月雷神！"
     ```

   测试结果：

   ![image-20220920203405184](C:\Users\JC_insistent\AppData\Roaming\Typora\typora-user-images\image-20220920203405184.png)

   

   ![image-20220920203423255](C:\Users\JC_insistent\AppData\Roaming\Typora\typora-user-images\image-20220920203423255.png)

   

3. TxtIOUtilsTest测试：

   ![image-20220920203443603](C:\Users\JC_insistent\AppData\Roaming\Typora\typora-user-images\image-20220920203443603.png)

## 5.PSP表格
| ***\*PSP2.1\****                        | ***\*Personal Software Process Stages\**** | ***\*预估耗时（分钟）\**** | ***\*实际耗时（分钟）\**** |
| --------------------------------------- | ------------------------------------------ | -------------------------- | -------------------------- |
| Planning                                | 计划                                       | 30                         | 30                         |
| · Estimate                              | · 估计这个任务需要多少时间                 | 10                         | 10                         |
| Development                             | 开发                                       | 180                        | 180                        |
| · Analysis                              | · 需求分析 (包括学习新技术)                | 180                        | 240                        |
| · Design Spec                           | · 生成设计文档                             | 30                         | 30                         |
| · Design Review                         | · 设计复审                                 | 30                         | 30                         |
| · Coding Standard                       | · 代码规范 (为目前的开发制定合适的规范)    | 30                         | 30                         |
| · Design                                | · 具体设计                                 | 30                         | 60                         |
| · Coding                                | · 具体编码                                 | 120                        | 120                        |
| · Code Review                           | · 代码复审                                 | 60                         | 60                         |
| · Test                                  | · 测试（自我测试，修改代码，提交修改）     | 90                         | 90                         |
| Reporting                               | 报告                                       | 30                         | 60                         |
| · Test Repor                            | · 测试报告                                 | 30                         | 60                         |
| · Size Measurement                      | · 计算工作量                               | 20                         | 20                         |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划             | 30                         | 30                         |
|                                         | · 合计                                     | 900                        | 1080                       |
## 6.参考

http://t.csdn.cn/tP9wM

http://blog.sina.com.cn/u/1328012245

http://t.csdn.cn/WaXhx