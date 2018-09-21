package spring.beans;

import lombok.extern.java.Log;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;

@Log
@org.springframework.stereotype.Component
public class ComponentFactoryBean implements FactoryBean<Component> {
    @Override
    public Component getObject() throws Exception {
        log.info("i am factorybean");
        return new Component();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
