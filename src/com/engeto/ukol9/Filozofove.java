package com.engeto.ukol9;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Filozofove implements Runnable {
    private int cisloFilozofa;
    private Lock pVidlicka;
    private Lock lVidlicka;
    private Random cislo = new Random();
    private final int bound = 10;

    public Filozofove(int cisloFilozofa, Lock pVidlicka, Lock lVidlicka) {
        this.cisloFilozofa = cisloFilozofa;
        this.pVidlicka = pVidlicka;
        this.lVidlicka = lVidlicka;
    }

    public void stav1() throws InterruptedException {
        System.out.println("Filozof "+ cisloFilozofa+" přemýšlí.");
        System.out.flush();
        Thread.sleep(cislo.nextInt(bound));
    }
    public void stav2() {
        lVidlicka.lock();
        System.out.println("Filozof "+ cisloFilozofa+" zvedl levou vidličku.");
        System.out.flush();
    }
    public void stav3() {
        pVidlicka.lock();
        System.out.println("Filozof "+ cisloFilozofa+" zvedl pravou vidličku.");
        System.out.flush();
    }
    public void stav4() throws InterruptedException {
        System.out.println("Filozof "+ cisloFilozofa+" jí.");
        System.out.flush();
        Thread.sleep(cislo.nextInt(bound));
    }
    public void stav5() {
        System.out.println("Filozof "+ cisloFilozofa+" položí levou vidličku.");
        lVidlicka.unlock();
        System.out.flush();
    }
    public void stav6() {
        System.out.println("Filozof "+ cisloFilozofa+" položí pravou vidličku.");
        pVidlicka.unlock();
        System.out.flush();

    }


    @Override
    public void run() {
        try {
            for (int i=1;i<=10000;i++) {
            stav1();
            stav2();
            stav3();
            stav4();
            stav5();
            stav6();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
