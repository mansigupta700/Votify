// Generated by view binder compiler. Do not edit!
package com.example.votify.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.votify.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityResultsPageBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout/</li>
   *   <li>layout-sw600dp/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout-land/</li>
   * </ul>
   */
  @Nullable
  public final TextView editText1;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-land/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   *   <li>layout-sw600dp/</li>
   * </ul>
   */
  @Nullable
  public final TextView editTextTextPersonName8;

  @NonNull
  public final EditText resultscode;

  @NonNull
  public final Button showResults;

  @NonNull
  public final TextView winner;

  private ActivityResultsPageBinding(@NonNull RelativeLayout rootView, @Nullable TextView editText1,
      @Nullable TextView editTextTextPersonName8, @NonNull EditText resultscode,
      @NonNull Button showResults, @NonNull TextView winner) {
    this.rootView = rootView;
    this.editText1 = editText1;
    this.editTextTextPersonName8 = editTextTextPersonName8;
    this.resultscode = resultscode;
    this.showResults = showResults;
    this.winner = winner;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityResultsPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityResultsPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_results_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityResultsPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.editText1;
      TextView editText1 = ViewBindings.findChildViewById(rootView, id);

      id = R.id.editTextTextPersonName8;
      TextView editTextTextPersonName8 = ViewBindings.findChildViewById(rootView, id);

      id = R.id.resultscode;
      EditText resultscode = ViewBindings.findChildViewById(rootView, id);
      if (resultscode == null) {
        break missingId;
      }

      id = R.id.show_results;
      Button showResults = ViewBindings.findChildViewById(rootView, id);
      if (showResults == null) {
        break missingId;
      }

      id = R.id.winner;
      TextView winner = ViewBindings.findChildViewById(rootView, id);
      if (winner == null) {
        break missingId;
      }

      return new ActivityResultsPageBinding((RelativeLayout) rootView, editText1,
          editTextTextPersonName8, resultscode, showResults, winner);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
