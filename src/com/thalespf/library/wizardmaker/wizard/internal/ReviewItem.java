/*
 * Copyright 2013 Thales Ferreira
 * Copyright 2012 Roman Nurik
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

/**
 * Represents a single line item on the final review page.
 * Implementation based on Roman Nurik code
 * 
 * @author Thales Ferreira / l.thales.x@gmail.com
 *
 */
public class ReviewItem {
    public static final int DEFAULT_WEIGHT = 0;

    private int mWeight;
    private String mTitle;
    private String mDisplayValue;

    public ReviewItem(String title, String displayValue) {
        this(title, displayValue, DEFAULT_WEIGHT);
    }

    public ReviewItem(String title, String displayValue, int weight) {
        mTitle = title;
        mDisplayValue = displayValue;
        mWeight = weight;
    }

    public String getDisplayValue() {
        return mDisplayValue;
    }

    public void setDisplayValue(String displayValue) {
        mDisplayValue = displayValue;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int weight) {
        mWeight = weight;
    }
}
