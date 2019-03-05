
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		play();
	}
	
	
	public static char[][] createBoard(int n){
		//create a new 2D square array of side length n and input a blank space into every address of it. Then return the array 
		char [][]a = new char [n][n]; 
		for (int i=0; i<a.length; i++){
			for (int j=0; j<a[i].length; j++){
				a[i][j]= ' ';
			}
		}
		return a; 
	}
	
	public static void displayBoard(char[][]c){
		//a method to display the board 
		String middleLine = (helperDisplayBoard(c.length)); //call helper method to get the + - + - between every line 
		//create the board 
		System.out.println(middleLine); 
		for (int i=0; i<c.length; i++){
			System.out.print("| ");
			for(int j=0; j<c[i].length;j++){
				System.out.print(c[i][j]+" | ");
				
			}
			System.out.println();
			System.out.println(middleLine);
		}
	}
	
	public static String helperDisplayBoard(int n){
		//a helper method to get the + - + - between every line on the board 
		String a = ""; 
		for(int i=0; i<2*n+1;i++){
			if (i%2==0){
				a += '+';
			}else {
				a += '-';		
		}
			a+=' ';
	}
		return a;
	}

	public static boolean writeOnBoard(char [][]b,char o,int x, int y){
		//a method to put a move on the board 
		boolean validMove=false; 
			try{
				if(b[x][y]==' '){ //will only put the move on the board if it is blank
					b [x][y] = o;
					validMove=true; 
			}else{
				throw new IllegalArgumentException();//will throw this exception if the space is taken
				}
		}catch(ArrayIndexOutOfBoundsException e){ //will catch exception if the space does not exist
			System.out.println("This space does not exist, please enter a new move");
			validMove=false; 
		}catch(IllegalArgumentException e){ //will catch exception if the space is already taken
			System.out.println("This space is already taken, please enter a new move");
			validMove=false; 
		}
		return validMove; 	
		//put return statement to help later on with possible errors when the user is inputting his/her move
	}
	
	public static void getUserMove(char[][]b){ 
		//will keep asking for users move until it can put it on the board. Wont skip users turn. 
		for(;;){
		try{
		System.out.print("What is your move: ");
		Scanner input = new Scanner(System.in);
		int x = input.nextInt();
		int y = input.nextInt(); 
		boolean checkValidMove= writeOnBoard(b,'x',x,y);
		if(checkValidMove==true){ //will only break for infinite loop if it is a valid move 
			break;
		}
		}catch(InputMismatchException e){ //if user enters the wrong format for an input. wont break from loop. 
			System.out.println("Wrong format for placement! Please enter two integers on the board separated by a space");
		}catch(ArrayIndexOutOfBoundsException e){ //if user enters a space not on the board. wont break from loop. 
			System.out.println("This space does not exist, please enter a new move"); 
		}catch(IllegalArgumentException e){ //if user enters a space that is already taken. wont break from loop. 
			System.out.println("This space is already taken, please enter a new move");
		}
		}
	}

	public static boolean checkForObviousMove(char[][]b){
		int count = 0; 
		//check across board for o's
		for(int i=0; i<b.length; i++){
			for(int j=0;j<b[i].length;j++){
				if(b[i][j]=='o'){
					count++; 
				}
			}
			//if there are o's in every spot except one. AI will place the o there. 
			if (count==b.length-1){
				for(int j=0;j<b[i].length;j++){
					if(b[i][j]==' '){
						writeOnBoard(b,'o',i,j);
						return true; 
					}
				}
			}
			count = 0; 
		}
		//check down board for o's
		count =0;
		for(int j=0; j<b.length; j++){
			for(int i=0; i<b[j].length;i++){
				if(b[i][j]=='o'){
					count++;
				}
			}
			//if there are o's in every spot except one. AI will place the o there.
			if(count==b.length-1){
				for(int i=0;i<b[j].length;i++){
					if(b[i][j]==' '){
						writeOnBoard(b,'o',i,j);
						return true; 
					}
				}
			}
			count = 0;
		}
		
		//check board diagonally for o's
		count=0; 
		for(int i=0; i<b.length;i++){
			if(b[i][i]=='o'){
				count++;
			}
		}
		//if there are o's in every spot except one. AI will place the o there.
		if(count==b.length-1){
			for(int i=0;i<b.length;i++){
				if(b[i][i]==' '){
					writeOnBoard(b,'o',i,i);
					return true;
				}
			}
		}
		
		//check backwards diagonally for o's
		count=0;
		for(int i=0;i<b.length;i++){
			if(b[i][b.length-(i+1)]=='o'){
					count++;
				}
			}
		//if there are o's in every spot except one. AI will place the o there.
		if (count==b.length-1){
			for(int i=0;i<b.length;i++){
				if(b[i][b.length-(i+1)]==' '){
					writeOnBoard(b,'o',i,(b.length-(i+1)));
					return true; 
				}
			}
		}
		
		//check down board for x's
				count =0;
				for(int j=0; j<b.length; j++){
					for(int i=0; i<b[j].length;i++){
						if(b[i][j]=='x'){
							count++;
						}
					}
					//if there are x's in every spot except one. AI will place the o there to prevent user from winning.
					if(count==b.length-1){
						for(int i=0;i<b[j].length;i++){
							if(b[i][j]==' '){
								writeOnBoard(b,'o',i,j);
								return true; 
							}
						}
					}
					count=0;
				}
		//check across board for x's
		count = 0; 
		for(int i=0; i<b.length; i++){
			for(int j=0;j<b[i].length;j++){
				if(b[i][j]=='x'){
					count++; 
				}
			}
			//if there are x's in every spot except one. AI will place the o there to prevent user from winning.
			if (count==b.length-1){
				for(int j=0;j<b[i].length;j++){
					if(b[i][j]==' '){
						writeOnBoard(b,'o',i,j);
						return true; 
					}
				}
			}
			count = 0; 
		}
		//check board diagonally for x's
		count=0; 
		for(int i=0; i<b.length;i++){
			if(b[i][i]=='x'){
				count++;
			}
		}
		//if there are x's in every spot except one. AI will place the o there to prevent user from winning.
		if(count==b.length-1){
			for(int i=0;i<b.length;i++){
				if(b[i][i]==' '){
					writeOnBoard(b,'o',i,i);
					return true;
				}
			}
		}
		
		//check board backwards diagonally for x's
				count=0;
				for(int i=0;i<b.length;i++){
					if(b[i][b.length-(i+1)]=='x'){
							count++;
						}
					}
				//if there are x's in every spot except one. AI will place the o there to prevent user from winning.
				if (count==b.length-1){
					for(int i=0;i<b.length;i++){
						if(b[i][b.length-(i+1)]==' '){
							writeOnBoard(b,'o',i,(b.length-(i+1)));
							return true; 
						}
					}
				}
		//if there is no obvious move for the AI 
		return false;
		
	}

	public static void getAIMove(char[][]b){
		boolean check = checkForObviousMove(b);//checks if there is an obvious move 
		//if not, then randomly generate move on the board. 
		if (check==false){
			for(;;){
			int x=(int)(Math.random()*b.length);
			int y=(int)(Math.random()*b.length); 
			if(b[x][y]==' '){
				writeOnBoard(b,'o',x,y);//will only break from loop if AI generates a valid move
				break;
			}
		}
	}
}

	public static char checkForWinner(char[][]b){
		int count = 0; 
		//check across for o's
		for(int i=0; i<b.length; i++){
			for(int j=0;j<b[i].length;j++){
				if(b[i][j]=='o'){
					count++; 
				}
			}
			//if there are all o's in a row
			if (count==b.length){
				return 'o';
			}
			count = 0; 
		}
		//check down for o's
		count =0;
		for(int j=0; j<b.length; j++){
			for(int i=0; i<b[j].length;i++){
				if(b[i][j]=='o'){
					count++;
				}
			}
			//if there are all o's in a column 
			if(count==b.length){
				return 'o';
			}
			count = 0;
		}
		
		//check diagonally for o's
		count=0; 
		for(int i=0; i<b.length;i++){
			if(b[i][i]=='o'){
				count++;
			}
		}
		//if there are all o's in a diagonal 
		if(count==b.length){
			return 'o';
		}
		
		//check backwards diagonally for o's
		count=0;
		for(int i=0;i<b.length;i++){
			if(b[i][b.length-(i+1)]=='o'){
					count++;
				}
			}
		//if there are all o's in a diagonal 
		if (count==b.length){
			return 'o';
		}
		
		//check down for x's
				count =0;
				for(int j=0; j<b.length; j++){
					for(int i=0; i<b[j].length;i++){
						if(b[i][j]=='x'){
							count++;
						}
					}
					//if there are all x's in a column
					if(count==b.length){
						return 'x';
					}
					count=0;
				}
		//check across for x's
		count = 0; 
		for(int i=0; i<b.length; i++){
			for(int j=0;j<b[i].length;j++){
				if(b[i][j]=='x'){
					count++; 
				}
			}
			//if there are all x's in a row
			if (count==b.length){
				return 'x';
			}
			count = 0; 
		}
		//check diagonally for x's
		count=0; 
		for(int i=0; i<b.length;i++){
			if(b[i][i]=='x'){
				count++;
			}
		}
		//if there are all x's in a diagonal  
		if(count==b.length){
			return 'x';
		}
		
		//check backwards diagonally for x's
				count=0;
				for(int i=0;i<b.length;i++){
					if(b[i][b.length-(i+1)]=='x'){
							count++;
						}
					}
				//if there are all x's in a diagonal 
				if (count==b.length){
					return 'x'; 
				}
				return ' '; //if no one has won
	
	
	}

	public static void play(){
		// a method to play the game
		System.out.print("Welcome to Tic-Tac-Toe. Please enter your name:");
		Scanner input2 = new Scanner(System.in);
		String name=input2.next(); //reads scanner as a string 
		System.out.println();
		System.out.println("Welcome, "+name+"! Are you ready to Play?");
		
		int boardSize=0;
		boolean validBoardSize=false; 
		//a loop to keep asking for the board size until the user enters a valid one  
		for(;;){
			try{
				System.out.print("Please choose the dimension of your board:");
				Scanner input3= new Scanner(System.in);
				boardSize=input3.nextInt();//reads scanner as an int 
				validBoardSize=true;
			}catch (InputMismatchException e){ //if user enters an invalid board size
				System.out.println();
				System.out.println("Invalid board size. Please enter an integer");
				validBoardSize=false; 
				continue;
			}
			if(boardSize<=1){
				System.out.println();
				System.out.println("Invalid board size. Please enter an integer greater than one.");
				validBoardSize=false;
			}
			if(validBoardSize==true){//will only break if user enters a valid board size 
				break;
			}
		}
		char[][]board = createBoard(boardSize);//will create board with dimensions specified by user 
		int diceRoll=(int)(Math.random()+0.5);//generate a random number between 0 and 1 
		System.out.println();
		System.out.println("The result of the coin toss is: "+diceRoll);
		if(diceRoll==0){ //if AI wins coin toss
			System.out.println("The AI has the first move");
			int i=0;
			for(i=0;i<=(boardSize*boardSize);i++){
				System.out.println("The AI made its move");
				getAIMove(board);//generate AI move
				displayBoard(board); //display board
				System.out.println(); 
				//check for a winner and if the board is full
				if(checkForWinner(board)=='o'){
					System.out.println("Game Over! Sorry you lost :(");
					break;
				}
				if (checkForWinner(board)=='x'){
					System.out.println("Game Over! Congratulations you won :)");
					break;
				}else{
					if(checkIfBoardIsFull(board)==true){
						System.out.println("Game Over! The result is a tie :|");
						break;
					}
				}
				getUserMove(board);//get user move 
				displayBoard(board);//display board
				System.out.println();//check for a winner and if the board is full 
				if(checkForWinner(board)=='o'){
					System.out.println("Game Over! Sorry you lost :(");
					break;
				}
				if (checkForWinner(board)=='x'){
					System.out.println("Game Over! Congratulations you won :)");
					break;
				}else{
					if(checkIfBoardIsFull(board)==true){
						System.out.println("Game Over! The result is a tie :|");
						break;
					}
				}
			}//will repeat this loop until a winner has been determined or the board is full 
		}else{ //if user has first move 
			System.out.println("You have the first move");
			displayBoard(board);
			for(;;){
				getUserMove(board);//get user move 
				displayBoard(board);//display board 
				System.out.println();
				//check for a winner and if the board is full 
				if(checkForWinner(board)=='o'){
					System.out.println("Game Over! Sorry you lost :(");
					break;
				}
				if (checkForWinner(board)=='x'){
					System.out.println("Game Over! Congratulations you won :)");
					break;
				}else{
					if(checkIfBoardIsFull(board)==true){
						System.out.println("Game Over! The result is a tie :|");
						break;
					}
				}
				System.out.println("The AI made its move");
				getAIMove(board);//get AI move
				displayBoard(board);//display board 
				System.out.println();
				//check for a winner and if the board is full again
				if(checkForWinner(board)=='o'){
					System.out.println("Game Over! Sorry you lost :(");
					break;
				}
				if (checkForWinner(board)=='x'){
					System.out.println("Game Over! Congratulations you won :)");
					break;
				}else{
					if(checkIfBoardIsFull(board)==true){
						System.out.println("Game Over! The result is a tie :|");
						break;
					}
				}
			}//will only break from this for loop if a winner has been determined or if the board is full 	
			}
}
		
	public static boolean checkIfBoardIsFull(char[][]b){
		//a method to check if the board is full 
		int count = 0;
		//check board for x's or o's
		for(int i=0; i<b.length; i++){
			for(int j=0;j<b[i].length;j++){
				if(b[i][j]=='o' || b[i][j]=='x'){
					count++; 
				}
			}
			//if board is full
			if (count==(b.length*b.length)){
				return true;
			}
			}
		return false; 
	}
}
		




















