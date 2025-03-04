package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int PAGE_MAX = 100;
        int[] processes = new int[PAGE_MAX];
        for (int i = 0; i < processes.length; i++) {
            processes[i] = -1;
        }
        userMemoryAllocation(processes);
    }
    public static void userMemoryAllocation(int[] processes) {
        Random random = new Random();
        int currentAddress = 2000;
        int currentProcess = 0;


    }

}
