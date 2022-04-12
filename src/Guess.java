//NAME: Daniel Peters
//Professor: Dr. Chen
//CLASS: Csci 433
//Date: April 21, 2021

import java.util.Scanner;

public class Guess {
	
	int guesses = 0;
	
	public static void main(String args[]) {
		
		if(args.length != 2) { 
			System.out.println("Must input 2 integers in order for program to work. Example:\n");
			System.out.println("java -jar Guess.jar k n\n\nWhere k is the number of chips you want and n is the desired set of integers from 0 to n (inclusive).");
			return;
		}
		
		int k = Integer.parseInt(args[0]);
		int n = Integer.parseInt(args[1]);
		
		int cost[][] = new int[k + 1][n + 1];
		int guesses[][] = new int[k + 1][n + 1];
		
		for(int i = 1; i <= n; i++) {
			cost[1][i] = i;
			guesses[1][i] = 1;
		}
		
		for(int i = 1; i <= k; i++) {
			cost[i][1] = 1;
			guesses[i][1] = 1;
		}
		
		for(int i = 2; i <= k; i++) {
			for(int j = 2; j <= n; j++) {
				cost[i][j] = n;
				for(int x = 1; x <= j; x++) {
					int y;
					if(cost[i - 1][x - 1] > cost[i][j - x]) {
						y = cost[i - 1][x - 1] + 1;
					}
					else {
						y = cost[i][j - x] + 1;
					}
					if (y >= cost[i][j]) {
						continue;
					}
					cost[i][j] = y;
					guesses[i][j] = x;
				}
			}
		}
		
		
		int row = k;
		int col = n;
		int guess = guesses[row][col];
		int z = 0;
		int q = 1;
		
		
		System.out.println("You chose " + k + " chips and the set of integers from 0 to " + n + ". In the worst case, I can find your number by asking " + cost[k][n] + " questions, at most.\n");
		
		System.out.println("Now, pick a number in your head! Ready? Here we go!");
		
		
		game(guesses, row, col, guess, z, q);
		
	}
	
	static void game(int guesses[][], int row, int col, int guess, int z, int q) {
		Scanner keyboard = new Scanner(System.in);
		
		if(guess == 0) {
			System.out.println("Got it! Your number is " + z + ".");
			System.out.println("Thanks for playing!");
			return;
		}
		
		System.out.println("Question #" + q + ": Is your number less than " + (guess + z) + "? Yes or No?");
		String answer = keyboard.nextLine();
		
		if(answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
			row--;
			col = guess - 1;
		}
		else {
			col = col - guess;
			z = z + guess;
		}
		System.out.println("You have " + row + " chips left.");
		q++;
		guess = guesses[row][col];
		game(guesses, row, col, guess, z, q);
	}
}
