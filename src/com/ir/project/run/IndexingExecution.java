package com.ir.project.run;

import java.io.IOException;
import java.util.Collections;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;

import com.ir.project.lucene.Index;
import com.ir.project.util.Constants;
import com.ir.project.util.TextFileFilter;

public class IndexingExecution {
	
	public static void main(String[] args) {
		
		try {
			// create standard index
			// BASE 1
			System.out.println("================================= INDEXING BASE 1 ======================================");
			Index standard = new Index(Constants.STANDARD_INDEX_PATH, new EnglishAnalyzer(CharArraySet.EMPTY_SET));
			standard.deleteAll();
			standard.startIndexing(Constants.DOCUMENTS_DS, new TextFileFilter());
			standard.startIndexing(Constants.DOCUMENTS_IR, new TextFileFilter());
			standard.startIndexing(Constants.DOCUMENTS_LG, new TextFileFilter());
			standard.startIndexing(Constants.DOCUMENTS_ML, new TextFileFilter());
			int out = standard.startIndexing(Constants.DOCUMENTS_NE, new TextFileFilter());
			System.out.println(out + " files were indexed.");
			System.out.println("Finish indexing base 1.");
			standard.commit();
			standard.close();
			
			// BASE 2
			// create stopwords index
			System.out.println("================================= INDEXING BASE 2 ======================================");
			Index stop = new Index(Constants.STOPWORDS_INDEX_PATH, new EnglishAnalyzer(EnglishAnalyzer.getDefaultStopSet()));
			stop.deleteAll();	
			stop.startIndexing(Constants.DOCUMENTS_DS, new TextFileFilter());
			stop.startIndexing(Constants.DOCUMENTS_IR, new TextFileFilter());
			stop.startIndexing(Constants.DOCUMENTS_LG, new TextFileFilter());
			stop.startIndexing(Constants.DOCUMENTS_ML, new TextFileFilter());
			int outStop = stop.startIndexing(Constants.DOCUMENTS_NE, new TextFileFilter());
			System.out.println(outStop + " files were indexed.");
			System.out.println("Finish indexing base 2.");
			stop.commit();
			stop.close();
			
			// BASE 3
			// DO NOT STOP AND STEM
			System.out.println("================================= INDEXING BASE 3 ======================================");
			Index stem = new Index(Constants.STEMMING_INDEX_PATH, new EnglishAnalyzer(CharArraySet.EMPTY_SET, CharArraySet.EMPTY_SET));
			stem.deleteAll();	
			stem.startIndexing(Constants.DOCUMENTS_DS, new TextFileFilter());
			stem.startIndexing(Constants.DOCUMENTS_IR, new TextFileFilter());
			stem.startIndexing(Constants.DOCUMENTS_LG, new TextFileFilter());
			stem.startIndexing(Constants.DOCUMENTS_ML, new TextFileFilter());
			int outStem = stem.startIndexing(Constants.DOCUMENTS_NE, new TextFileFilter());
			System.out.println(outStem + " files were indexed.");
			System.out.println("Finish indexing base 3.");
			stem.commit();
			stem.close();
			
			//BASE 4
			// Stemming e Stopwords
			System.out.println("================================= INDEXING BASE 4 ======================================");
			Index stopAndStem = new Index(Constants.STOPSTEM_INDEX_PATH, 
				new EnglishAnalyzer(EnglishAnalyzer.getDefaultStopSet(), CharArraySet.EMPTY_SET));
			stopAndStem.deleteAll();	
			stopAndStem.startIndexing(Constants.DOCUMENTS_DS, new TextFileFilter());
			stopAndStem.startIndexing(Constants.DOCUMENTS_IR, new TextFileFilter());
			stopAndStem.startIndexing(Constants.DOCUMENTS_LG, new TextFileFilter());
			stopAndStem.startIndexing(Constants.DOCUMENTS_ML, new TextFileFilter());
			int outStopAndStem = stopAndStem.startIndexing(Constants.DOCUMENTS_NE, new TextFileFilter());
			System.out.println(outStopAndStem + " files were indexed.");
			System.out.println("Finish indexing base 4.");
			stopAndStem.commit();
			stopAndStem.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
