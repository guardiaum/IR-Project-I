package com.ir.project.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.ir.project.util.Constants;

/**
 * Singleton for create searcher object for quering
 * 
 * @author jms5
 *
 */
public class Searcher {
	
	private static Searcher instance = null;
	
	private Searcher() {}
	
	public static Searcher getInstance() {
		if(instance==null)
			return new Searcher();
		else return instance;
	}
	
	/**
	 * 
	 * @param directory
	 * @return IndexSearcher
	 * @throws IOException
	 */
	public IndexSearcher createSearcher(String directory) throws IOException {
		Directory dir = FSDirectory.open(Paths.get(directory));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		return searcher;
	}
	
	public TopDocs queryFileContent(String query, IndexSearcher searcher, Analyzer analyzer) throws Exception {
		QueryParser qp = new QueryParser(Constants.CONTENTS, analyzer);
		Query queryParser = qp.parse(QueryParser.escape(query));
		TopDocs hits = searcher.search(queryParser,  200);
		return hits;
	}
}
