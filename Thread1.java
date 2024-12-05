package task2;

import java.util.Arrays;

public class Thread1 extends java.lang.Thread {
    int[] a;
    int b;

    Thread1(int[] a) {
        this.a = a;
        b = -10000;
    }

    @Override
    public void run() {
        for(int i = 0; i < a.length; i++) {
            if(b < a[i]) {
                b = a[i];
            }
        }
    }

    public int getB() {
        return b;
    }
}

