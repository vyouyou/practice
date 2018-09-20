package spring.event;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class BlackListNotifier implements ApplicationListener<BlackListEvent> {
    @Override
   public void onApplicationEvent(BlackListEvent event) {
       log.info("i am application event " + event.getAddress());
  }
}
