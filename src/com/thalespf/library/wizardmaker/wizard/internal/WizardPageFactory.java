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

import com.thalespf.library.wizardmaker.wizard.IWizardPage;
import com.thalespf.library.wizardmaker.wizard.LayoutWizardPage;

/**
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public class WizardPageFactory {

	/**
	 * @param container
	 * @param layoutPage
	 * @param pageName 
	 * @return
	 */
	public static IWizardPage createPage(String pageName, int layoutPage) {
		if(layoutPage > 0)
			return new LayoutWizardPage(pageName, layoutPage);
		return null;
	}

}
