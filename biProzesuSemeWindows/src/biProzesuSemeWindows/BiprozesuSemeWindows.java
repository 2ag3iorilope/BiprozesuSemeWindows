package biProzesuSemeWindows;

import java.io.*;
import java.nio.file.*;

public class BiprozesuSemeWindows {
	public static void main(String[] args) {
		try {

			File tempFile = new File("output.txt");

			ProcessBuilder builder1 = new ProcessBuilder("powershell.exe", "dir");
			builder1.redirectOutput(tempFile);
			builder1.redirectErrorStream(true);
			Process process1 = builder1.start();

			int exitCode1 = process1.waitFor();
			System.out.println("Prozesu 1 urrengo kodearekin amaitu da: " + exitCode1);

			ProcessBuilder builder2 = new ProcessBuilder("powershell.exe", "Select-String", "d",
					tempFile.getAbsolutePath());
			builder2.redirectErrorStream(true);
			Process process2 = builder2.start();

			int exitCode2 = process2.waitFor();
			System.out.println("Prozesu 2 amaitu da urrengo kdoearekin: " + exitCode2);

			InputStream inputStream2 = process2.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream2));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			tempFile.delete();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
