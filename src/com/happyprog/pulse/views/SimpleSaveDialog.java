package com.happyprog.pulse.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class SimpleSaveDialog implements SaveDialog {

	@Override
	public String open() {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setFilterExtensions(new String[] { "*.png" });

		return dialog.open();
	}

}
