package com.connor2.jedis;

//http://blog.csdn.net/linlzk/article/details/41801391
import redis.clients.jedis.Jedis;

public class TestJedis {

	private Jedis jedis;

	public void before() {
		jedis = new Jedis("127.0.0.1");
	}

	public void test1() {
		String name = "name";
		String value = "qq";
		jedis.set(name, value);
		System.out.println("追加前：" + jedis.get(name)); // 追加前：qq
		// 在原有值得基础上添加,如若之前没有该key，则导入该key
		jedis.append(name, "ww");
		System.out.println("追加后：" + jedis.get(name)); // 追加后：qqww

		jedis.append("id", "ee");
		System.out.println("没此key：" + jedis.get(name));
		System.out.println("get此key：" + jedis.get("id"));
	}
	
	/**
	 * 
	 */
	public void test4() {
		jedis.lpush("list", "aa");
		jedis.lpush("list", "bb");
		jedis.lpush("list", "cc");
		System.out.println(jedis.lrange("list", 0, -1));
		System.out.println(jedis.lrange("list", 0, 1));
		System.out.println(jedis.lpop("list")); // 栈顶
		jedis.del("list");
	}

}
