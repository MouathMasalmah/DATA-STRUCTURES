package application;

import java.util.Comparator;

public class MartyrCompareByAge implements Comparator<Martyr> {
    @Override
    public int compare(Martyr m1, Martyr m2) {
        return m1.getAge() - m2.getAge();
    }
}