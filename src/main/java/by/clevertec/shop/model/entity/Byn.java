package by.clevertec.shop.model.entity;

public class Byn implements Comparable<Byn> {
    private final int money;
    
    public Byn() {
        this(0);
    }

    public Byn(int money) {
        this.money = money;
    }

    public Byn(Byn byn) {
        this(byn.money);
    }

    public Byn multiplication(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(money * k, d));
    }

    public Byn round(RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(money, d));
    }

    public Byn add(Byn byn) {
        return new Byn(money + byn.money);
    }

    public Byn subtract(Byn byn) {
        return new Byn(money - byn.money);
    }

    public Byn multiplication(int num) {
        return new Byn(money * num);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Byn byn = (Byn) o;
        if (money != byn.money)
            return false;
        return money == byn.money;
    }

    @Override
    public int compareTo(Byn o) {
        return money - o.money;
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", money / 100, money % 100);
    }
}
