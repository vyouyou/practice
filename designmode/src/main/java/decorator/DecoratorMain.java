package decorator;

import lombok.extern.java.Log;

/**
 * @author qisy01
 * @create 18-10-17
 * @since 1.0.0
 */
@Log
public class DecoratorMain {
    public static void main(String[] args) {
        Customer customer = new Customer();
        BigClothing bigClothing = new BigClothing(customer);
        BigHat bigHat = new BigHat(bigClothing);
        log.info(bigHat.getDescrible() + ":" + bigHat.getCost());
    }
}
