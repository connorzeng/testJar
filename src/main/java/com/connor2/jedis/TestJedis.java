package com.connor2.jedis;

//http://blog.csdn.net/linlzk/article/details/41801391

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestJedis {

	private Jedis jedis;

	{
		jedis = new Jedis("127.0.0.1");
	}

	public void testKey() {
		System.out.println("====key功能展示====");
		try {
			jedis.select(0);
			System.out.println("清除数据：" + jedis.flushDB());
			System.out.println("判断某个键是否存在：" + jedis.exists("1"));
			System.out.println("新增{1，a}键值对:" + jedis.set("1", "a"));
			System.out.println(jedis.exists("1"));
			System.out.println("新增{2，b}键值对:" + jedis.set("2", "b"));
			System.out.println("系统中所有的键如下：" + jedis.keys("*").toString());
			System.out.println("删除键 1:" + jedis.del("1"));
			System.out.println("判断键 1是否存在：" + jedis.exists("1"));
			System.out.println("设置键 2的过期时间为5s:" + jedis.expire("2", 5));
			TimeUnit.SECONDS.sleep(2);
			System.out.println("查看键 2的剩余生存时间：" + jedis.ttl("2"));
			System.out.println("移除键 2的生存时间：" + jedis.persist("2"));
			System.out.println("查看键 2的剩余生存时间：" + jedis.ttl("2"));
			System.out.println("查看键 2所存储的值的类型：" + jedis.type("2"));
			System.out.println("查看键 2的值：" + jedis.get("2"));

			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void testString() {
		try {
			jedis.select(1);
			jedis.flushDB();
			System.out.println("====字符串功能展示====");
			System.out.println("增:");
			System.out.println(jedis.set("a", "1"));
			System.out.println(jedis.set("b", "2"));
			System.out.println(jedis.set("c", "3"));
			System.out.println("删除键 a:" + jedis.del("a"));
			System.out.println("获取键 a:" + jedis.get("a"));
			System.out.println("修改键 b:" + jedis.set("b", "bChanged"));
			System.out.println("获取键 b 的值:" + jedis.get("b"));
			System.out.println("在键 c后面加入值：" + jedis.append("c", "End"));
			System.out.println("获取键 c的值：" + jedis.get("c"));
			System.out.println("增加多个键值对：" + jedis.mset("key01", "value01", "key02", "value02", "key03", "value03"));
			System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));
			System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03", "key04"));
			System.out.println("删除多个键值对：" + jedis.del(new String[]{"key01", "key02"}));
			System.out.println("获取多个键值对：" + jedis.mget("key01", "key02", "key03"));

			jedis.flushDB();
			System.out.println("新增键值对防止覆盖原先值:");
			System.out.println(jedis.setnx("key001", "value001"));
			System.out.println(jedis.setnx("key002", "value002"));
			System.out.println(jedis.setnx("key002", "value002-new"));
			System.out.println("获取键key001的值：" + jedis.get("key001"));
			System.out.println("获取键key002的值：" + jedis.get("key002"));

			System.out.println("新增键值对并设置有效时间:");
			System.out.println(jedis.setex("key003", 2, "value003"));
			System.out.println("获取键key003的值：" + jedis.get("key003"));
			TimeUnit.SECONDS.sleep(3);
			System.out.println("获取键key003的值：" + jedis.get("key003"));

			System.out.println("获取原值，更新为新值:");
			System.out.println(jedis.getSet("key002", "key2GetSet"));
			System.out.println("获取键key002的值：" + jedis.get("key002"));

			System.out.println("截取key002的值的字符串：" + jedis.getrange("key002", 2, 5));

			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * 整数和浮点数
	 */
	public void testNumber() {
		try {
			jedis.select(2);
			jedis.flushDB();
			System.out.println("====整数和浮点数功能展示====");
			jedis.set("key001", "1");
			jedis.set("key002", "2");
			jedis.set("key003", "3.3");
			System.out.println("获取键key001的值：" + jedis.get("key001"));
			System.out.println("获取键key002的值：" + jedis.get("key002"));
			System.out.println("将键key001的值+1：" + jedis.incr("key001"));
			System.out.println("获取键key001的值：" + jedis.get("key001"));
			System.out.println("将键key002的值-1：" + jedis.decr("key002"));
			System.out.println("获取键key002的值：" + jedis.get("key002"));
			System.out.println("将key001的值加上整数5：" + jedis.incrBy("key001", 5));
			System.out.println("获取key001的值：" + jedis.get("key001"));
			System.out.println("将key002的值减去整数5：" + jedis.decrBy("key002", 5));
			System.out.println("获取key002的值：" + jedis.get("key002"));

			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。
	 */
	public void testList() {

		jedis.select(3);
		jedis.flushDB();
		System.out.println("====列表list功能展示====");
		jedis.lpush("collections", "ArrayList", "LinkedList", "Vector", "Stack", "queue");
		jedis.lpush("collections", "HashMap");
		jedis.lpush("collections", "HashMap");
		jedis.lpush("collections", "HashMap");
		jedis.lpush("collections", "HashMap");
		jedis.lpush("number", "1");
		jedis.lpush("number", "2");
		jedis.lpush("number", "3");
		// -1 代表倒数第一个
		System.out.println("collections 的内容：" + jedis.lrange("collections", 0, -1));
		System.out.println("collections区间0-2内容：" + jedis.lrange("collections", 0, 2));
		System.out.println("=================");
		// 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
		System.out.println("删除指定元素个数：" + jedis.lrem("collections", 2, "HashMap"));
		System.out.println("collections 的内容：" + jedis.lrange("collections", 0, -1));
		System.out.println("删除区间0-4以外的数据：" + jedis.ltrim("collections", 0, 4));
		System.out.println("collections 的内容：" + jedis.lrange("collections", 0, -1));
		System.out.println("collections列表出栈（左端）：" + jedis.lpop("collections"));
		System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
		System.out.println("collections添加元素，从列表右端，与lpush相对应：" + jedis.rpush("collections", "EnumMap"));
		System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
		System.out.println("collections列表出栈（右端）：" + jedis.rpop("collections"));
		System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
		System.out.println("修改collections指定下标1的内容：" + jedis.lset("collections", 1, "LinkedArrayList"));
		System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
		System.out.println("=================");
		System.out.println("collections的长度：" + jedis.llen("collections"));
		System.out.println("获取collections下标为2的元素：" + jedis.lindex("collections", 2));
		System.out.println("=================");
		jedis.lpush("sortedList", "3", "6", "2", "0", "7", "4");
		System.out.println("sortedList排序前：" + jedis.lrange("sortedList", 0, -1));
		System.out.println(jedis.sort("sortedList"));
		System.out.println("sortedList排序后：" + jedis.lrange("sortedList", 0, -1));

		System.out.println("");
	}



	/**
	 * Redis hash 是一个键值(key=>value)对集合。
	 Redis hash是一个string类型的field和value的映射表，hash特别适合用于存储对象。
	 */
	public void testHash() {

		try {
			System.out.println("=======集合（Set）=======");
			jedis.select(5);
			jedis.flushDB();
			Map<String, String> map = new HashMap<String, String>();
			map.put("key001", "value001");
			map.put("key002", "value002");
			map.put("key003", "value003");
			jedis.hmset("hash", map);
			jedis.hset("hash", "key004", "value004");
			// return Map<String,String>
			System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));
			// return Set<String>
			System.out.println("散列hash的所有键为：" + jedis.hkeys("hash"));
			// return List<String>
			System.out.println("散列hash的所有值为：" + jedis.hvals("hash"));
			System.out.println("将key006保存的值加上一个整数，如果key006不存在则添加key006：" + jedis.hincrBy("hash", "key006", 6));
			System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));
			System.out.println("将key006保存的值加上一个整数，如果key006不存在则添加key006：" + jedis.hincrBy("hash", "key006", 3));
			System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));
			System.out.println("删除一个或者多个键值对：" + jedis.hdel("hash", "key002"));
			System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));
			System.out.println("散列hash中键值对的个数：" + jedis.hlen("hash"));
			System.out.println("判断hash中是否存在key002：" + jedis.hexists("hash", "key002"));
			System.out.println("判断hash中是否存在key003：" + jedis.hexists("hash", "key003"));
			System.out.println("获取hash中的值：" + jedis.hmget("hash", "key003"));
			System.out.println("获取hash中的值：" + jedis.hmget("hash", "key003", "key004"));

			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Redis的Set是string类型的无序集合。
	 集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。
	 */
	public void testSet() {
		try {
			jedis.select(4);
			jedis.flushDB();
			System.out.println("========测试集合（set）=========");
			System.out.println("集合set添加数据：" + jedis.sadd("setElement", "e1", "e7", "e3", "e6", "e0", "e4"));
			System.out.println(jedis.sadd("setElement", "e6"));
			System.out.println("setElement的所有元素：" + jedis.smembers("setElement"));
			System.out.println("删除元素e0:" + jedis.srem("setElement", "e0"));
			System.out.println("setElement的所有元素：" + jedis.smembers("setElement"));
			System.out.println("删除两个元素e7和e6：" + jedis.srem("setElement", "e7", "e6"));
			System.out.println("setElement的所有元素为：" + jedis.smembers("setElement"));
			System.out.println("随机的移除集合中的一个元素：" + jedis.spop("setElement"));
			System.out.println("随机的移除集合中的一个元素：" + jedis.spop("setElement"));
			System.out.println("setElement的所有元素为：" + jedis.smembers("setElement"));
			System.out.println("setElement中包含元素的个数：" + jedis.scard("setElement"));
			System.out.println("e3是否在setElement中：" + jedis.sismember("setElement", "e3"));
			System.out.println("e1是否在setElement中：" + jedis.sismember("setElement", "e1"));

			System.out.println("=================");
			System.out.println(jedis.sadd("setElement1", "e1", "e2", "e4", "e3", "e0", "e8", "e7", "e5"));
			System.out.println(jedis.sadd("setElement2", "e1", "e2", "e4", "e3", "e0", "e8"));
			System.out.println("将setElement1中删除e1并存入setElement3中：" + jedis.smove("setElement1", "setElement3", "e1"));
			System.out.println("将setElement1中删除e2并存入setElement3中：" + jedis.smove("setElement1", "setElement3", "e2"));
			System.out.println("setElement1中的元素：" + jedis.smembers("setElement1"));
			System.out.println("setElement3中的元素：" + jedis.smembers("setElement3"));

			System.out.println("集合运算:");
			System.out.println("setElement1中的元素：" + jedis.smembers("setElement1"));
			System.out.println("setElement2中的元素：" + jedis.smembers("setElement2"));
			System.out.println("setElement1和setElement2的交集:" + jedis.sinter("setElement1", "setElement2"));
			System.out.println("setElement1和setElement2的并集:" + jedis.sunion("setElement1", "setElement2"));
			// setElement1中有，setElement2中没有
			System.out.println("setElement1和setElement2的差集:" + jedis.sdiff("setElement1", "setElement2"));

			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * zset(sorted set：有序集合)
	 Redis zset 和 set 一样也是string类型元素的集合,且不允许重复的成员。
	 不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
	 zset的成员是唯一的,但分数(score)却可以重复。
	 * testHash
	 */
	public void testSortSet() {

		try {
			System.out.println("=======有序集合=======");
			jedis.select(6);
			jedis.flushDB();
			Map<String, Double> map = new HashMap<String, Double>();
			map.put("key2", 1.2);
			map.put("key3", 4.0);
			map.put("key4", 5.0);
			map.put("key5", 0.2);
			System.out.println(jedis.zadd("zset", 3, "key1"));
			System.out.println(jedis.zadd("zset", map));
			System.out.println("zset中的所有元素：" + jedis.zrange("zset", 0, -1));
			System.out.println("zset中的所有元素：" + jedis.zrangeWithScores("zset", 0, -1));
			System.out.println("zset中的所有元素：" + jedis.zrangeByScore("zset", 0, 100));
			System.out.println("zset中的所有元素：" + jedis.zrangeByScoreWithScores("zset", 0, 100));
			System.out.println("zset中key2的分值：" + jedis.zscore("zset", "key2"));
			System.out.println("zset中key2的排名：" + jedis.zrank("zset", "key2"));
			System.out.println("删除zset中的元素key3：" + jedis.zrem("zset", "key3"));
			System.out.println("zset中的所有元素：" + jedis.zrange("zset", 0, -1));
			System.out.println("zset中元素的个数：" + jedis.zcard("zset"));
			System.out.println("zset中分值在1-4之间的元素的个数：" + jedis.zcount("zset", 1, 4));
			System.out.println("key2的分值加上5：" + jedis.zincrby("zset", 5, "key2"));
			System.out.println("key3的分值加上4：" + jedis.zincrby("zset", 4, "key3"));
			System.out.println("zset中的所有元素：" + jedis.zrange("zset", 0, -1));

			System.out.println("");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
