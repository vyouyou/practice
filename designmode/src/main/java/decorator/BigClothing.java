package decorator;

/**
 * @author qisy01
 * @create 18-10-17
 * @since 1.0.0
 */
public class BigClothing extends Clothing {
    Base base;

    public BigClothing(Base base) {
        this.base = base;
    }

    @Override
    public String getDescrible() {
        return base.getDescrible() + "has a bigclothin";
    }

    @Override
    public Double getCost() {
        return base.getCost() + 1.22;
    }
}
