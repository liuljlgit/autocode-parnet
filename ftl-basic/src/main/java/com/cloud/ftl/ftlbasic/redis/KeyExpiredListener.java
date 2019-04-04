package com.cloud.ftl.ftlbasic.redis;

import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Redis Key 事件监听器，收到事件监听后，派发给业务线程去处理
 * 
 * @author chenzhm
 *
 */
@Component
public class KeyExpiredListener implements MessageListener {
	Logger logger = Logger.getLogger(KeyExpiredListener.class);

	@Override
	public void onMessage(Message message, byte[] pattern) {
		//要catch异常,否则redis的事件监听器线程会挂掉,导致以后的监听事件不起效
		String msgBody = null;
		try {
			StringRedisSerializer strSerial = new StringRedisSerializer();
			msgBody = strSerial.deserialize(message.getBody());
			String channel = strSerial.deserialize(message.getChannel());
			String strPatt = strSerial.deserialize(pattern);
			logger.debug("onPMessage pattern " + strPatt+ " " + channel + " " + msgBody);
			handleExpire(msgBody);
		} catch (Exception e) {
			logger.error(String.format("Redis KeyExpireListener onMessage() occurred an error, cause by %s, key: %s", e.getMessage(), msgBody));
		}
	}

	private void handleExpire(String msgBody) {
		//dosomething
	}
}
