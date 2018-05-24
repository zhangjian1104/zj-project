# zj-project
企业级开发学习试验

## 版本变更履历

#### 2018-05-24
项目去掉spring data jpa 相关依赖，更换为Mybatis框架  


#### 2018-05-23
关于JPA全局filter，自动增加数据过滤条件  
[Spring Data Jpa使用Filter过滤数据](https://www.cnblogs.com/alchimistin/p/7872749.html)  
[数据过滤器注解@Filter 如何在hibernate、spring data jpa中调用](https://my.oschina.net/youway/blog/521833)  

关于对象转换，采用MapStruct自动映射框架
[官方文档](http://mapstruct.org/documentation/installation/)  
[实例](https://segmentfault.com/a/1190000011421042)

**调整ORM框架为Mybatis，灵活性更高一些。重新学习Mybatis**  
[Mybatis 官方文档](http://www.mybatis.org/mybatis-3/zh/java-api.html)  
[SpringBoot Mybatis 配置 官方说明](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)  
[Mybatis审计功能实现，自定义主键](https://my.oschina.net/stategrace/blog/347272)  
[Mybatis 教程 ](http://wiki.jikexueyuan.com/project/mybatis-in-action/)
[Mybatis 关联表查询](https://www.linuxidc.com/Linux/2015-02/113771p5.htm)  
[Mybatis 分页插件制作](https://www.cnblogs.com/EasonJim/p/7056270.html)  
[Mybatis 分页插件](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/README_zh.md)   
[Mybatis pagehelper springboot 集成](https://github.com/pagehelper/pagehelper-spring-boot)  
[MyBatis 通用 Mapper4](https://github.com/abel533/Mapper/wiki)  


#### 2018-05-22
关于JPA的各种查询操作实例，[参考](https://www.cnblogs.com/rulian/p/6533109.html)

关于Spring boot jpa 操作月表、日表的思考：
> 通过重写*org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl* 方法指定表映射策略。这样可以保证当前实体对应的是符合业务的最新的表。  
> 该方式可以解决业务流水数据的操作，如调度表以及GPS表。历史数据原则上应该禁止修改，对于历史数据的查询应该使用自定义@Query（）语句实现。   
> ？？需要调查是否可以动态调用映射策略   
> [参考](https://blog.csdn.net/lmy86263/article/details/69053376)  
> [参考](https://blog.csdn.net/xvshu/article/details/39187779)

关于Spring boot jpa Auditing 的相关说明：  
**关键词**：@CreatedBy  @LastModifiedBy @CreatedDate  @LastModifiedDate  
**适配类型**：type Joda-Time, DateTime, legacy Java Date and Calendar, JDK8 date and time types, and long or Long  
**操作方式**：首先申明实体类，需要在类上加上注解@EntityListeners(AuditingEntityListener.class)，其次在application启动类中加上注解EnableJpaAuditing，同时在需要的字段上加上@CreatedDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy等注解。  
[参考,包括异步调用时的解决方案](http://wiselyman.iteye.com/blog/2412478)   
**高级**：如想更多自定义设置，如时间格式等设置，可了解 *@EnableJpaAuditing*的说明 

	@Data
	@MappedSuperclass
	@EntityListeners(AuditingEntityListener.class)
	public abstract class AbstractBaseDomain {
	
	  /**
	   * 主键 ： 16位数字
	   */
	  @Id
	  @GenericGenerator(name = "uuid-sys",
	      strategy = "com.zj.helper.core.util.id.MyUuidStrategy")
	  @GeneratedValue(generator = "uuid-sys")
	  @Column(name = "o_id", length = 16)
	  Long id;
	
	
	  /**
	   * 创建时间
	   */
	  @CreatedDate
	  @Column(name = "o_create_time")
	  LocalDateTime createTime;
	
	  /**
	   * 创建者Id
	   */
	  @CreatedBy
	  @Column(name = "o_create_by")
	  Long createBy;
	
	  /**
	   * 修改时间
	   */
	  @LastModifiedDate
	  @Column(name = "o_update_time")
	  LocalDateTime updateTime;
	
	  /**
	   * 修改者Id
	   */
	  @LastModifiedBy
	  @Column(name = "o_update_by")
	  Long updateBy;
	}

	@Component("MyUserIDAuditorAware")
	public class MyUserIDAuditorAware implements AuditorAware<Long> {
	
	  /**
	   * 获取登陆用户主键ID
	   *
	   * @return 用户主键ID
	   */
	  @Override
	  public Optional<Long> getCurrentAuditor() {
	
	    return Optional.of(1001L);
	  }
	}

	@Configuration
	@EnableJpaAuditing(auditorAwareRef = "MyUserIDAuditorAware")
	public class JpaConfig {
	  
	  @Bean
	  public AuditorAware<Long> auditorProvider() {
	    return new MyUserIDAuditorAware();
	  }
	}

增加默认字段值以及insert时，不插入null值。**@DynamicInsert**  **@Column(name = "o_valid",columnDefinition = "bit default 1")**  
注意：@DynamicInsert 放在父类时不起作用

	@Data
	@EqualsAndHashCode(callSuper = true)
	@Builder
	@Entity
	@DynamicInsert
	@Table(name = "t_auth_user")
	public class User extends AbstractBaseDomain {
	
	  /**
	   * 用户名
	   */
	  @Column(name = "o_name", length = 40, nullable = false)
	  String name;
	
	  /**
	   * 用户密码
	   */
	  @Column(name = "o_password", length = 40, nullable = false)
	  String password;
	
	  /**
	   * 用户邮件地址
	   */
	  @Column(name = "o_email", length = 100)
	  String email;
	
	  /**
	   * 用户电话号码
	   */
	  @Column(name = "o_telephone", length = 40)
	  String telephone;
	
	  /**
	   * 是否有效
	   */  
	  @Column(name = "o_valid",columnDefinition = "bit default 1")
	  Boolean isValid;
	
	  /**
	   * 登陆时间
	   */
	  @Column(name = "o_login_time")
	  LocalDateTime loginTime;
	
	  /**
	   * 登陆IP
	   */
	  @Column(name = "o_login_ip", length = 40)
	  String loginIp;
	
	}

#### 2018-05-21

因为业务对数据库分表有要求，所以需要首先创建完成数据库结构。因此需要关闭hibernate的表结构自动生成。同样原因所有主键不能使用Mysql的自动增长，使用自定义UUID进行主键设置。
			
	#jpa 设置  
	spring.jpa.database=mysql  
	spring.jpa.hibernate.ddl-auto=none  
	spring.jpa.show-sql=true  

SQL脚本及设计文件存储位置

	helper\sql\*.sql
	helper\sql\DatabaseModel.pdb
	helper\sql\DatabaseModel.pdm

权限采用RBAC模型，参考  [权限系统与RBAC模型概述](https://blog.csdn.net/yangwenxue_admin/article/details/73936803)以及[RBAC权限模型——项目实战](https://blog.csdn.net/zwk626542417/article/details/46726491)。  
项目主要采用 Restful 形式提供接口服务，所以采用 用户-角色-权限-资源 的模型，角色容纳了对资源所具有的许可操作。

JPA主键生成策略采取自定义。本项目主键采用银行订单号生成算法，主键为16位数字。[生成唯一订单号](https://blog.csdn.net/linkkb/article/details/78206846)

	public abstract class UuidUtil {
	    /**
	     * 生成16位数字的UUID
	     * @return 16位数字唯一值
	     */
	    public static Long getNewUUId() {
	        int machineId = 1;//最大支持1-9个集群机器部署
	        int hashCodeV = UUID.randomUUID().toString().hashCode();
	        if(hashCodeV < 0) {//有可能是负数
	            hashCodeV = - hashCodeV;
	        }
	        // 0 代表前面补充0
	        // 4 代表长度为4
	        // d 代表参数为正数型
	        return Long.valueOf(machineId + String.format("%015d", hashCodeV));
	    }
	}

JPA自定义主键生成策略，注意两点：  
1. 继承IdentifierGenerator，重写generate方法时，需要提供一个无参数构造器。  
2. 引用时需要提供类全名

	import java.io.Serializable;

	public class MyUuidStrategy implements IdentifierGenerator {
	    /**
	     * Implementors should provide a public default constructor.
	     */
	    public MyUuidStrategy() {
	    }
	
	    @Override
	    public Serializable generate(SharedSessionContractImplementor session,
	                                 Object object)
	            throws HibernateException {
	        return UuidUtil.getNewUUId();
	    }
	}


	@Data
	@Builder
	@Entity
	@Table(name = "t_auth_user")
	public class User {
	
	    @Id
	    @GenericGenerator(name="uuid-sys",strategy = "com.zj.helper.core.util.id.MyUuidStrategy")
	    @GeneratedValue(generator = "uuid-sys")
	    @Column(name = "o_id",length = 16)
	    Long id;
	
	    @Column(name="o_name",length = 40,nullable = false)
	    String name;
	}

[IDEA 使用Google Java 代码规范](https://github.com/google/styleguide)
intellij-java-google-style.xml


####2018-05-19  
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
  [spring+mybatis+druid数据源+sharding-jdbc分库分表](https://blog.csdn.net/aitangyong/article/details/53291437)  详细案例  
  [JPA案例](https://blog.csdn.net/tianyaleixiaowu/article/details/70242971) JPA案例

 
