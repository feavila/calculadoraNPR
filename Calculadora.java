import java.util.Scanner;

public class Calculadora 
{
	public static void main(String[] args) 
	{		
		
		// Declaração
		boolean numero = false;
		int aux = 0; //Varredura
		double a, b, c, d = 0;
		double pot = 0;
		char digito;

		String notacao = "";
		Pilha<Character> Pilha1 = new Pilha<Character>(); //2 pilhas necessárias 
		Pilha<Double> Pilha2 = new Pilha<Double>();
		
		//Entrada de Dados
		Scanner teclado = new Scanner(System.in);
		System.out.println("Bem-Vindo à Calculadora Manual!");
		System.out.println("Insira apenas os sinais +, -, *, / para as operações");
		System.out.println("Insira uma equação: ");
		String equacao = teclado.nextLine();	
		
		//Processamento de Dados
		
		while (aux < equacao.length()) 
		{
			digito = equacao.charAt(aux);
			if (digito == '0' || 
				digito == '1' || 
				digito == '2' || 
				digito == '3' || 
				digito == '4' || 
				digito == '5' || 
				digito == '6' || 
				digito == '7' || 
				digito == '8' || 
				digito == '9') 
			{
				
				while (aux+1 < equacao.length() && 
						(equacao.charAt(aux+1) == '0' || 
						equacao.charAt(aux+1) == '1' || 
						equacao.charAt(aux+1) == '2' || 
						equacao.charAt(aux+1) == '3' || 
						equacao.charAt(aux+1) == '4' || 
						equacao.charAt(aux+1) == '5' || 
						equacao.charAt(aux+1) == '6' || 
						equacao.charAt(aux+1) == '7' || 
						equacao.charAt(aux+1) == '8' || 
						equacao.charAt(aux+1) == '9')) 
				{
					notacao = notacao + digito;
					aux = aux + 1;
					digito = equacao.charAt(aux);
				}
				notacao = notacao + digito;
				notacao = notacao + '.';
			}
			
			else if (digito == '(') {
				Pilha1.push(digito);
			}
			
			else if (digito == '+' || digito == '-') {
				while (!Pilha1.vazio() && 
						(Pilha1.top() == '*' || 
						Pilha1.top() == '/' || 
						Pilha1.top() == '^' || 
						Pilha1.top() == '+' || 
						Pilha1.top() == '-')) 
				{
					notacao = notacao + Pilha1.pop();
				}
				Pilha1.push(digito);
			}
			else if (digito == '*' || digito == '/') {
				while (!Pilha1.vazio() && (Pilha1.top() == '*' || 
						Pilha1.top() == '/' || 
						Pilha1.top() == '^')) 
				{
					notacao = notacao + Pilha1.pop();
				}
				Pilha1.push(digito);
			}
			else if (digito == '^') 
			{
				while (!Pilha1.vazio() && Pilha1.top() == '^') 
				{
					notacao = notacao + Pilha1.pop();
				}
				Pilha1.push(digito);
			}
			else if (digito == ')') 
			{
				while (!Pilha1.vazio() && Pilha1.top() != '(') 
				{
					notacao = notacao + Pilha1.pop();
				}
				if (Pilha1.top() == '(') 
				{
					Pilha1.pop();
				}
			}
			aux = aux + 1 ;
		}
		while (Pilha1.vazio() == false) 
		{
			notacao = notacao + Pilha1.pop();
		}
		
		aux = 0;
		
		
		while (aux < notacao.length()) 
		{
			digito = notacao.charAt(aux);
			if (digito == '0' || 
				digito == '1' ||
				digito == '2' || 
				digito == '3' || 
				digito == '4' ||
				digito == '5' || 
				digito == '6' || 
				digito == '7' || 
				digito == '8' ||
				digito == '9') 
			{
				
				// Coloca em notação Polonesa Reversa
				while (aux+1 < notacao.length() && 
						(notacao.charAt(aux+1) == '0' || 
						notacao.charAt(aux+1) == '1' || 
						notacao.charAt(aux+1) == '2' || 
						notacao.charAt(aux+1) == '3' || 
						notacao.charAt(aux+1) == '4' || 
						notacao.charAt(aux+1) == '5' || 
						notacao.charAt(aux+1) == '6' || 
						notacao.charAt(aux+1) == '7' || 
						notacao.charAt(aux+1) == '8' || 
						notacao.charAt(aux+1) == '9')) 
				{
					Pilha1.push(digito);
					aux = aux + 1;
					digito = notacao.charAt(aux);
					numero = true;
				}
				
				if (numero) 
				{
					Pilha1.push(digito);
					while (!Pilha1.vazio()) 
					{
						a = (Pilha1.pop() - '0');
						d = d + a * Math.pow(10, pot);
						pot = pot + 1;
					}
					Pilha2.push(d);
					pot = 0;
					d = 0;
					numero = false;
				}
				else 
				{
					a = (digito - '0');
					Pilha2.push(a);
				}
			}
			else if (digito == '+' || 
					digito == '-' || 
					digito == '/' || 
					digito == '*' || 
					digito == '^') 
			{
				c = Pilha2.pop();
				b = Pilha2.pop();
				if (digito == '+') 
				{
					b = b + c;
				}				
				else if (digito == '-') 
				{
					b = (b - c);
				}
				else if (digito == '*') 
				{
					b = (b * c);
				}				
				else if (digito == '/') 
				{
					b = (b / c);
				}				
								
				else if (digito == '^') 
				{
					b = Math.pow(b, c);
				}
				Pilha2.push(b);
			}
			aux = aux + 1;
		}
		
		// Saída de Dados
		System.out.println("RESULTADO = "+ Pilha2.pop());
		teclado.close();
	}
}
