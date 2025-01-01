package test;

import java.util.function.BinaryOperator;

public class BinOpAgent implements Agent {
    private Node agent;
    private Topic topicA;
    private Topic topicB;
    private Topic topicR;
    private Double left;
    private Double right;
    private BinaryOperator<Double> operator;


    public BinOpAgent(String agent,String topicA, String topicB, String topicR , BinaryOperator<Double> operator ){
        this.agent = new Node(agent);
        this.topicA = new Topic(topicA);
        this.topicB = new Topic(topicB);
        this.topicR = new Topic(topicR);
        this.operator = operator;

        Topic a = new Topic(topicA);
        a.subscribe((Agent) this.agent);
        Topic b = new Topic(topicB);
        b.subscribe((Agent) this.agent);
        Topic r = new Topic(topicR);
        r.addPublisher((Agent) this.agent);

    }


    @Override
    public String getName() {
        return "BinOpAgent";
    }

    @Override
    public void reset() {
        this.left = 0.0;
        this.right = 0.0;

    }

    @Override
    public void callback(String topic, Message msg) {
       if( Double.isNaN(msg.asDouble))
           return;

       if (topic.equals(this.topicA.name)){
           left = msg.asDouble;
       }
       else if (topic.equals(this.topicB.name)){
           right = msg.asDouble;
       }
       double test=operator.apply(left, right);
       this.topicR.publish(new Message(Double.toString(test)));

    }

    @Override
    public void close() {
        left=null;
        right=null;
        topicA.unsubscribe((Agent) this.agent);
        topicB.unsubscribe((Agent) this.agent);
        topicR.unsubscribe((Agent) this.agent);
        topicA = null;
        topicB = null;
        topicR = null;
    }
}
