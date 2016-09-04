package com.connor2.kafka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class kafkaProductor {

	private Producer<String, String> inner;

	public kafkaProductor() throws Exception {
		Properties properties = new Properties();
		properties.put("metadata.broker.list", "192.168.1.105:9092,192.168.1.105:9093,192.168.1.105:9094");
		properties.put("producer.type", "sync");
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		 
		
		ProducerConfig config = new ProducerConfig(properties);
		inner = new Producer<String, String>(config);
	}

	public void send(String topicName, String message) {
		if (topicName == null || message == null) {
			return;
		}
		KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, message);// 如果具有多个partitions,请使用new
																								// KeyedMessage(String
																								// topicName,K
																								// key,V
																								// value).
		inner.send(km);
	}

	public void send(String topicName, Collection<String> messages) {
		if (topicName == null || messages == null) {
			return;
		}
		if (messages.isEmpty()) {
			return;
		}
		List<KeyedMessage<String, String>> kms = new ArrayList<KeyedMessage<String, String>>();
		for (String entry : messages) {
			KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, entry);
			kms.add(km);
		}
		inner.send(kms);
	}

	public void close() {
		inner.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		kafkaProductor producer = null;
		try {
			producer = new kafkaProductor();
			int i = 0;
			while (true) {
				producer.send("test-topic", "this is a sample" + i);
				i++;
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (producer != null) {
				producer.close();
			}
		}
	}

}