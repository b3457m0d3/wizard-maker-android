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
package com.thalespf.library.wizardmaker.wizard.internal.ui;

import android.view.View;
import android.widget.ListView;

/**
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public interface IChoicePageFragment extends IPageFragment {
	
	public abstract void onListItemClick(ListView l, View v, int position, long id);

}
