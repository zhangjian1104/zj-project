# zj-project
企业级开发学习试验

## 版本变更履历

*2018-05-20*  
使用 Spring-JPA 完成单表增删改查。 

因为业务对数据库分表有要求，所以需要首先创建完成数据库结构。因此需要关闭hibernate的表结构自动生成。同样原因所有主键不能使用Mysql的自动增长，使用UUID进行主键设置。
			
	#jpa 设置  
	spring.jpa.database=mysql  
	spring.jpa.hibernate.ddl-auto=none  
	spring.jpa.show-sql=true  

SQL文件存储位置

	helper\sql\

----------
*2018-05-19*  
 新建项目


  
## 项目说明
##### 关于DAO层相关处理约定
> 111  


##### 关于数据库分库、分表的相关说明
> 由于需要保存历史数据以及流水数据，需要采用分表操作，项目上线后会采用主从分离，读写分离等配置。  
  目前解决方案初步定为采用当当网的开源中间件[Sharding JDBC](http://shardingsphere.io/document/cn/overview/)。
  该中间件支持JPA、Mybatis，侵入少。  
  [项目地址](https://github.com/dangdangdotcom/sharding-jdbc)    GITHUB地址  
  [案例说明](https://www.cnblogs.com/codestory/p/5591651.html)   实际业务改造案例

 
