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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.thalespf.library.wizardmaker.wizard.internal.ChoiceWizardPage;
import com.thalespf.library.wizardmaker.wizard.internal.ui.SingleChoiceFragment;

/**
 * 
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public class SingleFixedChoicePage extends ChoiceWizardPage {

    /**
	 * @param name
	 */
	public SingleFixedChoicePage(String name) {
		super(name);
	}

	@Override
    public Fragment createFragment() {
		pageFragment = new SingleChoiceFragment(this);
        return pageFragment;
    }

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.internal.ChoiceWizardPage#getChoosedValues()
	 */
	@Override
	public String getChoosedValues() {
		if(getData().keySet().size() == 1) {
			return getData().keySet().iterator().next(); 
		}
		return null;
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.WizardPage#onValueChoosed(java.lang.String, java.lang.String)
	 */
	@Override
	public void onValueChoosed(String field, String value) {
		getData().clear();
		getData().put(field, value);
		
		if(getWizard() == null) {
			Log.e("WizardMaker", "The wizard for the page is null");
			return;
		}
		
		if(getWizard().getContainer() == null) {
			Log.e("WizardMaker", "The container for the wizard is null");
			return;
		}
		
		getWizard().getContainer().onValueChoosed();
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.WizardPage#onCreateView(android.view.LayoutInflater, android.view.View)
	 */
	@Override
	public void onCreateView(LayoutInflater inflater, View rootView) {
		
	}
    
}
