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

package com.thalespf.library.wizardmaker.wizard.internal.ui;

import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.thalespf.library.wizardmaker.R;
import com.thalespf.library.wizardmaker.wizard.IWizardPage;
import com.thalespf.library.wizardmaker.wizard.internal.ChoiceWizardPage;

/**
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public abstract class ChoicePageFragment extends ListFragment implements IChoicePageFragment {
	
	protected IWizardPage page;
	protected View rootView;

	/**
	 * 
	 * @param layoutPage
	 */
	public ChoicePageFragment(IWizardPage page) {
		this.page = page;
	}

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.internal.ui.IPageFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_page, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(page.getName());

        final ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        
        List<String> choices = getChoices();
		setListAdapter(listView, choices);
        
        return listView;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
        String field = (String) l.getItemAtPosition(position);
		
        if(page.getChoosedValue(field) == null) {
	        String value = ChoiceWizardPage.VALUES_SELECTED;
			page.onValueChoosed(field, value);
        } else {
        	//esta deselecionando
        	page.removeValueChoosed(field);
        }
	}

	/**
	 * @return
	 */
	public List<String> getChoices() {
		if(page instanceof ChoiceWizardPage) {
			return ((ChoiceWizardPage)page).getChoices();
		}
		return Collections.emptyList();
	}

	/**
	 * @param listView
	 */
	public abstract void setListAdapter(ListView listView, List<String> choices);

}
