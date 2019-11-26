public class Pilha<E> 
{
	No<E> top;
	int tamanho;
	
	// Declaração das Funções Básicas
	
	public Pilha() 
	{
		this.tamanho = 0;
		this.top = null;
	}
	
	public E pop() 
	{
		No<E> aux = top;
		E elemento = null;
		if(!vazio()) 
		{
			elemento = aux.elemento;
			top = aux.next;
			aux.next = null;
			tamanho = tamanho - 1;
		}
		return elemento;
	}
	
	public void push(E elemento) 
	{
		No<E> aux = new No<>(elemento);
		if(tamanho != 0) 
		{
			aux.next = top;			
		}
		top = aux;
		tamanho = tamanho + 1;
	}
	
	public boolean vazio() 
	{
		if(tamanho == 0) 
		{
			return true;
		}
		return false;
	}
	public E top() 
	{
		E elemento = null;
		if(!vazio()) 
		{
			elemento = top.elemento;
		}
		return elemento;
	}
	
	public int tamanho() 
	{
		return tamanho;
	}
}
