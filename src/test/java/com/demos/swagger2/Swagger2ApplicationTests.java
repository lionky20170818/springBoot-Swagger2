package com.demos.swagger2;

import com.demos.entity.User;
import com.demos.repository.UserMapper;
import com.demos.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Swagger2ApplicationTests {

	//ValueOperations方式,可以参考http://blog.csdn.net/whatlookingfor/article/details/51863286
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> template;
	@Resource(name = "redisTemplate")
	private ValueOperations<String, Object> vOps;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;

	@Test
	public void redisTest() {
		//调用方法
		template.opsForValue().set("key20171128001","value20171128001");
		System.out.println("RedisTemplateTest.testAddTem=="+template.opsForValue().get("key20171128001"));
		//调用方法
		vOps.set("wohenhao20171128002","nihaobuhao20171128002");
		System.out.println("vOps.testAddTem=="+vOps.get("wohenhao20171128002"));

	}

	@Test
	public void jpaMybatisTest() {
		// 创建测试数据
		userRepository.save(new User("Test1", "abc"));
		// 测试findAll, 查询所有记录
		System.out.println("findAll = " + userRepository.findAll().size());

		log.info("=="+userMapper.findById(1));


	}

}
