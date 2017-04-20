/*
 *    Copyright 2017 Excel Micro LLC
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.excelmicro.lib.fx.toaster;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Notification Anchor Stack
 *
 * Container for the notification stack.
 *
 * @author Elizabeth Harper [elliefops@gmail.com]
 * @version 1.0
 * @since v1.0.0, 2017-04-20
 */
public class NotificationAnchorStack extends AnchorPane
{
  private final IntegerProperty defShow;

  private final IntegerProperty defFadeIn;

  private final IntegerProperty defFadeOut;

  private final IntegerProperty defCollapse;

  private final VBox notificationStack;

  NotificationAnchorStack()
  {
    defShow = new SimpleIntegerProperty(2000);
    defFadeIn = new SimpleIntegerProperty(200);
    defFadeOut = new SimpleIntegerProperty(150);
    defCollapse = new SimpleIntegerProperty(150);
    notificationStack = new VBox(10);

    getChildren().add(notificationStack);

    setRightAnchor(notificationStack, 0D);
    setTopAnchor(notificationStack, 0D);
    setBottomAnchor(notificationStack, 0D);

    notificationStack.setStyle(buildNotificationStackStyle());
    notificationStack.getChildren().addListener(this::childrenListener);
  }

  protected String buildNotificationStackStyle()
  {
    return "-fx-pref-width: 200px;"
      + "-fx-padding: 10px;";
  }

  public IntegerProperty defaultShowProperty()     { return defShow; }
  public IntegerProperty defaultFadeInProperty()   { return defFadeIn; }
  public IntegerProperty defaultFadeOutProperty()  { return defFadeOut; }
  public IntegerProperty defaultCollapseProperty() { return defCollapse; }

  public int getDefaultShow()     { return defShow.get(); }
  public int getDefaultFadeIn()   { return defFadeIn.get(); }
  public int getDefaultFadeOut()  { return defFadeOut.get(); }
  public int getDefaultCollapse() { return defCollapse.get(); }

  public void setDefaultShow( final int mil )     { this.defShow.set(mil); }
  public void setDefaultFadeIn( final int mil )   { this.defFadeIn.set(mil); }
  public void setDefaultFadeOut( final int mil )  { this.defFadeOut.set(mil); }
  public void setDefaultCollapse( final int mil ) { this.defCollapse.set(mil); }

  public VBox getNotificationStack() { return notificationStack; }

  private void childrenListener( final ListChangeListener.Change change )
  {
    if ( change.getList().isEmpty() ) {
      toBack();
    } else {
      toFront();
    }
  }

  /**
   * Push Notification
   *
   * Animation and visibility times will use defaults.
   *
   * @param notification Note to show
   */
  public void notify( final Notification notification )
  {
    notify(
      notification,
      defFadeIn.get(),
      defShow.get(),
      defFadeOut.get(),
      defCollapse.get()
    );
  }

  public void notify( final Notification note, final int show )
  {
    notify(
      note,
      defFadeIn.get(),
      show,
      defFadeOut.get(),
      defCollapse.get()
    );
  }

  /**
   * Push Notification
   *
   * @param note Notification to show
   * @param in   Fade in transition time in milliseconds
   * @param show Time to show notification for in milliseconds
   * @param out  Fade out transition time in milliseconds
   * @param col  Collapse transition time in milliseconds
   */
  public void notify(
    final Notification note,
    final int in,
    final int show,
    final int out,
    final int col
  )
  {
    final FadeTransition transition;

    notificationStack.getChildren().add(note);

    transition = new FadeTransition(
      Duration.millis(in),
      note
    );

    transition.setByValue(1);
    transition.play();

    new Timer().schedule(buildFadeOut(note, out, col), show);
  }

  private TimerTask buildFadeOut( final Notification note, final int out, final int col )
  {
    return new TimerTask() {
      @Override
      public void run()
      {
        final FadeTransition fade;
        final TranslateTransition  trans;

        trans = new TranslateTransition(
          Duration.millis(col),
          notificationStack
        );
        trans.setByY(0 - (note.getHeight() + notificationStack.getSpacing()));
        trans.setOnFinished(event -> {
          notificationStack.getChildren().remove(note);
          notificationStack.setTranslateY(0);
        });

        fade = new FadeTransition(
          Duration.millis(out),
          note
        );
        fade.setByValue(-1);
        fade.setOnFinished(event -> trans.play());
        fade.play();
      }
    };
  }
}
