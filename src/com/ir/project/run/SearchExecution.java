package com.ir.project.run;

import java.io.IOException;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.ir.project.lucene.Searcher;
import com.ir.project.util.Constants;

public class SearchExecution {
	
	private static Scanner in;
	
	public static void main(String[] args) {
		
		int baseOp = readBaseOption();
		
		String query = readQuery();
		
		try {
			switch(baseOp) {
				case 1: // Querying Base 1
					queryBase(query, Constants.STANDARD_INDEX_PATH, 
							new StandardAnalyzer(CharArraySet.EMPTY_SET));
					break;
				case 2: // Querying Base 2
					queryBase(query, Constants.STOPWORDS_INDEX_PATH, 
							new EnglishAnalyzer(EnglishAnalyzer.getDefaultStopSet()));
					break;
				case 3: // Querying Base 3
					queryBase(query, Constants.STEMMING_INDEX_PATH, 
							new EnglishAnalyzer(CharArraySet.EMPTY_SET, CharArraySet.EMPTY_SET));
					break;
				case 4: // Querying Base 4
					queryBase(query, Constants.STOPSTEM_INDEX_PATH, 
							new EnglishAnalyzer(EnglishAnalyzer.getDefaultStopSet(), CharArraySet.EMPTY_SET));
					break;
				default:
					System.out.println("Option not allowed.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static String readQuery() {
		in = new Scanner(System.in);
		System.out.println("Inform query and press ENTER: ");
		String query = in.nextLine();
		return query;
	}

	private static int readBaseOption() {
		in = new Scanner(System.in);
		System.out.println("1 : Standard");
		System.out.println("2 : Stopwords");
		System.out.println("3 : Stemming");
		System.out.println("4 : Stop + Stemming");
		
		System.out.println("Choose base to query: ");
		int baseOp = in.nextInt();
		
		return baseOp;
	}

	private static void queryBase(String query, String base, Analyzer analyzer) throws IOException, Exception {
		Searcher searcher = Searcher.getInstance();
		IndexSearcher searchStandardIndex = searcher.createSearcher(base);
		TopDocs foundFiles = searcher.queryFileContent(query, searchStandardIndex, analyzer);
		
		System.out.println("Consulta: "+ query);
		
		printFoundFiles(searchStandardIndex, foundFiles, true);
	}
	
	private static void printFoundFiles(IndexSearcher searcher, TopDocs foundFiles, boolean showReturnedFiles) throws IOException {
		showReturnSize(foundFiles);
		if(showReturnedFiles) {
			System.out.println("--- DOCUMENTS -----------------------------------------------------------------------------------");
			for (ScoreDoc foundFile : foundFiles.scoreDocs) {
				Document document = searcher.doc(foundFile.doc);
				System.out.println("> "+document.get(Constants.FILENAME));
			}
		}
	}

	private static void showReturnSize(TopDocs foundFiles) {
		System.out.println("Quantidade de documentos retornados: " + foundFiles.totalHits);
	}

}
