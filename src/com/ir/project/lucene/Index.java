package com.ir.project.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;

import com.ir.project.util.Constants;

public class Index {
	
	private IndexWriter writer;
	
	public Index(String indexDirectory, Analyzer analyzer) throws IOException {
		CreateIndexer indexer = CreateIndexer.getInstance();
		this.writer = indexer.createIndexer(indexDirectory, analyzer);
	}
	
	public void deleteAll() throws IOException {
		this.writer.deleteAll();
	}
	
	public int startIndexing(String dir, FileFilter filter) throws IOException {
		File[] files = new File(dir).listFiles();
		for (File file : files) {
			if(!file.isDirectory() && !file.isHidden() 
					&& file.exists() && file.canRead() 
					&& filter.accept(file)) {
				indexFile(file);
			}
		}
		return this.writer.numDocs();
	}
	
	public void close() throws CorruptIndexException, IOException {
		this.writer.close();
	}
	
	private void indexFile(File file) throws IOException{
		System.out.println("Indexing "+file.getCanonicalPath());
		Document document = createDocument(file);
		writer.addDocument(document);
	}
	
	private Document createDocument(File file) throws IOException{
		Document doc = new Document();
		
		Field contentField = new TextField(Constants.CONTENTS, new FileReader(file));
		Field filenameField = new TextField(Constants.FILENAME, file.getName(), Field.Store.YES);
		Field filepathField = new TextField(Constants.FILEPATH, file.getAbsolutePath(), Field.Store.YES);
		
		doc.add(contentField);
		doc.add(filenameField);
		doc.add(filepathField);
		
		return doc;
	}
}
