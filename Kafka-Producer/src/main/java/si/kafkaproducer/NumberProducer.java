package si.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NumberProducer {
    private static final String topic = "input-topic";
    private static final Logger logger = LoggerFactory.getLogger(NumberProducer.class);
    private long number = 0;

    @Autowired
    private KafkaTemplate<String, Long> template;

    @Scheduled(fixedRate = 2000)
    public void produce() {
        this.template.sendDefault(number++);
        // logger.info(String.format("### -> Producer sends message -> %s", message));
        logger.info("### Producer sends number [{}]", number);
        template.flush();
    }
}
