package spring.beans;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import spring.beans.BaseB;

@Log
public class BaseBImpl implements BaseB{

    @Override
    public void baseB() {
        log.info("i am b");
    }
}
