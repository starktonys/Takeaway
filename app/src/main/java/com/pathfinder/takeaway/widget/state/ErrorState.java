package com.pathfinder.takeaway.widget.state;

import com.pathfinder.takeaway.R;

public class ErrorState extends AbstractShowState {
  @Override public void show(boolean animate) {
    showViewById(R.id.epf_error, animate);
  }

  @Override public void dismiss(boolean animate) {
    dismissViewById(R.id.epf_error, animate);
  }
}
