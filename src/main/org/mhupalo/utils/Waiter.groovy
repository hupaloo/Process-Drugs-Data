package main.org.mhupalo.utils

class Waiter {

    static void sleep(int seconds) {
        try {
            int ms = 1000 * seconds
            Thread.sleep(ms)
        } catch (InterruptedException e) {
            e.printStackTrace()
        }
    }

}
