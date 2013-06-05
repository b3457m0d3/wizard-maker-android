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

import java.util.List;

import com.thalespf.library.wizardmaker.wizard.internal.IWizardContainer;


/**
 * 
 * @author Thales Ferreira / l.thales.x@gmail.com
 */
public interface IWizard {
	
	public final static Integer PROPERTY__SHIFTER_VIEW_LOCATION = 0;

	public final static Integer VALUE__SHIFTER_VIEW_LOCATION_ABOVE = 100;
	
	public final static Integer VALUE__SHIFTER_VIEW_LOCATION_BELOW = 101;
	
	public final static Integer
	PROPERTY__REVIEW_PAGE = new Integer(PROPERTY__SHIFTER_VIEW_LOCATION) + 1;
	
    /**
     * Add the pages to wizard using method call form <code>addPage(IWizardPage)</code> receiving
     * an instance of WizardPage or the form <code>addPage(int)</code> receiving
     * the integer of xml page layout.
     */
	public void onCreatePages();
	
	/**
	 * Perform any actions in case of user cancel the wizard
	 *  
	 */
	public void performCancel();

	/**
	 * Perform actions when wizard finishs
	 *  
	 */
	public void performFinish();
	
    /**
     * Indicate if wizard could be finished based if last page in wizard
     * is complete or not
     * 
     * <p>
     * The result is used by the wizard activity to enable or disable the 
     * Finish button.
     * </p>
     *
     * @return <code>true</code> if the wizard could be finished,
     *   and <code>false</code> otherwise
     */
    public boolean canFinish();
    
    /**
     * Returns the size of pages
     *
     * @return the size of wizard pages
     */
    public int getPagesSize();

    /**
     * Returns all the pages
     *
     * @return a list of pages
     */
    public List<IWizardPage> getPages();
    
    /**
     * Returns the wizard container that host this wizard
     * 
     * @return IWizardContainer the container
     */
    public IWizardContainer getContainer();
    
    /**
     * Sets the container to wizard
     *
     * @param wizardContainer the wizard container, or <code>null</code> 
     */
    public void setContainer(IWizardContainer wizardContainer);

	/**
	 * Returns a next page position from the actual page.
	 * If was last page the last page returned.
	 * 
	 * @return position -1 if there isnt a next page
	 */
	public int getNextPage();
	
	/**
	 * Returns a previous page position from the actual page.
	 * If was first page the first page returned. 
	 * 
	 * @return position -1 if there isnt no pages in wizard
	 */
	public int getPreviousPage();
	
	public IWizardPage getPreviousPage(IWizardPage page);
	
	public IWizardPage getNextPage(IWizardPage page);

	/**
	 * @param choiceWizardPage
	 * @return
	 */
	public IWizardPage getPage(IWizardPage page);

	/**
	 * @param position
	 */
	public void onEditScreenAfterReview(int position);

	/**
	 * @param choicePage
	 * @return
	 */
	public int getPagePosition(IWizardPage choicePage);
	
	public void configure(Object property, Object value);
	
	public int getShifterViewLocation();

	/**
	 * @return
	 */
	public IWizardPage getPage();

	/**
	 * Break down the actual branch page
	 * 
	 */
	public void breakDownBranchPage();

	/**
	 * @return
	 */
	public boolean isReviewPage();

	/**
	 * @param rWizardPage
	 */
	public void addPage(IWizardPage page);

	/**
	 * @param position
	 */
	public void setCurrentPage(int position);

}
