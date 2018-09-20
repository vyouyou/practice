package spring.event;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Lazy(value = false)
@Log
public class ListenAnno {
    public ListenAnno(){
        log.info("ListenAnno init");
    }

    @EventListener
    public void handlerEvent(BlackListNotifier blackListNotifier){
        log.info("i am ListenAnno");
    }
}
