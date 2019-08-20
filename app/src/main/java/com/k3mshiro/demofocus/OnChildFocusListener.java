package com.k3mshiro.demofocus;

import android.graphics.Rect;
import android.view.View;

/**
 * Created by dong on 10/19/17.
 */

public interface OnChildFocusListener {
  boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect);

  void onRequestChildFocus(View child, View focused);
}
