package decorator;

/**
 * @author qisy01
 * @create 18-10-17
 * @since 1.0.0
 */
public class BigHat extends Hat {
    Base base;

    public BigHat(Base base) {
        this.base = base;
    }

    @Override
    public String getDescrible() {
        return base.getDescrible() + " i am bighat";
    }

    @Override
    public Double getCost() {
        return base.getCost() + 2.00;
    }
}
