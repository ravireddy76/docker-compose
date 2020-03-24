package com.play.poc.xcel;

import java.io.File;
import java.util.List;

import io.github.millij.poi.ss.reader.XlsReader;

public class XcelDataClient {
	private static String INPUT_FILE = "/Users/rredd16/IntelliJ-Playgrounds/standalone-play/src/main/resources/hxCommissions_Transactions_Paid.xlsx";

	public static void main(String[] args) {
		try {
			final File xlsxFile = new File(INPUT_FILE);
			final XlsReader reader = new XlsReader();
			List<XcelData> dataRecords = reader.read(XcelData.class, xlsxFile);
			
			System.out.println("Total DataRecords :: "+dataRecords.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
