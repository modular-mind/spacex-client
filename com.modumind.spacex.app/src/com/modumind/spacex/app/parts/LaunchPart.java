package com.modumind.spacex.app.parts;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.e4.core.di.extensions.Service;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;

import com.modumind.spacex.service.LaunchService;
import com.modumind.spacex.service.model.Launch;

public class LaunchPart {

	@Inject
	@Service
	private LaunchService launchService;

	private TableViewer launchViewer;

	@PostConstruct
	public void createComposite(Composite parent) {
		createTableViewer(parent);

		/*
		 * All you need to do to toggle between synchronous and asynchronous calls is to
		 * switch the comments.
		 */
		retrieveLaunches();
//		retrieveLaunchesAsync();
	}

	@Focus
	public void setFocus() {
		launchViewer.getTable().setFocus();
	}

	@Persist
	public void save() {
	}

	private void createTableViewer(Composite parent) {
		launchViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		launchViewer.getTable().setHeaderVisible(true);
		launchViewer.getTable().setLinesVisible(true);

		TableColumn tblColumnDate = new TableColumn(launchViewer.getTable(), SWT.NONE);
		tblColumnDate.setWidth(150);
		tblColumnDate.setText("Flight Number");

		TableColumn tblColumnMsg = new TableColumn(launchViewer.getTable(), SWT.NONE);
		tblColumnMsg.setWidth(300);
		tblColumnMsg.setText("Mission Name");
	}

	private void retrieveLaunches() {
		List<Launch> launches = launchService.getLaunches();
		setLaunchesIntoTable(launches);
		WritableList<Launch> input = new WritableList<Launch>(launches, Launch.class);
		ViewerSupport.bind(launchViewer, input, PojoProperties.values(new String[] { "flightNumber", "missionName" }));
	}

	private void retrieveLaunchesAsync() {
		CompletableFuture<List<Launch>> launchesFuture = null;
		launchesFuture = launchService.getLaunchesAsync();

		if (launchesFuture != null) {
			launchesFuture.thenAccept((launches) -> {
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						setLaunchesIntoTable(launches);
					}
				});
			});
		}
	}

	private void setLaunchesIntoTable(List<Launch> launches) {
		WritableList<Launch> input = new WritableList<Launch>(launches, Launch.class);
		ViewerSupport.bind(launchViewer, input, PojoProperties.values(new String[] { "flightNumber", "missionName" }));
	}
}