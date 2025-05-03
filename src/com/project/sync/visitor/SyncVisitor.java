package com.project.sync.visitor;

import com.project.sync.filesystem.DirectoryElement;
import com.project.sync.filesystem.FileElement;
import com.project.sync.adapter.WebDavFileAdapter;
import com.project.sync.adapter.WebDavDirectoryAdapter;

public interface SyncVisitor {
	public void visitFile(FileElement file);
	public void visitDirectory(DirectoryElement directory);
	public void visitWebFile(WebDavFileAdapter webDavFile);
	public void visitWebDirectory(WebDavDirectoryAdapter webDavDir);
}
