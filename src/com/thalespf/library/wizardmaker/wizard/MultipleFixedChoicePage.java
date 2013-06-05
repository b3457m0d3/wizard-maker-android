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

import java.util.Iterator;
import java.util.Set;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.thalespf.library.wizardmaker.wizard.internal.ChoiceWizardPage;
import com.thalespf.library.wizardmaker.wizard.internal.ui.MultipleChoiceFragment;

/**
 * 
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public class MultipleFixedChoicePage extends ChoiceWizardPage {

    /**
	 * @param name
	 */
	public MultipleFixedChoicePage(String name) {
		super(name);
	}

	@Override
    public Fragment createFragment() {
    	pageFragment = new MultipleChoiceFragment(this);
        return pageFragment;
    }

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.internal.ChoiceWizardPage#getChoosedValues()
	 */
	@Override
	public String getChoosedValues() {
		if(getData().size() > 0) {
			Set<String> keys = getData().keySet();
			StringBuffer sb = new StringBuffer();
			for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				sb.append(key + ", ");
			}
			String s = sb.toString().trim();
			return s.substring(0, s.length() - 1);
		}
		return null;
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.WizardPage#onCreateView(android.view.LayoutInflater, android.view.View)
	 */
	@Override
	public void onCreateView(LayoutInflater inflater, View rootView) {
		
	}
}
