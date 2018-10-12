package effectivejava;

/**
 * 使用匿名内部类，
 * 生成策略，比如比较的策略
 */
public class cp21 {
    public static void main(String[] args) {
        StrategyClass strategyClass = new StrategyClass(4,3);
        strategyClass.cmp(new StrategyClass.Compare() {
            @Override
            public boolean isBigger(int a, int b) {
                return a>b;
            }
        });
    }

    public static class StrategyClass {
        final int a;
        final int b;

        public StrategyClass(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public boolean cmp(Compare compare){
            return compare.isBigger(a,b);
        }

        private interface Compare{
            boolean isBigger(int a,int b);
        }
    }
}
