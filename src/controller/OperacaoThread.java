package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class OperacaoThread extends Thread {
	
	private static List<String> operacoes = new ArrayList<>();
	private int codigoConta;
	private int saldoConta;
	private int valorTransacao;
	private String operacao;
	private Semaphore semaforo;
	private Random random;
	private int index;
	
	public OperacaoThread(String operacao, Semaphore semaforo, int index) {
		random = new Random();
		
		this.codigoConta = random.nextInt(100);
		this.saldoConta = random.nextInt(5000);
		this.valorTransacao = random.nextInt(5000);
		this.operacao = operacao;
		this.semaforo = semaforo;
		this.index = index;
	}
	
	@Override
	public void run() {
		try {
			
			aguardandoOperacao();
			
			while (operacoes.contains(operacao)) {
				sleep(5000);
			}
			
			operacoes.add(operacao);
			semaforo.acquire();
			operacao();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			
			operacoes.remove(operacao);
		}
	}
	
	private void aguardandoOperacao() throws InterruptedException {
		int tempo = valorTransacao + saldoConta;
		sleep(tempo);
	}
	
	private void operacao() throws InterruptedException {
		
		System.out.println(index + " - Operação '" + operacao + "' sendo realizada com os seguintes dados: ");
		System.out.println("Conta: " + codigoConta + "; Saldo: " + saldoConta + "; Valor da transação: " + valorTransacao);
		
		int tempo = valorTransacao + saldoConta;
		sleep(tempo);
		
		System.out.println(index + " - Operação finalizada");
	}
	
}