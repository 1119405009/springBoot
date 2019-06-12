Spring Boot + JPA(hibernate 5) 开发时，数据库表名大小写问题
https://blog.51cto.com/4528195/1983780

#springJpa的使用
英文文档
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations
https://javabeat.net/tag/spring-data-jpa/
https://www.objectdb.com/java/jpa/query/api
https://www.journaldev.com/17034/spring-data-jpa

SpringData JPA进阶查询—JPQL/原生SQL查询、分页处理、部分字段映射查询
https://blog.csdn.net/phapha1996/article/details/78994395

Spring Data JPA 使用详解
https://www.jianshu.com/p/a2f98f6d6fbd


Spring Data JPA 实现多表关联查询
https://liuyanzhao.com/6978.html

Spring Data JPA 之 一对一，一对多，多对多 关系映射
https://liuyanzhao.com/7913.html

JPA常见注解及使用
https://www.jianshu.com/p/38d247f02724

JPA分组查询，求和，自定义查询字段，自定义VO承接
https://blog.csdn.net/m0_37890289/article/details/80708824

Spring Data JPA 函数的用法
https://blog.csdn.net/liuyunyihao/article/details/81986172

范例：
订单按天数分组
  List<Order> orders = orderDao.findAll((Root<Order> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      predicates.add(cb.between(root.get("createAt"), orderQuery.getCreateTimeBegin(), orderQuery.getCreateTimeEnd()));
      //状态为已支付
      predicates.add(cb.equal(root.get("status").as(Integer.class), Order.STATUS_PAIED));
      //将时间格式转为yyyy-mm-ss格式的函数
      Expression<String> date = cb.function("DATE_FORMAT", String.class, root.get("createAt"), cb.literal("%Y-%m-%d")).as(String.class);
      return cq.multiselect(
          date.alias("date"),
          cb.count(root.get("id").as(Long.class)).alias("orderTotal"),
          cb.sum(root.get("money").as(Long.class)).alias("orderTotalMoney"),
          cb.count(root.get("device").get("id").as(Long.class)).alias("devicesCount"),
          cb.count(root.get("user").get("id").as(Long.class)).alias("usersCount")

      ).where(predicates.toArray(new Predicate[predicates.size()])).
          groupBy(date).orderBy(cb.desc(date)).getGroupRestriction();
    });

订单按天数分组
@Query(value = "select DATE_FORMAT(o.createAt,'%Y-%m-%d') date,count(o.id) total,count(IF(o.`status`=0,o.id,null)) initCount,count(IF(p.`status`=1,o.id,null)) paidCount,count(IF(p.`status`=2,o.id,null)) cancelCount,count(IF(p.`status`=3,o.id,null)) refundCount,SUM(o.money) totalMoney,SUM(IF(p.`status`=0,p.money,0)) initMoney,sum(IF(p.`status`=1,p.money,0)) paidMoney,SUM(IF(p.`status`=2,p.money,0)) cancelMoney,sum(IF(p.`status`=4,p.money,0)) refundMoney,count(o.deviceId) deviceTotalCount,count(IF(o.`status`=0,o.deviceId,null)) deviceInitCount,count(IF(o.`status`=10,o.deviceId,null)) devicePaidCount,count(IF(o.`status`=20,o.deviceId,null)) deviceCancelCount,count(IF(o.deviceId is null,1,null)) userTotalCount,count(IF(o.`status` = 0 and o.deviceId is null,1,null)) userInitCount,count(IF(o.`status` = 10 and o.deviceId is null,1,null)) userPaidCount,count(IF(o.`status` = 20 and o.deviceId is null,1,null)) userCancelCount,count(IF((o.`status` in (20,0) AND p.`status`=1) OR (r.`status`=1 AND r.type=20),1,null)) exceptionOrderCount,(select count(id) from refund where type=30 and `status`=1 and remark is not null and DATE_FORMAT(createAt,'%Y-%m-%d')=DATE_FORMAT(o.createAt,'%Y-%m-%d')) exceptionInsuranceCount from `order` o LEFT JOIN payment p ON p.id=o.paymentId LEFT JOIN refund r ON r.paymentId=p.id where o.createAt BETWEEN ?1 AND ?2 GROUP BY DATE_FORMAT(o.createAt,'%Y-%m-%d')",nativeQuery = true)
 List<Map> findReport(Date start,Date end);














