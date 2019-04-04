package com.cloud.ftl.ftlbasic.redis;

import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisKeyspaceNotifier {
	Logger log = Logger.getLogger(RedisKeyspaceNotifier.class);
	private JedisConnectionFactory connectionFactory;
	private MessageListener listener;
	private Thread subThread;
	private Boolean needListener;
	private JedisConnection conn;
	private static String CHANNEL_PATTERN = "__keyevent*__:expired";

	public void init() {
		if (!needListener) {
			return;
		}
		log.info("RedisNotifer starting");
		RedisSubScriber sub = new RedisSubScriber(listener);
		subThread = new Thread(sub, "Redis SubScriber");
		subThread.start();
		log.info("RedisNotifer started");
	}

	public void cleanup() {
		if (!needListener) {
			return;
		}
		log.info("RedisNotifer stoping");
		conn.close();
		log.info("RedisNotifer stoped");
	}

	private class RedisSubScriber implements Runnable {
		private MessageListener listener;

		public RedisSubScriber(MessageListener listener) {
			this.listener = listener;
		}

		public void run() {
			conn = (JedisConnection) connectionFactory.getConnection();
			StringRedisSerializer strSerial = new StringRedisSerializer();
			conn.pSubscribe(listener, strSerial.serialize(CHANNEL_PATTERN));
		}
	}

	public JedisConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(JedisConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public MessageListener getListener() {
		return listener;
	}

	public void setListener(MessageListener listener) {
		this.listener = listener;
	}

	public Boolean getNeedListener() {
		return needListener;
	}

	public void setNeedListener(Boolean needListener) {
		this.needListener = needListener;
	}

}
