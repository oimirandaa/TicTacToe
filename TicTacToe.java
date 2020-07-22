import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * 
 * Esta es una clase donde se puede jugar el juego de TicTacToe contra la computadora. La computadora
 * genera sus elecciones de manera aleatoria. 
 * @author Oscar Miranda
 *
 */
public class TicTacToe {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner scan;
		
		
		/**
		 * Aquí creamos el tablero de juego en blanco
		 */
		char[][] gameBoard = {{' ', '|',' ','|',' '},
						{'-', '+','-','+','-'},
						{' ', '|',' ','|',' '},
						{'-', '+','-','+','-'},
						{' ', '|',' ','|',' '}};
		
		
		//Lo mostramos por primera vez
		printGameBoard(gameBoard);
		
		
		/**
		 * Aquí es donde se llevara acabo el juego. Tenemos el while que nos dejara jugar hasta un break.
		 */
		while(true)
		{
			scan = new Scanner(System.in);
			
			//Imprimimos el primer comando
			System.out.println("Enter your placement 1-9");
			int posJugador =scan.nextInt(); //Tomamos el numero que escoga  el jugador.
			
			while(jugadorPos.contains(posJugador) || cpuPos.contains(posJugador)) //Revisamos para que no se repitan posisciones por parte del jugador
			{
				System.out.println("Posicion tomada, elegi otra posicion");
				posJugador =scan.nextInt();
			}
			
			/**
			 * Agregamos la posición
			 */
			position(gameBoard,posJugador, "player");
			
			//Revisamos que el juego continue
			String resultado = ganador();
			if(resultado.length() > 0 )
			{
				System.out.println(resultado);
				printGameBoard(gameBoard);
				break;
			}
			
			
			//Generamos el numero aleatorio de la computadora.
			Random rand = new Random();
			int cpuPosicion = rand.nextInt(9)+1;
			
			while(jugadorPos.contains(cpuPosicion) || cpuPos.contains(cpuPosicion)) //Revisamos que no se repita el lugar elegido al azar.
			{
				cpuPosicion = rand.nextInt(9)+1;
			}
			
			//Agregamos la posicion elegida por la computadora.
			position(gameBoard,cpuPosicion, "cpu");
			
			//Mostramos el tablero como va
			printGameBoard(gameBoard);
			
			//Revisamos si ya gano alguien
			resultado = ganador();
			
			if(resultado.length() > 0 )//Si ya gano alguien, si el String no es vacio, entonces terminamos el juego.
			{
				System.out.println(resultado);
				printGameBoard(gameBoard);
				break;
			}
			
		}
	
		

	
		
	}	
	
	
	/**
	 * Este método imprime el tablero de juego. 
	 * @param gameBoard. El parametro es un array de tipo Char bidimensional. El array es creado anteriormente.
	 */
	public static void printGameBoard(char[][] gameBoard)
	{
		for(char[] row:gameBoard)
		{
			for(char c:row)
			{
				System.out.print(c);
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * Este método coloca en una posición elegida por el jugador o por la computadora
	 * en el lugar elegido. El jugador le corresponde el caracter X y a la computadora el O.
	 * 
	 * @param gameBoard. El parametro es un array de tipo Char bidimensional. El array es creado anteriormente
	 * @param pos. Este parametro es un int entre 1-9 que nos da la posición en la que se quiere hacer una jugada.
	 * @param user. Este parametro indica quien es el que esta jugando.
	 */
	public static void position(char[][] gameBoard, int pos, String user)
	{
		
		char simbolo = ' ';
	
		
		if(user.equals("player"))
		{
			simbolo = 'X';
			jugadorPos.add(pos);
		}
		else if(user.equals("cpu"))
		{
			simbolo = 'O';
			cpuPos.add(pos);
		}
		
		
		
		switch(pos) {
		
		case 1:
			gameBoard[0][0] = simbolo;
			break;
		case 2:
			gameBoard[0][2] = simbolo;
			break;	
		case 3:
			gameBoard[0][4] = simbolo;
			break;
		case 4:
			gameBoard[2][0] = simbolo;
			break;
		case 5:
			gameBoard[2][2] = simbolo;
			break;
		case 6:
			gameBoard[2][4] = simbolo;
			break;
		case 7:
			gameBoard[4][0] = simbolo;
			break;
		case 8:
			gameBoard[4][2] = simbolo;
			break;
		case 9:
			gameBoard[4][4] = simbolo;
			break;
		default:
			break;
		}

	}

	
	/**
	 * Este método nos devuelve el quien gano durante el juego o si hubo un empate. 
	 * @return Nos devuelce un String que nos indica si alguien ha ganado o si se llego a un empate.
	 */
	public static String ganador()
	{
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);

		List izCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List derCol = Arrays.asList(3, 6, 9);
		
		List diagonal1 = Arrays.asList(1,5,9);
		List diagonal2 = Arrays.asList(3,5,7);
		
		List<List> condiciones = new ArrayList<List>();
		
		condiciones.add(topRow);
		condiciones.add(midRow);
		condiciones.add(botRow);
		condiciones.add(izCol);
		condiciones.add(midCol);
		condiciones.add(derCol);
		condiciones.add(diagonal1);
		condiciones.add(diagonal2);
		
		for(List l:condiciones)
		{
			if(jugadorPos.containsAll(l))
			{
				return "Gana Jugador";
			}
			else if(cpuPos.contains(l))
			{
				return "Gana la computadora";
			}
			else if(jugadorPos.size() + cpuPos.size() == 9)
			{
				return "Empate";
			}
		}
		
		
		
		return "";
	}
	

	static ArrayList<Integer> jugadorPos = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPos = new ArrayList<Integer>();

}
