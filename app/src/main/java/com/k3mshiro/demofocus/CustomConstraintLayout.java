package com.k3mshiro.demofocus;

import android.content.Context;
import android.graphics.Rect;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dong on 10/3/17.
 */

public class CustomConstraintLayout extends ConstraintLayout {
  private OnFocusSearchListener mListener;
  private OnChildFocusListener mOnChildFocusListener;

  public CustomConstraintLayout(Context context) {
    super(context);
  }

  public CustomConstraintLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CustomConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setOnFocusSearchListener(OnFocusSearchListener listener) {
    mListener = listener;
  }

  public void setOnChildFocusListener(OnChildFocusListener listener) {
    mOnChildFocusListener = listener;
  }

  @Override
  protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
    if (mOnChildFocusListener != null) {
      return mOnChildFocusListener.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }
    return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
  }

  @Override
  public View focusSearch(View focused, int direction) {
    if (mListener != null) {
      View view = mListener.onFocusSearch(focused, direction);
      if (view != null) {
        return view;
      }
    }
    return super.focusSearch(focused, direction);
  }

  @Override
  public void requestChildFocus(View child, View focused) {
    super.requestChildFocus(child, focused);
    if (mOnChildFocusListener != null) {
      mOnChildFocusListener.onRequestChildFocus(child, focused);
    }
  }
}
