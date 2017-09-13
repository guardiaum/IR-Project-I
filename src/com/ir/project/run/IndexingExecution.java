package com.ir.project.run;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.index.CorruptIndexException;

import com.ir.project.lucene.Index;
import com.ir.project.util.Constants;
import com.ir.project.util.TextFileFilter;

public class IndexingExecution {
	
	public static void main(String[] args) {
		
		try {
			// create standard index
			// BASE 1
			System.out.println("================================= INDEXING BASE 1 ======================================");
			indexBase(Constants.STANDARD_INDEX_PATH, new EnglishAnalyzer(CharArraySet.EMPTY_SET), 1);
			
			// BASE 2
			// create stopwords index
			System.out.println("================================= INDEXING BASE 2 ======================================");
			indexBase(Constants.STOPWORDS_INDEX_PATH, new EnglishAnalyzer(EnglishAnalyzer.getDefaultStopSet()), 2);
			
			// BASE 3
			// DO NOT STOP AND STEM
			System.out.println("================================= INDEXING BASE 3 ======================================");
			indexBase(Constants.STEMMING_INDEX_PATH, new EnglishAnalyzer(CharArraySet.EMPTY_SET, CharArraySet.EMPTY_SET), 3);
			
			//BASE 4
			// Stemming e Stopwords
			System.out.println("================================= INDEXING BASE 4 ======================================");
			indexBase(Constants.STOPSTEM_INDEX_PATH, 
				new EnglishAnalyzer(EnglishAnalyzer.getDefaultStopSet(), CharArraySet.EMPTY_SET), 4);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void indexBase(String indexPath, Analyzer analyzer, int baseID) throws IOException, CorruptIndexException {
		Index base = new Index(indexPath, analyzer);
		
		base.deleteAll();
		
		base.startIndexing(Constants.DOCUMENTS_DS, new TextFileFilter());
		base.startIndexing(Constants.DOCUMENTS_IR, new TextFileFilter());
		base.startIndexing(Constants.DOCUMENTS_LG, new TextFileFilter());
		base.startIndexing(Constants.DOCUMENTS_ML, new TextFileFilter());
		int out = base.startIndexing(Constants.DOCUMENTS_NE, new TextFileFilter());
		
		System.out.println(out + " files were indexed.");
		System.out.println("Finish indexing base " + baseID);
		
		base.commit();
		base.close();
	}

}
