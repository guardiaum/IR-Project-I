package com.ir.project.util;

import java.io.File;
import java.io.FileFilter;

public class TextFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		return pathname.getName().endsWith(".txt");
	}

}
