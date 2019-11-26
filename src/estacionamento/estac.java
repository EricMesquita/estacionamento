package estacionamento;

import java.util.concurrent.Semaphore;

public class estac {
	


	    // Vaga ocupada = true; livre = false.
	    private static final boolean[] VAGAS = new boolean[5];
	    // Set fair to true that for method aÑ�quire() garantie order
	    private static final Semaphore SEMAFORO = new Semaphore(5, true);


	    public static void main(String[] args) throws InterruptedException {
	        for (int i = 0; i < 7; i++) {
	            new Thread(new Carro(i)).start();
	            Thread.sleep(500);
	        }
	    }


	    public static class Carro implements Runnable {

	        private int numeroCarro;

	        public Carro(int numeroCarro) {
	            this.numeroCarro = numeroCarro;
	        }

	        @Override
	        public void run() {
	            System.out.printf("Carro #%d entrou no estacionamento \n", numeroCarro);

	            try {
	                SEMAFORO.acquire();

	                int numeroVaga = -1;

	                synchronized (VAGAS) {
	                    for (int i = 0; i < 5; i++) {
	                        if (!VAGAS[i]) {
	                            VAGAS[i] = true;
	                            numeroVaga = i;
	                            System.out.printf("Carro #%d estacionou na vaga %d.\n", numeroCarro, i);
	                            break;
	                        }
	                    }
	                }
	                //Shopping!
	                Thread.sleep(5000);

	                synchronized (VAGAS) {
	                    //Free space for car
	                    VAGAS[numeroVaga] = false;
	                }
	                

	                SEMAFORO.release();
	                System.out.printf("Carro #%d saiu do estacionamento.\n", numeroCarro);

	            } catch (InterruptedException e) {
	            }
	        }
	    }
	}


