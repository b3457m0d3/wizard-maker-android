/*
 * Copyright 2012 Roman Nurik
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

import com.thalespf.library.wizardmaker.wizard.internal.ui.CustomerInfoFragment;


/**
 * 
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public class CustomerInfoPage extends WizardPage {
	
    /**
	 * @param name
	 */
	public CustomerInfoPage(String name) {
		super(name);
	}

	public static final String NAME_DATA_KEY = "name";
    public static final String EMAIL_DATA_KEY = "email";

    @Override
    public boolean isPageComplete() {
        return getChoosedValue(NAME_DATA_KEY) != null && getChoosedValue(NAME_DATA_KEY) != "" ? true :
        	false;
    }

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizardPage#createFragment()
	 */
	@Override
	public Fragment createFragment() {
		pageFragment = new CustomerInfoFragment(this);
		return pageFragment;
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.WizardPage#onCreateView(android.view.LayoutInflater, android.view.View)
	 */
	@Override
	public void onCreateView(LayoutInflater inflater, View rootView) {
		
	}
}
