/*
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

package com.thalespf.library.wizardmaker.wizard.internal.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.thalespf.library.wizardmaker.R;
import com.thalespf.library.wizardmaker.wizard.IWizard;
import com.thalespf.library.wizardmaker.wizard.IWizardPage;
import com.thalespf.library.wizardmaker.wizard.internal.IChoiceWizardPage;
import com.thalespf.library.wizardmaker.wizard.internal.ReviewItem;

public class ReviewFragment extends ListFragment {
	
    private List<ReviewItem> mCurrentReviewItems;

    private ReviewAdapter mReviewAdapter;

	private IWizardPage page;

    public ReviewFragment(IWizardPage page) {
    	this.page = page;
        mReviewAdapter = new ReviewAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);

        TextView titleView = (TextView) rootView.findViewById(android.R.id.title);
        titleView.setText(R.string.review);
        titleView.setTextColor(getResources().getColor(R.color.review_green));
        
        mCurrentReviewItems = getPagesReviews();
        
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        setListAdapter(mReviewAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        return rootView;
    }
    
    public List<ReviewItem> getPagesReviews() {
        ArrayList<ReviewItem> reviewItems = new ArrayList<ReviewItem>();
        
        List<IWizardPage> pages = page.getWizard().getPages();
        for (Iterator<IWizardPage> iterator = pages.iterator(); iterator.hasNext();) {
			IWizardPage iWizardPage = (IWizardPage) iterator.next();
			if(iWizardPage instanceof IChoiceWizardPage) {
				IChoiceWizardPage p = (IChoiceWizardPage) iWizardPage;
				reviewItems.addAll(p.getReviewItems());
			}
		}        
        
        Collections.sort(reviewItems, new Comparator<ReviewItem>() {
            @Override
            public int compare(ReviewItem a, ReviewItem b) {
                return a.getWeight() > b.getWeight() ? +1 : a.getWeight() < b.getWeight() ? -1 : 0;
            }
        });

        if (mReviewAdapter != null) {
            mReviewAdapter.notifyDataSetInvalidated();
        }
		return reviewItems;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	IWizard wizard = page.getWizard();
    	if(wizard != null) {
    		wizard.onEditScreenAfterReview(position);
    	}
    }

    private class ReviewAdapter extends BaseAdapter {
        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public Object getItem(int position) {
            return mCurrentReviewItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mCurrentReviewItems.get(position).hashCode();
        }

        @Override
        public View getView(int position, View view, ViewGroup container) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View rootView = inflater.inflate(R.layout.list_item_review, container, false);

            ReviewItem reviewItem = mCurrentReviewItems.get(position);
            String value = reviewItem.getDisplayValue();
            if (TextUtils.isEmpty(value)) {
                value = "(None)";
            }
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(reviewItem.getTitle());
            ((TextView) rootView.findViewById(android.R.id.text2)).setText(value);
            return rootView;
        }

        @Override
        public int getCount() {
            return mCurrentReviewItems.size();
        }
    }
}
