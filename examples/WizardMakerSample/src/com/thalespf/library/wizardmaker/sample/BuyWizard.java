/**
 * 
 */
package com.thalespf.library.wizardmaker.sample;

import java.util.Arrays;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.thalespf.library.wizardmaker.wizard.IWizard;
import com.thalespf.library.wizardmaker.wizard.IWizardPage;
import com.thalespf.library.wizardmaker.wizard.LayoutWizardPage;
import com.thalespf.library.wizardmaker.wizard.Wizard;

/**
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public class BuyWizard extends Wizard implements IWizard {

	private IWizardPage shoppingCartPage;

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.Wizard#hookPages()
	 */
	@Override
	public void onCreatePages() {
		shoppingCartPage = new ShoppingCartPage("Shopping Cart", R.layout.cart_page_wizard);
		//add a page customized
		addPage(shoppingCartPage);
		//add a page defined in the layout
		addPage("Payment", R.layout.payment_page_wizard);
	}
	
	/**
	 * The buy car page.
	 * 
	 * This class is used to cooperate with the view of the page
	 */
	public class ShoppingCartPage extends LayoutWizardPage {

		/**
		 * @param name
		 * @param layoutPage
		 */
		public ShoppingCartPage(String name, int layoutPage) {
			super(name, layoutPage);
		}
		
		/**
		 * Cooperate to layout adding new elements on the list
		 * 
		 * @see com.thalespf.library.wizardmaker.wizard.LayoutWizardPage#onCreateView(android.view.LayoutInflater, android.view.View)
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

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.Wizard#performFinish()
	 */
	@Override
	public void performFinish() {
		//get a page and make a post processing
		//saving for example
		View view = shoppingCartPage.getView();
		ListView list = (ListView) view.findViewById(R.id.listView1);
		
		String products = "";
		//retrieve the products from the list
		for (int i = 0; i < list.getCount(); i++) {
			products += (String)list.getItemAtPosition(i) + " ";
		}
		
		Log.i("BuyWizard:products", products);
	}

}
