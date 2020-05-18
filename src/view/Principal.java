package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.OperacaoThread;

/**
 * Um banco deve controlar Saques e Depósitos. 
 * O sistema pode permitir um Saque e um Depósito Simultâneos, 
 * mas nunca 2 Saques ou 2 Depósitos Simultâneos. 
 * Para calcular a transação(Saque ou Depósito),
 * o método deve receber o código da conta, 
 * o saldo da conta e o valor a ser transacionado. 
 * Deve-se montar um sistema que deve considerar que 
 * 20 transações simultâneas serão enviadas ao sistema 
 * (aleatoriamente essas transações podem ser qualquer uma das opções) 
 * e tratar todas as transações, de acordo com as regras acima.
 * @author daniel
 */
public class Principal {

	private static final String[] OPERACOES = new String[] {"Saque", "Depósito"};

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
