package effectivejava;

import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class cp30 {
    public static void main(String[] args) {
        log.info("" + Planet.MOON.surfaceGravity);

        for (Operation operation:Operation.values()){
            System.out.printf("%f %s %f = %f%n",
                    1.2,operation.symbol,2.0,operation.apply(1.2,2.0));
        }
    }

    @Getter
    public enum Planet {
        MERCURY(3.30e+23, 2.436e6),
        MOON(1.e+10, 1.5e5);

        private final double mass;
        private final double radius;
        private final double surfaceGravity;

        Planet(double mass, double radius) {
            this.mass = mass;
            this.radius = radius;
            surfaceGravity = 11 * mass / (radius * radius);
        }
    }

    public enum Operation {
        PLUS("+") {
            double apply(double x, double y) {
                return x + y;
            }
        },
        MINUS("-") {
            double apply(double x, double y) {
                return x - y;
            }
        },
        TIMES("*") {
            double apply(double x, double y) {
                return x * y;
            }
        },
        DIVIDE("/") {
            double apply(double x, double y) {
                return x / y;
            }
        };

        @Getter
        private String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }


        abstract double apply(double x, double y);
    }
}
