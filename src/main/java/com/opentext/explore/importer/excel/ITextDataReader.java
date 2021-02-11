package com.opentext.explore.importer.excel;

import java.util.List;

import com.opentext.explore.importer.excel.pojo.TextDataImporterMapping;

public interface ITextDataReader {
	public List<CallRecording> read(String filePath, TextDataImporterMapping config);

}
