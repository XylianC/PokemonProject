package com.xylian.pokemon.zOld;

import java.util.Scanner;

public class OldMain {
    public static void main(String[] args) {
      /*  Scanner sc = new Scanner(System.in);


//        System.out.println("Welcome to the game!");
//        System.out.println("Have you played before?");
//
//        if(getYesOrNo()) {
//
//        } else {
//            return;
//        }


        Player player = SaveService.loadPlayer();

        if(player != null && player.getName() != null) {
            System.out.println("Welcome back; " + player.getName() + "!");
            System.out.println("The starter you chose was: " + player.getStarter());

            System.out.printf("You currently have %d €%n", player.getMoney());

            System.out.println("How much money would you like to add?");
            System.out.print(">: ");
            int amount = sc.nextInt();
            sc.nextLine();

            player.addMoney(amount);
            SaveService.savePlayer(player);

        } else {
            System.out.println("Welcome to the world of pokemon!");

            System.out.println("Can you tell me your name?");
            System.out.print(">: ");
            String playerName = sc.next().trim();

            player = new Player(playerName);

            System.out.printf("Nice to meet you %s!", playerName);

            System.out.printf("%n%nPlease select your starter%n");
            System.out.printf("1: Bulbasaur%n2: Charmander%n3: Squirtle%n");
            System.out.print(">: ");

            int selection = sc.nextInt();
            sc.nextLine();
            String starter;

            switch (selection) {
                case 1:
                    starter = "Bulbasaur";
                    break;
                case 2:
                    starter = "Charmander";
                    break;
                case 3:
                    starter = "Squirtle";
                    break;
                default:
                    System.out.println("Invalid choice, defaulting to Bulbasaur");
                    starter = "Bulbasaur";
            }

            player.writeStarter(starter);
            player.addMoney(0);
            System.out.printf("You chose %s! What a great choice!", starter);

            SaveService.savePlayer(player);
        }
        sc.close();

       */
    }

    private static boolean getYesOrNo() {
        Scanner sk = new Scanner(System.in);

        String answer;
        answer = sk.nextLine();

        boolean output;

        if(answer.equalsIgnoreCase("y")) {
            output = true;
        } else {
            output = false;
        }

        sk.close();
        return output;
    }


}