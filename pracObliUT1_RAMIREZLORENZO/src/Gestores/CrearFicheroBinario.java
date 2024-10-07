package Gestores;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearFicheroBinario {

	public static void main(String[] args) {

		try {
			File fichero = new File("Grupos.dat");
			RandomAccessFile ficheroRan = new RandomAccessFile(fichero, "rw");

			String[] grupos = { "Psychonaut 4", "The cure" };
			int[] fechaCreacion = { 2010, 1976 };

			StringBuffer nombres = null;

			for (int i = 0; i < grupos.length; i++) {
				nombres = new StringBuffer(grupos[i]);
				nombres.setLength(12);
				ficheroRan.writeChars(nombres.toString());

				ficheroRan.writeInt(fechaCreacion[i]);
			}
			
			ficheroRan.close();

		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
