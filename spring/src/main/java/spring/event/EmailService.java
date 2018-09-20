package spring.event;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import spring.controller.ContextUtils;

import javax.annotation.Resource;

/**
 * 事件生产者
 */
@Log
public class EmailService implements ApplicationEventPublisherAware {
    @Resource
    private ApplicationContext applicationContext;

    private static ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        if (applicationEventPublisher == null){
            log.info("=========is null");
        }else{
            log.info("=========is not null" );
        }
        this.applicationEventPublisher = applicationEventPublisher;

    }

    public void sendEmail(String address){
        log.info("======send");
        ApplicationContext context = ContextUtils.getContext();
        if (applicationEventPublisher==null){
            log.info("======send is null");
        }
        applicationEventPublisher.publishEvent(new BlackListEvent(this,address));
//        applicationEventPublisher.publishEvent(new BlackListEvent(this,address));
    }
}
