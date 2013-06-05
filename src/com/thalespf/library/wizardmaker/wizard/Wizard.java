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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thalespf.library.wizardmaker.wizard.internal.IWizardContainer;
import com.thalespf.library.wizardmaker.wizard.internal.WizardPageFactory;


/**
 * An abstract default implementation of a wizard. A typical client subclasses
 * <code>Wizard</code> to implement a particular wizard. The subclasses may create 
 * the wizard pages in method <code>hookPages</code> and call <code>addPage</code> to 
 * put the pages in the wizard. 
 * 
 * @author Thales Ferreira / l.thales.x@gmail.com
 */
public abstract class Wizard implements IWizard {
	
    private List<IWizardPage> pages = new ArrayList<IWizardPage>();
	private IWizardContainer wizardContainer;
	private IWizardPage actualPage = null;
	private int shifterViewLocation;
	private Boolean isReviewPage;
	
	/**
	 * 
	 */
	public Wizard() {
		configure(PROPERTY__SHIFTER_VIEW_LOCATION, VALUE__SHIFTER_VIEW_LOCATION_ABOVE);
		configure(PROPERTY__REVIEW_PAGE, false);
	}
    
    /**
     * Adds a new page to this wizard. 
     * 
     * @param page the IWizardPage instance of the page
     */
	@Override
	public void addPage(IWizardPage page) {
    	page.setWizard(this);
    	pages.add(page);
    	
    	if(actualPage == null)
    		actualPage = pages.get(0);
    }
	
	protected void addPages(IWizardPage... ps) {
		for (IWizardPage iWizardPage : ps) {
			iWizardPage.setWizard(this);
			pages.add(iWizardPage);
		}
		
    	if(actualPage == null)
    		actualPage = pages.get(0);
	}
    
    /**
     * Adds a new page to this wizard. 
     * 
     * @param pageName 
     * @param layoutPage the id of the layout resource
     */
    protected void addPage(String pageName, int layoutPage) {
    	IWizardPage page = WizardPageFactory.createPage(pageName, layoutPage);
    	addPage(page);
    }

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#onCreatePages()
	 */
	@Override
	public abstract void onCreatePages();

	@Override
	public IWizardContainer getContainer() {
		return wizardContainer;
	}

	/** 
	 * Default cancel implementation.
	 * 
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#performCancel()
	 */
	@Override
	public void performCancel() {
		
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#setContainer(com.thalespf.library.wizardmaker.wizard.internal.IWizardContainer)
	 */
	@Override
	public void setContainer(IWizardContainer wizardContainer) {
		this.wizardContainer = wizardContainer;
	}

	/**
	 * The client define the finish tasks
	 * 
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#performFinish()
	 */
	@Override
	public abstract void performFinish();

	@Override
	public boolean canFinish() {
		if(getLastPage().isPageComplete()) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public IWizardPage getLastPage() {
		return pages.get(pages.size() - 1);
	}

	@Override
	public int getPagesSize() {
		return pages.size();
	}

	@Override
	public List<IWizardPage> getPages() {
		return pages;
	}

	@Override
	public int getNextPage() {
		if(actualPage != null) {
			IWizardPage next = getNextPage(actualPage);
			if(next == null)
				return -1;
			else		
				return pages.indexOf(next);
		}
		return -1;
	}
	
	@Override
	public int getPreviousPage() {
		if(actualPage != null) {
			IWizardPage prev = getPreviousPage(actualPage);
			if(prev == null)
				return -1;
			else		
				return pages.indexOf(prev);
		}
		return -1;
	}

	/**
	 * @param page
	 * @return
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if(isLastPage(page)) {
			return null;
		}
		
		if(pages.indexOf(page) == -1) {
			return null;
		}
		
		if(pages.indexOf(page) + 1 < pages.size()) {
			IWizardPage next = pages.get(pages.indexOf(page) + 1);
			return next;
		} else {
			return null;
		}
	}

	/**
	 * @param page
	 * @return
	 */
	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		if(isFirstPage(page)) {
			return null;
		}
		
		if(pages.indexOf(page) == -1) {
			return null;
		}
		
		if(pages.indexOf(page) - 1 >= 0) {
			IWizardPage prev = pages.get(pages.indexOf(page) - 1);
			return prev;
		} else {
			return null;
		}
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#getPage()
	 */
	@Override
	public IWizardPage getPage() {
		return actualPage;
	}

	/**
	 * @param page
	 * @return
	 */
	private boolean isFirstPage(IWizardPage page) {
		if(pages.indexOf(page) == 0)
			return true;
		return false;
	}
	
	/**
	 * @param page
	 * @return
	 */
	private boolean isLastPage(IWizardPage page) {
		if(pages.indexOf(page) == pages.size() - 1)
			return true;
		return false;
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#getPage(com.thalespf.library.wizardmaker.wizard.IWizardPage)
	 */
	@Override
	public IWizardPage getPage(IWizardPage page) {
		for (Iterator<IWizardPage> iterator = pages.iterator(); iterator.hasNext();) {
			IWizardPage p = (IWizardPage) iterator.next();
			if(p.equals(page)) {
				return p;
			}
		}
		return null;
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#setCurrentPage(int)
	 */
	@Override
	public void setCurrentPage(int position) {
		if(position >=0 && position < pages.size())
			actualPage = pages.get(position);
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#breakDownBranchPage()
	 */
	@Override
	public void breakDownBranchPage() {
		if(actualPage instanceof BranchPage) {
			if(!((BranchPage)actualPage).isBranched()) {
				if(((BranchPage)actualPage).hasBranche()) {
					//remove the actual branched pages
					int size = ((BranchPage)actualPage).getBranchPages().size();
					removePages(getPagePosition(actualPage), size);
				}
				
				List<IWizardPage> ps = ((BranchPage)actualPage).retrieveBranchPages();
				int j = getPagePosition(actualPage);
				for (int i = 0; i < ps.size(); i++) {
					IWizardPage iWizardPage = ps.get(i);
					addPage(j + 1, iWizardPage);
					j++;
				}
			}
		}
	}
	
	/**
	 * @param start
	 * @param end
	 */
	private void removePages(int from, int size) {
		for (int i = 0; i < size; i++) {
			pages.remove(from + 1);			
		}
	}

	/**
	 * @param i
	 * @param iWizardPage
	 */
	private void addPage(int i, IWizardPage iWizardPage) {
		iWizardPage.setWizard(this);
		pages.add(i, iWizardPage);
	}

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#onEditScreenAfterReview(com.thalespf.library.wizardmaker.wizard.IChoiceWizardPage)
	 */
	@Override
	public void onEditScreenAfterReview(int position) {
		getContainer().onEditScreenAfterReview(position);
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#getPagePosition(com.thalespf.library.wizardmaker.wizard.IWizardPage)
	 */
	@Override
	public int getPagePosition(IWizardPage page) {
		return pages.indexOf(page);
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#configure(int, int)
	 */
	@Override
	public void configure(Object property, Object value) {
		if(property == PROPERTY__SHIFTER_VIEW_LOCATION) {
			if(value instanceof Integer)
				shifterViewLocation = (Integer) value;
		} else if(property == PROPERTY__REVIEW_PAGE) {
			if(value instanceof Boolean)
				isReviewPage = (Boolean) value; 
		}
	}
	
	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.IWizard#getShifterViewLocation()
	 */
	@Override
	public int getShifterViewLocation() {
		return shifterViewLocation;
	}
	
	/**
	 * @return the isReviewPage
	 */
	@Override
	public boolean isReviewPage() {
		return isReviewPage;
	}

}
