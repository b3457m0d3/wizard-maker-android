Android Wizard Maker Lib
========================

An android library to help developers creating easy and fast wizard based UI's in Android

Wizard Maker is a framework to facilitate the development of the UI designed to representation of complex user input which are provided in a sequence of steps. This type of interface is used sometimes more effective to guide the user through the process step-by-step.

The purpose of the library is to abstract the concepts of the Android to develop a wizard using only extensions to classes provided in the library. The user cares only in specify the wizard and its pages from base classes provided. The rest is done by the Wizard Maker. 
The wizard implemetation use ViewPager as the basis for transitioning between the pages. All the pages defined by the user are inserted and treated automatically as fragments of ViewPager.

Features:

    - two types of button bar to make the transitions of the pages;
    - option to include a page for review at the end of the wizard;
    - page creation from a layout resource provided by the user;
    - pages with predefined data representing multiple choice or single choice (similar to the 
    types of pages defined by Roman Nurick in your wizardpager (https://code.google.com/p/romannurik-code/) ).

This library is used in conjunction with the library ActionBarSherlock (https://github.com/JakeWharton/ActionBarSherlock).

This work was inspired by WizardPage from Roman Nurick (https://code.google.com/p/romannurik-code/).

Application Example
=====
To view an application example using the framework access in google play.  https://play.google.com/store/apps/details?id=com.thalespf.library.wizardmaker.samplelist

Usage
=====

The wizard constitutes an activity in android that is responsible for displaying and controlling the pages of the wizard. The Wizard Maker Lib provides the base class WizardMakerActivity for the user to extend and add your wizard created. To create a wizard, the framework provides the base class WizardMakerActivity Wizard, where the user can use as a basis to define your own wizard. In this class, there is a method to include the user's pages within the wizard. A page is defined using as a base class WizardPage. The following steps illustrate the creation of a wizard.

1 - Creation of the activity that displays the wizard

The user can use as a base the abstract class WizardMakerActivity provided by Wizard Maker Lib. Through the method createWizard the activity displays the wizard of the user. The user's responsibility is to instantiate an object wizard that he set, as shown in the next step.

2 - Wizard's creation

The user creates a wizard from the Wizard class. This class stores the pages of the wizard. In the method onCreatePages the user adds pages. For example, a wizard shopping cart having two pages, the first page of the products display and the second payment page could thus be defined:

    public class ShoppingWizard extends Wizard {
    
    	@Override
    	public void onCreatePages() {
    		IWizardPage productsPage = new ProductsPage("Products", R.layout.cart_page_wizard);
    		//add a page customized
    		addPage(productsPage);
    		//add a page defined in the layout
    		addPage("Payment", R.layout.payment_page_wizard);
    	}
    
    }

As seen, the method onCreatePages is used to create pages and add them to the wizard. This example shows the use of two types of pages: the first type are pages created by the user using as a base the class LayoutWizardPage and other pages are defined only through its layout resource feature. The first page (BuyCarPage) is defined from the class LayoutWizardPage. It used to add the products in the page view. The second page is defined using only the method addPage passing your name and layout feature. The next step shows how it created the products page.

3 - Creation of product page

	/**
	 * The buy ProductsPage.
	 * 
	 * This class is used to cooperate with the view of the page
	 */
	public class ProductsPage extends LayoutWizardPage {

		public ProductsPage(String name, int layoutPage) {
			super(name, layoutPage);
		}
		
		/**
		 * Cooperate to layout adding new elements on the list
		 * 
		 * @see com.thalespf.library.wizardmaker.wizard.LayoutWizardPage#onCreateView(android.view.LayoutInflater, 		
			android.view.View)
		 */
		@Override
		public void onCreateView(LayoutInflater inflater, View rootView) {
			ListView list = (ListView) rootView.findViewById(R.id.listView1);
			String[] objects = {"Orange", "Apple", "Potate"};
			ListAdapter listAdapter = new ArrayAdapter<String>(getContext(), 
					android.R.layout.simple_list_item_1, Arrays.asList(objects));
			list.setAdapter(listAdapter);
		}
		
	}

A page is defined by LayoutWizardPage class that has a name and id of the layout resource feature. The list of products to be displayed are defined by the method onCreateView.

This complete example is included in the code.

Developed By
============

* Thales Ferreira - l.thales.x@gmail.com

License
=======

    Copyright 2013 Thales Ferreira
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
