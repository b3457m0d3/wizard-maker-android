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
package com.thalespf.library.wizardmaker.wizard.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.support.v4.app.Fragment;

import com.thalespf.library.wizardmaker.wizard.WizardPage;

/**
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public abstract class ChoiceWizardPage extends WizardPage implements IChoiceWizardPage {

	public static final String VALUES_SELECTED = "selected";
	
    protected ArrayList<String> choices = new ArrayList<String>();

	/**
	 * @param name
	 */
	public ChoiceWizardPage(String name) {
		super(name);
	}

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizardPage#createFragment()
	 */
	@Override
	public abstract Fragment createFragment();

	/**
	 * Returns the choices from this page
	 * 
	 * @return the choices
	 */
	public List<String> getChoices() {
		return choices;
	}

	/**
	 * 
	 * @return string representing the choices
	 */
	public abstract String getChoosedValues();

	/**
	 * The default implementation returns the review item
	 * with the page name and values choosed from fragment
	 * 
	 * @see com.thalespf.library.wizardmaker.wizard.WizardPage#getReviewItems()
	 */
	@Override
	public List<ReviewItem> getReviewItems() {
		List<ReviewItem> items = new ArrayList<ReviewItem>();
		
		if(pageFragment != null) {
			ReviewItem ri = new ReviewItem(getName(), getChoosedValues());
			items.add(ri);
		}
		return items;
	}

    @Override
    public boolean isPageComplete() {
    	if(!isRequired())
    		return true;
    	else
    		return !getData().isEmpty();
    }
    
    @Override
    public ChoiceWizardPage setChoices(String... ch) {
        choices.addAll(Arrays.asList(ch));
        return this;
    }
    
    /** (non-Javadoc)
     * @see com.thalespf.library.wizardmaker.wizard.internal.IChoiceWizardPage#setChoices(java.util.List)
     */
    @Override
    public ChoiceWizardPage setChoices(List<String> choices) {
        this.choices.addAll(choices);
        return this;
    }

    @Override
    public ChoiceWizardPage setChoiceSelected(String choice) {
        getData().put(choice, ChoiceWizardPage.VALUES_SELECTED);
        return this;
    }

}
