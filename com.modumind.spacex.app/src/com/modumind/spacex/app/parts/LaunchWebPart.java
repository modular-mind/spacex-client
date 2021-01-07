package com.modumind.spacex.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class LaunchWebPart {

	private Browser browser;
	
	@PostConstruct
	public void createComposite(Composite parent) {
		createBrowser(parent);
	}

	@Focus
	public void setFocus() {
		this.browser.setFocus();
	}

	@Persist
	public void save() {
	}

	private void createBrowser(Composite parent) {
		this.browser = new Browser(parent, SWT.CHROMIUM);
		Display.getDefault().asyncExec(() -> {
			browser.setUrl("http://localhost:8080/spacex/launches.html");
		});
	}
}