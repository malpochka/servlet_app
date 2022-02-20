package by.clevertec.shop.model.entity;

public enum RoundMethod {
    FLOOR {
        double roundFunction(double d) {
            return Math.floor(d);
        }
    },
    ROUND {
        double roundFunction(double d) {
            return Math.round(d);
        }
    },
    CEIL {
        double roundFunction(double d) {
            return Math.ceil(d);
        }
    };

    abstract double roundFunction(double value);

    public int round(double roundedValue, int d) {
        int[] tenPowD = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
        int tenPow = tenPowD[d];
        int result = (int) roundFunction((roundedValue / tenPow) * tenPow);
        return result;
    }
}
