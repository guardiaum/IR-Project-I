package com.ir.project.lucene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

/**
 * Singleton for indexer creation
 * @author jms5
 *
 */
public class CreateIndexer {
	private static CreateIndexer instance = null;
	
	private CreateIndexer() {}
	
	public static CreateIndexer getInstance() {
		if(instance==null)
			instance = new CreateIndexer();
		return instance;
	}
	
	/**
	 * Creates the indexer
	 * 
	 * @param indexDirectoryPath
	 * @return indexWriter
	 * @throws IOException
	 */
	public IndexWriter createIndexer(String indexDirectoryPath, Analyzer analyzer) throws IOException{
		Path path = Paths.get(indexDirectoryPath);
		/*
		 * Verifies existence of destine index directory.
		 * If it doesn't exists, it creates with all needed subdirectories.
		 * In case of some problem occurs it trows an IOException 
		 */
		if(Files.createDirectories(path) != null) {
			FSDirectory indexDirectory = FSDirectory.open(path);
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			IndexWriter indexWriter = new IndexWriter(indexDirectory, config);
			return indexWriter;
		}else
			throw new IOException();
	}
	
}
