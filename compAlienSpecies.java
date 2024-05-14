package Project;

import java.util.Scanner;

public class compAlienSpecies {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the size of the population: ");
        int size = input.nextInt();
        System.out.println("");

        System.out.println("Simulating compAlienSpecies...");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

        char[] geneticSequence = new char[128];
        int[] compAlienID = new int[size];
        String[] compAlienSex = new String[size];
        int[] compAlienHealth = new int[size];
        compAlien(geneticSequence, compAlienID, compAlienSex, compAlienHealth);
        System.out.println("");
        System.out.println("Simulation Complete");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

        int option;

        do {
            System.out.println("Chose an option:");
            System.out.println("(1) Mate two compAliens");
            System.out.println("(2) Randomly mate a set of compAliens");
            System.out.println("(3) Show statistics");
            System.out.println("(4) Simulate battle between compAliens");
            System.out.println("(5) Make a compAlien apply for a job");
            System.out.println("(0) Exit the program");
            System.out.println("");
            System.out.print("Enter an option: ");
            option = input.nextInt();
            System.out.println("");

            switch (option) {
                case 0:
                    break;
                case 1:
                case 2:
                    matingMethod(compAlienSex, compAlienHealth, option);
                    break;
                case 3:
                    printStatistics(compAlienSex, compAlienHealth);
                    break;
                case 4:
                    simulateBattle(compAlienHealth, compAlienSex);
                    break;
                case 5:
                    getAJob(compAlienHealth, compAlienSex);
                    break;
                default:
                    System.out.println("Invalid Index");
                    System.out.println("");
                    break;
            }

        } while (option != 0);
    }

    public static void compAlien(char[] geneticSequence, int[] compAlienID, String[] compAlienSex, int[] compAlienHealth) {

        for (int i = 0; i < compAlienHealth.length; i++) {
            for (int j = 0; j < geneticSequence.length; j++) {
                char previousLetter = ' ';
                for (int k = 0; k < geneticSequence.length - 1; k++) {
                    double probability = Math.random();
                    switch (previousLetter) {
                        case 'C':
                            if (probability < 0.40) {
                                geneticSequence[k] = 'S';
                            } else if (probability > 0.40 || probability < 0.70) {
                                geneticSequence[k] = 'C';
                            } else {
                                geneticSequence[k] = 'E';
                            }
                            break;
                        case 'S':
                            if (probability < 0.40) {
                                geneticSequence[k] = 'E';
                            } else if (probability > 0.40 || probability < 0.70) {
                                geneticSequence[k] = 'C';
                            } else {
                                geneticSequence[k] = 'S';
                            }
                            break;

                        default:
                            if (probability < 0.25) {
                                geneticSequence[k] = 'C';
                            } else if (probability > 0.25 || probability < 0.50) {
                                geneticSequence[k] = 'S';
                            } else {
                                geneticSequence[k] = 'E';
                            }
                            break;
                    }
                    previousLetter = geneticSequence[k];
                }
                double probability = Math.random();
                if (probability < 0.50) {
                    geneticSequence[127] = 'S';
                } else if (probability > 0.50 || probability < 0.75) {
                    geneticSequence[127] = 'C';
                } else {
                    geneticSequence[127] = 'E';
                }

            }
            compAlienHealth[i] = healthCalc(geneticSequence);
            compAlienSex[i] = genderCalc(geneticSequence);
        }
        for (int i = 0; i < compAlienID.length; i++) {
            compAlienID[i] = i + 1;
        }

        for (int i = 0; i < compAlienID.length; i++) {
            System.out.println("ID: " + compAlienID[i] + ", " + compAlienSex[i] + ", Health: " + compAlienHealth[i]);
        }

    }

    public static int healthCalc(char[] geneticSequence) {

        int health = 0;

        for (int i = 0; i < geneticSequence.length - 2; i++) {
            if (geneticSequence[i] == 'C') {
                i++;
                if (geneticSequence[i] == 'S') {
                    i++;
                    if (geneticSequence[i] == 'E') {
                        health++;
                    }
                } else {
                    i--;
                }
            }
        }

        return health;
    }

    public static String genderCalc(char[] geneticSequence) {
        if (geneticSequence[127] == 'S') {
            return "Male";
        } else {
            return "Female";
        }
    }

    public static void matingMethod(String[] compAlienSex, int[] compAlienHealth, int ans) {
        Scanner input = new Scanner(System.in);

        int condition = 1, id = 0, id2 = 0;

        switch (ans) {
            case 1:
                System.out.println("Mating two compAliens");
                System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
                System.out.print("Enter ID of first compAlien: ");
                id = input.nextInt();
                while (id < 0 || id > compAlienSex.length) {
                    System.out.println("Invalid ID");
                    System.out.print("Enter ID of first compAlien again:");
                    id = input.nextInt();

                }
                System.out.print("Enter ID of second compAlien: ");
                id2 = input.nextInt();
                while (id2 < 0 || id2 > compAlienSex.length) {
                    System.out.println("Invalid ID");
                    System.out.print("Enter ID of second compAlien again:");
                    id2 = input.nextInt();

                }
                System.out.println("");
                break;
            case 2:
                System.out.print("Enter number of pairs to reproduce: ");
                condition = input.nextInt();
                while (condition < 0) {
                    System.out.println("Invalid Syntax, can't be negative");
                    System.out.print("Enter number of pairs to reproduce: ");
                    condition = input.nextInt();
                }
                System.out.println("");
                id = (int) (Math.random() * compAlienSex.length);
                id2 = (int) (Math.random() * compAlienSex.length);
        }
        for (int i = 0; i < condition; i++) {
            if (condition > 1) {
                id = (int) (Math.random() * compAlienSex.length) + 1;
                id2 = (int) (Math.random() * compAlienSex.length) + 1;
            }
            int probability = (int) (((compAlienHealth[id - 1] + compAlienHealth[id2 - 1]) / 36.0) * 100);
            int rand = (int) (Math.random() * 100) + 1;
            String message;
            if (compAlienSex[id - 1].equals(compAlienSex[id2 - 1])) {
                message = "no mate";
            } else {
                if (rand > probability) {
                    message = "Offspring chance " + probability + "%. They have no offspring";
                } else {
                    message = "Offspring chance " + probability + "%. They have 1 offspring :)";
                }
            }
            System.out.println("compAlien " + id + "(" + compAlienSex[id - 1].charAt(0) + ")" + " and " + id2 + "(" + compAlienSex[id2 - 1].charAt(0) + ")" + " Mate: " + message);
            System.out.println("");
        }
    }

    public static void printStatistics(String[] compAlienSex, int[] compAlienHealth) {
        Scanner input = new Scanner(System.in);
        int maleSumHealth = 0, femaleSumHealth = 0, males = 0, females = 0, max = 0, min = 20, indexMax = 0, indexMin = 0;
        System.out.println("compAlien Population Statistics");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        for (int i = 0; i < compAlienSex.length; i++) {
            if (compAlienSex[i].equals("Male")) {
                males++;
                maleSumHealth += compAlienHealth[i];
            } else {
                femaleSumHealth += compAlienHealth[i];
                females++;
            }
            if (compAlienHealth[i] > max) {
                max = compAlienHealth[i];
                indexMax = i + 1;
            }
            if (compAlienHealth[i] < min) {
                min = compAlienHealth[i];
                indexMin = i + 1;
            }
        }
        System.out.println("Female population = " + (int) ((females / (double) compAlienSex.length) * 100) + "%");
        System.out.println("Male population = " + (int) ((males / (double) compAlienSex.length) * 100) + "%");
        System.out.println("Average Health: " + (int) ((maleSumHealth + femaleSumHealth) / (double) (males + females)));
        System.out.println("Average health of Males: " + (int) (maleSumHealth / (double) males));
        System.out.println("Average health of Females: " + (int) (femaleSumHealth / (double) females));
        System.out.println("Healthiest compAlien is: " + indexMax + "(" + compAlienSex[indexMax - 1] + ")" + "with " + max + " healh");
        System.out.println("Least healthy compAlien is: " + indexMin + "(" + compAlienSex[indexMin - 1] + ")" + "with " + min + " healh");
        System.out.print("Enter a health threshold between [2 and 14]: ");
        int threshold = input.nextInt();
        while (threshold < 2 || threshold > 14) {
            System.out.println("Invalid Syntax");
            System.out.print("Enter another threshold: ");
            threshold = input.nextInt();
        }
        int countThreshold = 0;
        for (int i = 0; i < compAlienHealth.length; i++) {
            if (compAlienHealth[i] >= threshold) {
                countThreshold++;
            }

        }
        System.out.println((int) ((countThreshold / (double) compAlienHealth.length) * 100) + "% of compAlien population have a health of " + threshold + " or higher");
        System.out.println("");
    }

    public static void simulateBattle(int[] compAlienHealth, String[] compAlienSex) {
        Scanner input = new Scanner(System.in);
        int condition = 1, winner = 0, id = 0, id2 = 0;
        System.out.println("Simulating Battle");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        System.out.println("(1) Chose two compAliens to do battle");
        System.out.println("(2) Simualte battle between a number of pairs of compAliens");
        System.out.println("");
        System.out.print("Choose option:");
        int choice = input.nextInt();
        System.out.println("");
        while (choice < 1 || choice > 2) {
            System.out.println("Invalid Sytanx");
            System.out.println("");
            System.out.print("Choose option again: ");
            choice = input.nextInt();
            System.out.println("");
        }
        switch (choice) {
            case 1:
                System.out.print("Enter ID of first compAlien: ");
                id = input.nextInt();
                while (id < 0 || id > compAlienSex.length) {
                    System.out.println("Invalid ID");
                    System.out.print("Enter ID of first compAlien again:");
                    id = input.nextInt();
                }
                System.out.print("Enter ID of second compAlien: ");
                id2 = input.nextInt();
                while (id2 < 0 || id2 > compAlienSex.length) {
                    System.out.println("Invalid ID");
                    System.out.print("Enter ID of second compAlien again:");
                    id2 = input.nextInt();
                }
                System.out.println("");
                break;
            case 2:
                System.out.print("Enter number of pairs to fight: ");
                condition = input.nextInt();
                while (condition < 0) {
                    System.out.println("Invalid Syntax, can't be negative");
                    System.out.print("Enter number of pairs to reproduce: ");
                    condition = input.nextInt();
                }
                id = (int) (Math.random() * compAlienSex.length);
                id2 = (int) (Math.random() * compAlienSex.length);
                System.out.println("");
                break;
        }
        for (int i = 0; i < condition; i++) {
            if (condition > 1) {
                id = (int) (Math.random() * compAlienSex.length) + 1;
                id2 = (int) (Math.random() * compAlienSex.length) + 1;
            }
            int health1 = compAlienHealth[id - 1];
            int health2 = compAlienHealth[id2 - 1];
            System.out.println("compAlien " + id + "(" + compAlienSex[id - 1] + ") " + "and " + id2 + "(" + compAlienSex[id2 - 1] + ")" + "are about to engage in a fight");
            System.out.println("");
            System.out.println("Initial Health of " + id + "(" + compAlienSex[id - 1] + "):" + health1);
            System.out.println("Initial Health of " + id2 + "(" + compAlienSex[id2 - 1] + "):" + health2);
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            for (int j = 0; j < (compAlienHealth[id - 1] + compAlienHealth[id2 - 1]); j++) {
                int rand = (int) (Math.random() * 100) + 1;
                if (rand > 50) {
                    System.out.println("compAlien  " + id2 + "(" + compAlienSex[id2 - 1] + ") " + "does damage to " + id + "(" + compAlienSex[id - 1] + ")");
                    System.out.println("");
                    health1--;
                    System.out.println("Current Health of " + id + "(" + compAlienSex[id - 1] + ") " + " is " + health1);
                    System.out.println("Current Health of " + id2 + "(" + compAlienSex[id2 - 1] + ") " + " is " + health2);
                    System.out.println("");
                    if (health1 == 0) {
                        winner = id2;
                        break;
                    }
                } else {
                    System.out.println("compAlien " + id + "(" + compAlienSex[id - 1] + ") " + "does damage to " + id2 + "(" + compAlienSex[id2 - 1] + ")");
                    System.out.println("");
                    health2--;
                    System.out.println("Current Health of " + id + "(" + compAlienSex[id - 1] + ") " + " is " + health1);
                    System.out.println("Current Health of " + id2 + "(" + compAlienSex[id2 - 1] + ") " + " is " + health2);
                    System.out.println("");
                    if (health2 == 0) {
                        winner = id;
                        break;
                    }
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            String message = "The winner of the fight is:  ID: " + winner + " (" + compAlienSex[winner - 1] + ") ";
            System.out.println("compAlien " + id + "(" + compAlienSex[id - 1].charAt(0) + ")" + " and " + id2 + "(" + compAlienSex[id2 - 1].charAt(0) + ")" + " Fight: " + message);
            System.out.println("");
        }
    }

    public static void getAJob(int[] compAlienHealth, String[] compAlienSex) {
        Scanner input = new Scanner(System.in);

        boolean job = true;
        int sum = 0;
        for (int i = 0; i < compAlienHealth.length; i++) {
            sum += compAlienHealth[i];
        }
        System.out.print("Pick a compAlien to be Employed: ");
        int alien = input.nextInt();
        System.out.println("");

        if (compAlienHealth[alien - 1] > sum / (double) compAlienHealth.length) {
            int max = compAlienHealth[alien - 1];
            for (int i = 0; i < 5; i++) {
                if (compAlienHealth[i] > max) {
                    System.out.println("Eventhough compAlien " + alien + " (" + compAlienSex[alien - 1] + ") " + " was qualified for the job, his/her competitors proved to be  more valuable, therefore they didn't get the job :(");
                    job = false;
                    break;
                }
            }
            if (job) {
                System.out.println("Fortunanlty, compAlien " + alien + " (" + compAlienSex[alien - 1] + ") " + " is qualitified for the job and is outstanding amongst his/her competitors, therefore they got a job :)");
            }
        } else {
            System.out.println("Unfortunantly, compAlien " + alien + " (" + compAlienSex[alien - 1] + ") " + " is not qualified for a job due to his health problems");
        }
        System.out.println("");

    }
}
