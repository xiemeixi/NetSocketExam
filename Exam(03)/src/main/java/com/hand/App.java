package com.hand;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class App {

	public static void main(String[] args) {
		try {
			// Get
			URL url = new URL("http://hq.sinajs.cn/list=sz300170");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStreamReader isr = new InputStreamReader(
					conn.getInputStream(), "GBK");
			BufferedReader br = new BufferedReader(isr);

			String buff = null;
			StringBuilder sb = new StringBuilder();
			while ((buff = br.readLine()) != null) {
				sb.append(buff);
				Thread.sleep(5);
			}
			buff = sb.toString();
			String[] arg = buff.split(",");
			arg[0] = arg[0].substring(arg[0].indexOf('\"') + 1);

			br.close();
			isr.close();

			// DOM
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			Element root = document.createElement("xml");
			Element stock = document.createElement("stock");
			Element name = document.createElement("name");
			name.setTextContent(arg[0]);
			Element open = document.createElement("open");
			open.setTextContent(arg[1]);
			Element close = document.createElement("close");
			close.setTextContent(arg[2]);
			Element current = document.createElement("current");
			current.setTextContent(arg[3]);
			Element high = document.createElement("high");
			high.setTextContent(arg[4]);
			Element low = document.createElement("low");
			low.setTextContent(arg[5]);

			stock.appendChild(name);
			stock.appendChild(open);
			stock.appendChild(close);
			stock.appendChild(current);
			stock.appendChild(high);
			stock.appendChild(low);
			root.appendChild(stock);
			document.appendChild(root);

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("encoding", "UTF-8");
			transformer.transform(new DOMSource(document), new StreamResult(
					new File("hand.xml")));

			String jstr = "{\"name\":\"" + arg[0] + "\",\"open\":" + arg[1]
					+ ",\"close\":" + arg[2] + ",\"current\":" + arg[3]
					+ ",\"high\":" + arg[4] + ",\"low\":" + arg[5] + "}";

			FileOutputStream fos = new FileOutputStream("hand.json");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(jstr);
			bw.flush();
			bw.close();
			osw.close();
			fos.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
