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

import javafx.scene.layout.StackPane;

/**
 * Notification Pane
 *
 * Root container provided to wrap any content that the user wishes to display
 * toast notifications over.
 *
 * Compatible with both FXML and raw Java usage.
 *
 * @author Elizabeth Harper [elliefops@gmail.com]
 * @version 1.0
 * @since v1.0.0, 2017-04-20
 */
public class NotificationPane extends StackPane
{
  private final NotificationAnchorStack stack;

  public NotificationPane()
  {
    stack = new NotificationAnchorStack();
    stack.setPickOnBounds(false);

    getChildren().add(stack);
  }

  /**
   * @see NotificationAnchorStack#notify(Notification)
   */
  public void notify( final Notification notification )
  {
    stack.notify(notification);
  }

  /**
   * @see NotificationAnchorStack#notify(Notification, int)
   */
  public void notify( final Notification note, final int show )
  {
    stack.notify(note, show);
  }

  /**
   * @see NotificationAnchorStack#notify(Notification, int, int, int, int)
   */
  public void notify(
    final Notification note,
    final int in,
    final int show,
    final int out,
    final int col
  )
  {
    stack.notify(note, in, show, out, col);
  }
}
