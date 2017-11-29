package cn.rfidcer.rocketmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**   
* @Title: Product.java 
* @Package cn.rfidcer.rocketmq 
* @Description:rocketMQ消息生产者
* @author 席志明
* @Copyright Copyright
* @date 2016年8月10日 下午5:22:08 
* @version V1.0   
*/
public class Producer {

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	/**
	 * namesrv集群地址
	 */
	private String namesrvAddr;
    /**
     * ProducerGroup名称
     */
    private String producerGroup;
    /**
     * MQ的生产者
     */
    private DefaultMQProducer producer;
	/**获取namesrv集群地址
	 * @return the namesrv集群地址
	 */
	public String getNamesrvAddr() {
		return namesrvAddr;
	}
	/**设置namesrv集群地址
	 * @param namesrvAddr the namesrv集群地址 to set
	 */
	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}
	/**获取ProducerGroup名称
	 * @return the ProducerGroup名称
	 */
	public String getProducerGroup() {
		return producerGroup;
	}
	/**设置ProducerGroup名称
	 * @param producerGroup the ProducerGroup名称 to set
	 */
	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}
	/**获取MQ的生产者
	 * @return the MQ的生产者
	 */
	public DefaultMQProducer getProducer() {
		return producer;
	}
	/**设置MQ的生产者
	 * @param producer the MQ的生产者 to set
	 */
	public void setProducer(DefaultMQProducer producer) {
		this.producer = producer;
	}
    
    /**
     * 初始化Producer
     */
    public void init(){
    	producer=new DefaultMQProducer(producerGroup);
    	producer.setNamesrvAddr(namesrvAddr);
    	 producer.setInstanceName("Producer");
    	try {
			producer.start();
		} catch (MQClientException e) {
			logger.error(e.getMessage(),e);
		}
    }
    
    public SendResult send(Message message){
    	SendResult sendResult=null;
		try {
			sendResult = producer.send(message);
		} catch (MQClientException e) {
			logger.error(e.getMessage(),e);
		} catch (RemotingException e) {
			logger.error(e.getMessage(),e);
		} catch (MQBrokerException e) {
			logger.error(e.getMessage(),e);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(),e);
		}
    	return sendResult;
    }
    
}
