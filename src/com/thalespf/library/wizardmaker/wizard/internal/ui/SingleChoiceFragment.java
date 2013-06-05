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

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.thalespf.library.wizardmaker.wizard.IWizardPage;
import com.thalespf.library.wizardmaker.wizard.internal.ChoiceWizardPage;

public class SingleChoiceFragment extends ChoicePageFragment {

    /**
	 * @param page
	 */
	public SingleChoiceFragment(IWizardPage page) {
		super(page);
	}
	
	@Override
	public void setListAdapter(ListView listView, List<String> choices) {
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_single_choice,
                android.R.id.text1,
                choices));
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final ListView listView = (ListView) super.onCreateView(inflater, container, savedInstanceState);

        //Pre-select currently selected item.
        new Handler().post(new Runnable() {
            @Override
            public void run() {
				if(page instanceof ChoiceWizardPage) {
            		ChoiceWizardPage cwp = (ChoiceWizardPage)page;
            		
            		if(cwp.getChoosedValues() == null || cwp.getChoosedValues() == "") {
            			return;
            		}
            		
            		String selection = cwp.getChoosedValues();
            		List<String> choices = cwp.getChoices();
            		for (int i = 0; i < choices.size(); i++) {
            			if (choices.get(i).equals(selection)) {
            				listView.setItemChecked(i, true);
            				break;
            			}
            		}
				}
            }
        });

        return rootView;
    }
}
