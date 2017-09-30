package cc.fcsp;

import com.rabbitmq.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmitLogTopic {
    private static final Logger LOGGER= LogManager.getLogger(EmitLogTopic.class);

    private static final String EXCHANGE_NAME = "exchange.direct.ivr.sign";
    private static final String ROUTING_KEY="ivr_1";

    public static void main(String[] argv) {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            factory.setUsername("admin");
            factory.setPassword("admin");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true);


            while (true){
                String m=System.currentTimeMillis()+"--";
                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, m.getBytes("UTF-8"));
                LOGGER.info(" [x] Sent {}: {}",ROUTING_KEY,m);
                Thread.sleep(1000);
            }

        }
        catch  (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ignore) {
                }
            }
        }
    }

//    private static String getRouting(String[] strings){
//        if (strings.length < 1)
//            return "anonymous.info";
//        return strings[0];
//    }
//
//    private static String getMessage(String[] strings){
//        if (strings.length < 2)
//            return "Hello World!";
//        return joinStrings(strings, " ", 1);
//    }
//
//    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
//        int length = strings.length;
//        if (length == 0 ) return "";
//        if (length < startIndex ) return "";
//        StringBuilder words = new StringBuilder(strings[startIndex]);
//        for (int i = startIndex + 1; i < length; i++) {
//            words.append(delimiter).append(strings[i]);
//        }
//        return words.toString();
//    }
}
