package estacionamento;

import java.lang.Thread;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

public class estacionamento {

	private Semaphore semaforo;
	private Semaphore semaforo1;
	private Semaphore semaforo2;

	// Vaga ocupada = true; livre = false.
	private static final boolean[] VAGAS = new boolean[10];
	int i;
	int numeroVaga;

	public static void main(String[] args) {
		// interação com usuário
		int x;
		Runnable t1 = null, t2 = null, t3 = null;

		String options[] = { "ENTRAR" };

		x = JOptionPane.showOptionDialog(null, "BEM VINDO!!", "", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		new Thread(t1).start();
		new Thread(t2).start();
		new Thread(t3).start();

	}

	public void cancela() throws InterruptedException {

		synchronized (VAGAS) {

			for (i = 1; i <= VAGAS.length; i++) {

				semaforo.acquire();
				Runnable t1 = new Runnable() {
					// cancela 1
					public void run() {

						if (!VAGAS[i]) {
							VAGAS[i] = true;
							numeroVaga = i;
							JOptionPane.showInputDialog("VAGA " + i + " OCUPADA");
						}
					}
				};
				semaforo.release();

				semaforo1.acquire();
				Runnable t2 = new Runnable() {
					// cancela 2
					public void run() {

						if (!VAGAS[i]) {
							VAGAS[i] = true;
							numeroVaga = i;
							JOptionPane.showInputDialog("VAGA " + i + " OCUPADA");

						}
					}
				};
				semaforo1.release();

				semaforo2.acquire();
				Runnable t3 = new Runnable() {

					public void run() {

						if (!VAGAS[i]) {
							VAGAS[i] = true;
							numeroVaga = i;
							JOptionPane.showInputDialog("VAGA " + i + " OCUPADA");
						}

					}

				};
				semaforo2.release();
			}
		}

	}

}
