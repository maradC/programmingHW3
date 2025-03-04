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
        int currentPage = 0;

        int PAGE_MAX = processes.length;
        int[] processIds = new int[PAGE_MAX];
        int[] startingAddresses = new int[PAGE_MAX];
        int[] processSizes = new int[PAGE_MAX];
        int[] unusedSpaces = new int[PAGE_MAX];
        int processCount = 0;

        while (currentPage < processes.length) {
            int processId = processCount;
            int randomNumber = random.nextInt(30) + 1;
            int processSize = randomNumber * 80;

            int numMemPages = (int) Math.ceil((double) processSize / 160);

            if (currentPage + numMemPages > processes.length) {
                break;
            }

            int unusedMemSpace = (numMemPages * 160) - processSize;

            processIds[processCount] = processId;
            startingAddresses[processCount] = currentAddress;
            processSizes[processCount] = processSize;
            unusedSpaces[processCount] = unusedMemSpace;

            for (int j = 0; j < numMemPages; j++) {
                processes[currentPage] = processId;
                currentPage++;
            }

            currentAddress += (numMemPages * 160);
            processCount++;
        }

        System.out.println("Summary Report:");
        System.out.printf("%-12s %-28s %-30s %-20s%n",
                "Process Id", "Starting Memory Address", "Size of the Process MB", "Unused Space MB");
        System.out.println("---------------------------------------------------------------------");

        for (int i = 0; i < processCount; i++) {
            System.out.printf("%-12d %-28d %-30d %-20d%n",
                    processIds[i], startingAddresses[i], processSizes[i], unusedSpaces[i]);
        }

        System.out.println("\nMemory Usage Statistics:");
        System.out.println("Total Pages: " + processes.length);
        System.out.println("Pages Used: " + currentPage);
        System.out.println("Pages Free: " + (processes.length - currentPage));

        int totalAllocated = 0;
        int totalUsed = 0;
        for (int i = 0; i < processCount; i++) {
            totalAllocated += (processSizes[i] + unusedSpaces[i]);
            totalUsed += processSizes[i];
        }

        double memoryEfficiency = (double) totalUsed / totalAllocated * 100;
        System.out.printf("Memory Utilization Efficiency: %.2f%%\n", memoryEfficiency);
    }
}