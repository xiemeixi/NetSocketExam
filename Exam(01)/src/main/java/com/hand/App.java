package com.hand;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

public class App {

	public static void main(String[] args) {
		URL url;
		String str;
		try {
			url = new URL("http://files.saas.hand-china.com/java/target.pdf");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			FileOutputStream fos = new FileOutputStream("target.pdf");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			PrintWriter pw = new PrintWriter(osw, true);
			while ((str = br.readLine()) != null) {
				pw.println(str);
			}
			pw.close();
			br.close();
			osw.close();
			isr.close();
			fos.close();
			is.close();
			System.out.println("done");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
