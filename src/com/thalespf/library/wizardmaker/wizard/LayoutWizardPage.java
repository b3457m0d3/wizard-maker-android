/*
 * Copyright 2013 Thales Ferreira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thalespf.library.wizardmaker.wizard;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.thalespf.library.wizardmaker.wizard.internal.ui.LayoutPageFragment;

/**
 * A default implementation of a wizard page that implements a page 
 * based on a layout provided by the user.
 * 
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public class LayoutWizardPage extends WizardPage {

	/**
	 * @param name
	 * @param layoutPage
	 */
	public LayoutWizardPage(String name, int layoutPage) {
		super(name, layoutPage);
	}

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizardPage#createFragment()
	 */
	@Override
	public Fragment createFragment() {
		//instantiate the fragment to this layoutPage layout
		this.pageFragment = new LayoutPageFragment(this, layoutPage);
		return this.pageFragment;
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.WizardPage#onCreateView(android.view.LayoutInflater, android.view.View)
	 */
	@Override
	public void onCreateView(LayoutInflater inflater, View rootView) {
		
	}

}
