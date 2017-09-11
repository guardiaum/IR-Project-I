package com.ir.project.run;

import java.io.IOException;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import com.ir.project.lucene.Index;
import com.ir.project.util.Constants;
import com.ir.project.util.TextFileFilter;

public class IndexingExecution {
	
	public static void main(String[] args) {
		
		try {
			// create standard index
			// BASE 1
			System.out.println("================================= INDEXING BASE 1 ======================================");
			Index standard = new Index(Constants.STANDARD_INDEX_PATH, new StandardAnalyzer());
			standard.deleteAll();
			standard.startIndexing(Constants.DOCUMENTS_DS, new TextFileFilter());
			standard.startIndexing(Constants.DOCUMENTS_IR, new TextFileFilter());
			standard.startIndexing(Constants.DOCUMENTS_LG, new TextFileFilter());
			standard.startIndexing(Constants.DOCUMENTS_ML, new TextFileFilter());
			int out = standard.startIndexing(Constants.DOCUMENTS_NE, new TextFileFilter());
			System.out.println(out + " files were indexed.");
			System.out.println("Finish indexing base 1.");
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
			stop.close();
			
			// BASE 3
			// create stemming index ===> SnowballFilter, KStemFilter or HunspellStemFilter ???
			// Index stem = new Index(Constants.STEMMING_INDEX_PATH, new EnglishAnalyzer(null, stemExclusionSet));
			
			//BASE 4
			// Stemming e Stopwords
			// Index stopAndStem = new Index(Constants.STEMMING_INDEX_PATH, 
			//		new EnglishAnalyzer(EnglishAnalyzer.getDefaultStopSet(), stemExclusionSet));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
