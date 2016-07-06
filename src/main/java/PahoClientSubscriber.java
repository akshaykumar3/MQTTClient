import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by akshay.kumar1 on 06/07/16.
 */
public class PahoClientSubscriber implements MqttCallback{

    private static String topic = "Akshay_MQTT";
    private static String broker = "tcp://iot.eclipse.org:1883";
    private static String clientId = "JavaSample";

    private static MqttClient client;

    public static void main(String[] args) {
        new PahoClientSubscriber().subscribe();
    }

    public void subscribe() {
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            client = new MqttClient(broker, clientId, persistence);
            client.connect();
            client.setCallback(this);
            client.subscribe(topic);
            MqttMessage message = new MqttMessage();
            message.setPayload("{\"message\" : \"true\"}".getBytes());
            client.publish(topic, message);
        } catch(MqttException e) {
            System.out.println("reason "+e.getReasonCode());
            System.out.println("msg "+e.getMessage());
            System.out.println("loc "+e.getLocalizedMessage());
            System.out.println("cause "+e.getCause());
            System.out.println("excep "+e);
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        // TODO Auto-generated method stub

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println(message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub

    }
}
