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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



/**
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public class BranchPage extends SingleFixedChoicePage {

	private List<BranchElement> branches;
	private BranchElement branched = null;
	private List<IWizardPage> actualBranchPages;

	/**
	 * @param name
	 */
	public BranchPage(String name) {
		super(name);
		branches = new ArrayList<BranchElement>();
	}
	
    public BranchPage addBranch(String choice, IWizardPage... childPages) {
        List<IWizardPage> pages = new ArrayList<IWizardPage>();
        for (IWizardPage iWizardPage : childPages) {
        	pages.add(iWizardPage);
		}
        //add choice of branch to this choice page
        choices.add(choice);
		branches.add(new BranchElement(choice, pages));
        return this;
    }
    
    private class BranchElement {
        public String choice;
        public List<IWizardPage> childPageList;

        private BranchElement(String choice, List<IWizardPage> childPageList) {
            this.choice = choice;
            this.childPageList = childPageList;
        }

		/**
		 * @return
		 */
		public String getChoice() {
			return choice;
		}

		/**
		 * @return
		 */
		public List<IWizardPage> getPages() {
			return childPageList;
		}
    }

	/**
	 * @return
	 */
	public List<IWizardPage> retrieveBranchPages() {
		if(getChoosedValues() != null && getChoosedValues() != "") {
			List<IWizardPage> pages = new ArrayList<IWizardPage>();
			
			for (Iterator<BranchElement> iterator = branches.iterator(); iterator.hasNext();) {
				BranchElement be = (BranchElement) iterator.next();
				if(be.getChoice().equals(getChoosedValues())) {
					branched = be;
					pages.addAll(be.getPages());
				}
			}
			actualBranchPages = pages;
			return pages;
		}
		return Collections.emptyList();
	}

	/**
	 * @return
	 */
	public boolean isBranched() {
		if(branched != null && getChoosedValues().equals(branched.getChoice())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return
	 */
	public List<IWizardPage> getBranchPages() {
		return actualBranchPages;
	}

	/**
	 * @return
	 */
	public boolean hasBranche() {
		return branched != null;
	}

}
