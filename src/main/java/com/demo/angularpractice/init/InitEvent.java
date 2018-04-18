package com.demo.angularpractice.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class InitEvent {
    private final CountDownLatch latch = new CountDownLatch(3);
//    @Autowired
//    private KafkaTemplate<String, String> template;

    public void loadEvent() throws InterruptedException {
//        this.template.send("myTopic", "foo1");
//        this.template.send("myTopic", "foo2");
//        this.template.send("myTopic", "foo3");
//        latch.await(60, TimeUnit.SECONDS);
        log.info("All received");
    }

//    @KafkaListener(topics = "myTopic")
//    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
//        log.info(cr.toString());
//        latch.countDown();
//    }
}
