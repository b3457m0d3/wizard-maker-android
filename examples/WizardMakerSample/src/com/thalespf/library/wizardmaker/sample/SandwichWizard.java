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
package com.thalespf.library.wizardmaker.sample;

import com.thalespf.library.wizardmaker.wizard.BranchPage;
import com.thalespf.library.wizardmaker.wizard.CustomerInfoPage;
import com.thalespf.library.wizardmaker.wizard.MultipleFixedChoicePage;
import com.thalespf.library.wizardmaker.wizard.SingleFixedChoicePage;
import com.thalespf.library.wizardmaker.wizard.Wizard;


/**
 * A sandwich wizard example based on the Roman Nurik's example. For Romam's wizard
 * go to (https://code.google.com/p/romannurik-code/).
 * 
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public class SandwichWizard extends Wizard {

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.Wizard#onCreatePages()
	 */
	@Override
	public void onCreatePages() {
		addPages(new BranchPage("Order Type")
					.addBranch("Sandwich", new SingleFixedChoicePage("Bread")
										   		.setChoices("White", "Wheat", "Rye", "Pretzel", "Ciabatta")
										   		.setRequired(true),
										   
										   new MultipleFixedChoicePage("Meats")
												.setChoices("Pepperoni", "Turkey", "Ham", "Pastrami",
							                            	"Roast Beef", "Bologna"),
							               
							               new MultipleFixedChoicePage("Veggies")
											        .setChoices("Tomatoes", "Lettuce", "Onions", "Pickles",
											                "Cucumbers", "Peppers"),
											
										   new MultipleFixedChoicePage("Cheeses")
											        .setChoices("Swiss", "American", "Pepperjack", "Muenster",
											                "Provolone", "White American", "Cheddar", "Bleu"),
										   
									       new BranchPage("Toasted?")
											        .addBranch("Yes",
											                new SingleFixedChoicePage("Toast time")
											                        .setChoices("30 seconds", "1 minute",
											                                "2 minutes"))
											        .addBranch("No")
											        .setChoiceSelected("No"))
											        
			        .addBranch("Salad",
	                        new SingleFixedChoicePage("Salad type")
	                                .setChoices("Greek", "Caesar")
	                                .setRequired(true),
	
	                        new SingleFixedChoicePage("Dressing")
	                                .setChoices("No dressing", "Balsamic", "Oil & vinegar",
	                                        "Thousand Island", "Italian")
	                                .setChoiceSelected("No dressing"))
	                
	                .setRequired(true),
		
			    new CustomerInfoPage("Your info").setRequired(true)
			);
	}

	/** (non-Javadoc)
	 * @see com.thalespf.library.wizardmaker.wizard.Wizard#performFinish()
	 */
	@Override
	public void performFinish() {
		// TODO Auto-generated method stub
		
	}

}
