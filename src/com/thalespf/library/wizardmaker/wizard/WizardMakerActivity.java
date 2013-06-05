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

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.ActionBarSherlock.OnCreateOptionsMenuListener;
import com.thalespf.library.wizardmaker.wizard.internal.WizardContainer;

/**
 * 
 * @author Thales Ferreira / l.thales.x@gmail.com
 */
public abstract class WizardMakerActivity extends FragmentActivity implements OnCreateOptionsMenuListener {
	
	private ActionBarSherlock mSherlock = ActionBarSherlock.wrap(this);
	
	/**
	 * A factory method to leave the sub-classes decide what wizard to create. 
	 * Use this for create the wizard.
	 * 
	 * @return IWizard the user defined wizard
	 */
	protected abstract IWizard createWizard();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		IWizard wizard = createWizard();
		WizardContainer wizardContainer = new WizardContainer(this, wizard);
		wizardContainer.initWizard();
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
        return mSherlock.dispatchCreateOptionsMenu(menu);
	}

	/**
	 * @return
	 */
	public ActionBarSherlock getActionBarSherlock() {
		return mSherlock;
	}

}
