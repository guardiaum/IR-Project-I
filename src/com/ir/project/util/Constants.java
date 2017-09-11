package com.ir.project.util;

public class Constants {
	// Project config
	public static final String PROJECT_DIR = System.getProperty("user.dir");
	public static final String DOCS_DIR = PROJECT_DIR + "/arxiv-documents";
		
	public static final String CONTENTS = "contents";
	public static final String FILENAME = "filename";
	public static final String FILEPATH = "filepath";
	
	public static final String STANDARD_INDEX_PATH = "/tmp/standard/";
	public static final String STOPWORDS_INDEX_PATH = "/tmp/stop/";
	public static final String STEMMING_INDEX_PATH = "/tmp/stem/";
	public static final String STOPSTEM_INDEX_PATH = "/tmp/stopstem/";
	
	public static final String DOCUMENTS_DS = DOCS_DIR + "/cs.DS";
	public static final String DOCUMENTS_IR = DOCS_DIR + "/cs.IR";
	public static final String DOCUMENTS_LG = DOCS_DIR + "/cs.LG";
	public static final String DOCUMENTS_NE = DOCS_DIR + "/cs.NE";
	public static final String DOCUMENTS_ML = DOCS_DIR + "/stat.ML";
}
