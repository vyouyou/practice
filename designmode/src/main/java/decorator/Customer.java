package decorator;

/**
 * @author qisy01
 * @create 18-10-17
 * @since 1.0.0
 */
public class Customer implements Base {
    @Override
    public String getDescrible() {
        return "i am customer";
    }

    @Override
    public Double getCost() {
        return 0.00;
    }
}
