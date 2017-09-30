package cc.fcsp;

import com.rabbitmq.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ReceiveLogsTopic {
    private static final Logger LOGGER= LogManager.getLogger(ReceiveLogsTopic.class);
    private static final String EXCHANGE_NAME = "exchange.direct.ivr.sign";
    private static final String ROUTING_KEY = "ivr_1";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);

//        for (String bindingKey : argv) {
//            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
//        }

        LOGGER.info(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                LOGGER.info(" [x] Received {} :{}",ROUTING_KEY,message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

}
