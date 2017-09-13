package com.ir.project.run;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.ir.project.lucene.Searcher;
import com.ir.project.util.Constants;

public class SearchExecution {
	
	public static void main(String[] args) {
		String query = "machine learning in information recovery";
		
		try {
			// Querying Base 1
			queryBase(query, Constants.STANDARD_INDEX_PATH, new EnglishAnalyzer(CharArraySet.EMPTY_SET));
			/*
			// Querying Base 2
			queryBase(query, Constants.STOPWORDS_INDEX_PATH, new EnglishAnalyzer(EnglishAnalyzer.getDefaultStopSet()));

			// Querying Base 3
			queryBase(query, Constants.STEMMING_INDEX_PATH, new EnglishAnalyzer(CharArraySet.EMPTY_SET, CharArraySet.EMPTY_SET));

			// Querying Base 4
			queryBase(query, Constants.STOPSTEM_INDEX_PATH, new EnglishAnalyzer(EnglishAnalyzer.getDefaultStopSet(), CharArraySet.EMPTY_SET));
			*/
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void queryBase(String query, String base, Analyzer analyzer) throws IOException, Exception {
		Searcher searcher = Searcher.getInstance();
		IndexSearcher searchStandardIndex = searcher.createSearcher(base);
		TopDocs foundFiles = searcher.queryFileContent(query, searchStandardIndex, analyzer);
		
		System.out.println("Consulta: "+ query);
		
		printFoundFiles(searchStandardIndex, foundFiles);
	}
	
	private static void printFoundFiles(IndexSearcher searcher, TopDocs foundFiles) throws IOException {
		System.out.println("Quantidade de documentos retornados: " + foundFiles.totalHits);
		for (ScoreDoc foundFile : foundFiles.scoreDocs) {
			Document document = searcher.doc(foundFile.doc);
			System.out.println(document.get(Constants.FILENAME));
		}
	}

}
