# Project Title: Advanced Crawer Farmework  项目名称：先进爬虫框架
## 概述
这个框架设计用来简化分布式爬虫系统的模块化开发。

This framework is designed to simplify the modular development of distributed crawler systems.

使用该框架开发的一个完整的分布式爬虫系统至少包含一个承担消息队列功能的中心节点和一个承担爬虫工作职能的爬虫端节点。

A complete distributed crawler system developed with this framework consists of at least one central node that performs message queuing and one crawler-side node that performs the function of crawling.

## 工作原理
本框架描述了一个爬虫任务的完整生命周期，以爬虫端节点的视角来看，包含了以下几个步骤：

1、从消息队列中获取一定数量的任务，并将获取到的任务加入本机的任务队列。

2、从任务队列中获取一个任务，将该任务分发给一个适当的爬虫类执行爬虫任务。在爬虫任务执行完毕后，将爬到的数据打包成为一个结果类，计入本机的结果队列。

3、从结果队列中获取一个结果，将该结果分发给一个适当的爬虫类执行分析任务。在分析任务执行完毕后，将得到的执行结果打包成为一个可以被存储的结果，并将该可以被存储的结果加入本机的可以被存储的结果队列。

4、从可以被存储的结果中获取一个可以被存储的结果，将该可以被存储的结果分发给一个上传类，该上传类将会把结果上传回到消息队列当中。

## Classes 类
### Functional classes 功能类 

BaseCommunicationQueue  
抽象类，要求插入数据和获取并删除数据的操作
>BaseData popData()     //获取并删除数据  
>void pushData(BaseData)        //插入数据

Communitcation 具体类，用于承担通信功能,使用依赖注入方式初始化BaseCommunicationQueue
>BaseMession popMession()   
>void pushMession(BaseMession)
>BaseCrawledData popCrawledData()
>void pushCrawledData(BaseCrawledData)
>BaseAnalyzedData popAnalyzedData()
>void pushAnalyzedData(BaseAnalyzedData)

BaseCatcher  
抽象类，用于定义任务获取操作，用户应当实现该类。
>BaseMession getMession()       //用于从中心节点获取一个爬虫任务

CatcherManager  
具体类，用于多线程的调用Catcher，调用的具体Catcher通过依赖注入方式在config.xml中定义
>void run()

BaseCrawler  
抽象类，用于定义爬虫动作，用户应当实现该类，要求实现处理Mession并获取BaseCrawledData。
>BaseCrawledData dealMession(BaseMession)

BaseCrawlerSelection
抽象类，用于支持使用特殊规则判断应当执行爬虫操作的类名。用户应当实现该类并在config.xml中注册，若无注册使用默认方式返回null。
>String selectCrawler(BaseMession)

CrawlerDistributor  
具体类，用于多线程的将爬虫任务分发给相应的Crawler。初始化时使用依赖注入的方式指定BaseCrawlerSelection。执行过程中，首先对每个获取的任务执行SelectionCwarler，若返回值为null，则寻找直接映射确定Crawler类名。
>void run()

BaseAnalyzer  
抽象类，用于处理爬虫直接得到的数据并获取BaseAnalyzedData。需要由用户自行实现该类。
>BaseAnalyzedData DealCrawledData(BaseCrawledData)

AnalyzerDistributor  
抽象类，用于支持使用特殊规则判断应当执行数据处理操作的类名。初始化时使用依赖注入的方式指定BaseAnalyzerSelection。执行过程中，首先对每个获取的任务执行SelectionAnalyzer，若返回值为null，则寻找直接映射确定Analyzer类名。用户应当实现该类并在config.xml中注册，若无注册使用默认方式返回null。
>void run()

BaseAnalyzerSelection  
抽象类，用于支持使用特殊规则判断应当执行数据处理操作的类名。用户应当实现该类并在config.xml中注册，若无注册使用默认方式返回null。
>string selectAnalyzer(BaseCrawledData)



UpdaterManager  
具体类，用于多线程的调用Catcher，调用的具体Catcher通过依赖注入方式在config.xml中定义
>void run()

BaseUpdater  
抽象类，用于定义任务上传操作，用户应当实现该类。
>void updateAnalyzeredData(BaseAnalyzedData)


DelaysConstants
>类名-操作成功的等待时间、类名-操作失败的等待时间。默认为不等待,内部实现应当为Map<String,Integer>  
>int getSuccessfulDelay(String)  
>int getFailureDelay(String)  
>因为xml支持树状结构的解析，所以应当能将Delay写到一个树枝当中。

ConfigConstants
>各节点的线程数量配置  
>消息队列的API调用方式  

MappingConstants
>Json对Mession的映射
>Mession类型到Crawler的直接对应  
>CrawledData到AnalyzedData的直接对应  
>AnalyzedData到Updater的直接对应

### Data class 数据类
BaseData  
抽象类，所有数据类的父类，BaseData含有一个String类型的强制变量，用来表示类别。

BaseMession  
抽象类，继承BaseData

BaseCrawledData  
抽象类，继承BaseData

BaseAnalyzedData  
抽象类，继承BaseData

## 名词对照
config.xml 使用一个大的XML作为整个爬虫端节点的配置文件。

Message queue 主要职责为分发爬虫端节点所需的Mession和收集存储爬虫端节点上传的数据。

Catcher 主要职责为从消息队列中获取Mession。

Crawler 主要职责为处理Catcher取得的Mession，应当得出Crawled data。

Analyzer 主要职责为处理Crawler提供的Crawler data，应当得出Analyzed data 

Updater 主要职责为处理Analyzer提供的Analyzed data。

Mession 代表爬虫端应当执行的任务，主要任务内容为URL。

Ceawled data 代表爬虫线程爬取到的，未经加工的数据。

Analyzed data 代表已经被分析线程加工过的数据。



# Getting Started 使用手册
# Prerequisites 运行和测试环境
# Installing 安装
# Running the tests 测试
# Deployment
# Authors


