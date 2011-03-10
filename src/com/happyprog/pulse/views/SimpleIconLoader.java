package com.happyprog.pulse.views;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;

public class SimpleIconLoader implements IconLoader {

	@Override
	public ImageDescriptor load(String imagePath) {
		Bundle bundle = Platform.getBundle("com.happyprog.pulse");
		return (ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(imagePath), null)));
	}

}
