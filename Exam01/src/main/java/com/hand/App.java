package com.hand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class App {

	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://files.saas.hand-china.com/java/target.pdf");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			FileOutputStream fos = new FileOutputStream("target.pdf");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte[] input = new byte[1000];
			while (bis.read(input) != -1) {
				bos.write(input);
				Thread.sleep(5);
			}
			bos.flush();
			bos.close();
			bis.close();
			fos.close();
			System.out.println("Íê³É");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
