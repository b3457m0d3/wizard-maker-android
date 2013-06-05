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
package com.thalespf.library.wizardmaker.wizard.internal.shifter;

import android.view.View;
import android.view.View.OnClickListener;

import com.thalespf.library.wizardmaker.R;
import com.thalespf.library.wizardmaker.wizard.IWizardPage;
import com.thalespf.library.wizardmaker.wizard.WizardMakerActivity;
import com.thalespf.library.wizardmaker.wizard.internal.WizardContainer.WizardContainerController;

/**
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public abstract class AbstractShifter {
	
	protected View shifterView;
	protected WizardContainerController controller;
	protected WizardMakerActivity wizardActivity;

	/**
	 * @param controller
	 */
	public AbstractShifter(WizardContainerController controller) {
		this.controller = controller;
	}

	/**
	 * @param wizardActivity 
	 * @return
	 */
	public View getShifterView(WizardMakerActivity wizardActivity) {
		this.wizardActivity = wizardActivity;
		shifterView = onCreateShifterView();
		
		registerListeners(shifterView);
		
		return shifterView;
	}

	/**
	 * @param shifterView
	 */
	private void registerListeners(View shifterView) {
		View leftButton = (View) shifterView.findViewById(R.id.shifterLeftButton);
		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				controller.wizardPagePreviousChanged();
			}
			
		});
		
		View rigthButton = (View) shifterView.findViewById(R.id.shifterRigthButton);
		rigthButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				controller.wizardPageNextChanged();
			}
			
		});
		
		View finishButton = (View) shifterView.findViewById(R.id.shifterFinishButton);
		if(finishButton != null) {
			finishButton.setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View view) {
					controller.onFinishWizard();
				}
				
			});
		}
	}

	/**
	 * @return
	 */
	protected abstract View onCreateShifterView();

	/**
	 * @param page 
	 * @param viewPager 
	 * @param wizard 
	 * @param i
	 */
	public void updateShifter(IWizardPage page, int currentItem, int lastItem) {
		View leftButton = (View) shifterView.findViewById(R.id.shifterLeftButton);
		if(currentItem == 0) {
			leftButton.setEnabled(false);
		} else {
			leftButton.setEnabled(true);
		}
	}

}
