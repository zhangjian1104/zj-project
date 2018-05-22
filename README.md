# zj-project
企业级开发学习试验

## 版本变更履历

#### 2018-05-22
关于Spring boot jpa 操作月表、日表的思考：
> 通过重写*org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl* 方法指定表映射策略。这样可以保证当前实体对应的是符合业务的最新的表。  
> 该方式可以解决业务流水数据的操作，如调度表以及GPS表。历史数据原则上应该禁止修改，对于历史数据的查询应该使用自定义@Query（）语句实现。   
> ？？需要调查是否可以动态调用映射策略   
> [参考](https://blog.csdn.net/lmy86263/article/details/69053376)

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

	***********************************************************
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

 
