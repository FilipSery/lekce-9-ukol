package com.engeto.ukol9;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        int pocetFilozofu = 10;
	    Lock[] lock=new ReentrantLock[pocetFilozofu];

        for(int i=0;i<10;i++) {
            lock[i]=new ReentrantLock();
        }
        Filozofove[] filozofove = new Filozofove[pocetFilozofu];
        for (int i=0;i<filozofove.length;i++) {
            int cisloFilozofa = i+1;
            Lock lVidlicka = lock[(i+1) % lock.length];
            Lock pVidlicka = lock[i];
            if (i == filozofove.length-1) {
                filozofove[i] = new Filozofove(cisloFilozofa, pVidlicka, lVidlicka);
            } else {
                filozofove[i] = new Filozofove(cisloFilozofa, lVidlicka, pVidlicka);
            }
            Thread thread = new Thread(filozofove[i], "Filozof číslo: "+cisloFilozofa);
            thread.start();
        }

    }
}
