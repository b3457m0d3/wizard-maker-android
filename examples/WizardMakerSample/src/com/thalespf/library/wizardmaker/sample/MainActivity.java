package com.thalespf.library.wizardmaker.sample;

import android.os.Bundle;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.thalespf.library.wizardmaker.wizard.IWizard;
import com.thalespf.library.wizardmaker.wizard.WizardMakerActivity;

public class MainActivity extends WizardMakerActivity {

	private static final int MENU_SAVE = Menu.FIRST;
	private static final int MENU_REFRESH = MENU_SAVE + 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.wizard, menu);

        menu.add(0, MainActivity.MENU_SAVE, 0, "Save")
            .setIcon(android.R.drawable.ic_menu_more)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menu.add(0, MainActivity.MENU_REFRESH, 2, "Refresh")
            .setIcon(android.R.drawable.ic_menu_more)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
	}

	/* (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.WizardMakerActivity#createWizard()
	 */
	@Override
	protected IWizard createWizard() {
		return createBuyWizard();
		//return createSandwich();
	}

	/**
	 * A wizard based on the example of the Roman Nurick 
	 * (https://code.google.com/p/romannurik-code/)
	 * 
	 * The wizard is configured to have the next and previous buttons
	 * below to the page and one review page at the final of the wizard. 
	 * 
	 * @return sandwich wizard
	 * 
	 */
	private IWizard createSandwich() {
		IWizard wizard = new SandwichWizard();
		wizard.configure(IWizard.PROPERTY__SHIFTER_VIEW_LOCATION, IWizard.VALUE__SHIFTER_VIEW_LOCATION_BELOW);
		wizard.configure(IWizard.PROPERTY__REVIEW_PAGE, true);
		return wizard;
	}

	/**
	 * A wizard that demonstrate a shooping car
	 * 
	 * The wizard is configured to have the next and previous buttons at the 
	 * top of the page
	 * 
	 * @return buy wizard
	 */
	private IWizard createBuyWizard() {
		IWizard wizard = new BuyWizard();
		wizard.configure(IWizard.PROPERTY__SHIFTER_VIEW_LOCATION, IWizard.VALUE__SHIFTER_VIEW_LOCATION_ABOVE);
		wizard.configure(IWizard.PROPERTY__REVIEW_PAGE, false);
		return wizard;
	}


}
