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

import java.util.List;


/**
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public interface IChoiceWizardPage {
	
	/**
	 * Returns each field from the page in form of ReviewItem 
	 * to appear in the review page
	 * 
	 * @return a review items list
	 */
	public List<ReviewItem> getReviewItems();

	/**
	 * @param choices
	 * @return
	 */
	public ChoiceWizardPage setChoices(String... choices);
	
	public ChoiceWizardPage setChoices(List<String> choices);

	/**
	 * @param choice
	 * @return
	 */
	public ChoiceWizardPage setChoiceSelected(String choice);

}
