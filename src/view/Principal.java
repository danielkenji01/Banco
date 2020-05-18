package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.OperacaoThread;

/**
 * Um banco deve controlar Saques e Dep�sitos. 
 * O sistema pode permitir um Saque e um Dep�sito Simult�neos, 
 * mas nunca 2 Saques ou 2 Dep�sitos Simult�neos. 
 * Para calcular a transa��o(Saque ou Dep�sito),
 * o m�todo deve receber o c�digo da conta, 
 * o saldo da conta e o valor a ser transacionado. 
 * Deve-se montar um sistema que deve considerar que 
 * 20 transa��es simult�neas ser�o enviadas ao sistema 
 * (aleatoriamente essas transa��es podem ser qualquer uma das op��es) 
 * e tratar todas as transa��es, de acordo com as regras acima.
 * @author daniel
 */
public class Principal {

	private static final String[] OPERACOES = new String[] {"Saque", "Dep�sito"};

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Semaphore semaphore = new Semaphore(2);

		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			
			String operacao = OPERACOES[random.nextInt(2)];
			
			OperacaoThread thread = new OperacaoThread(operacao, semaphore, i);
			thread.start();
		}
	}

}
