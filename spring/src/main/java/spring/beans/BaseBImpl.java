package spring.beans;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import spring.beans.BaseB;

@Log
public class BaseBImpl implements BaseB {
    public void sayIam(){
        log.info("i am b");
    }
}
