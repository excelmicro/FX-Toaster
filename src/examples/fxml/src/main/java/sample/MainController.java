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

package sample;

import com.excelmicro.lib.fx.toaster.Notification;
import com.excelmicro.lib.fx.toaster.NotificationPane;
import javafx.event.Event;
import javafx.fxml.FXML;

public class MainController
{
  @FXML
  private NotificationPane noteStack;

  @FXML
  private void addPlain( final Event ignored )
  {
    noteStack.notify(new Notification("Testing"));
  }

  @FXML
  public void addSuccess( final Event event )
  {
    final Notification note = new Notification("Success");

    note.setStyle(
      note.getStyle()
        + "-fx-border-width: 0 0 0 10px;"
        + "-fx-border-color: lightgreen;"
    );

    noteStack.notify(note);
  }

  public void addError( final Event event )
  {
    final Notification note = new Notification("Error");

    note.setBodyText("Something went wrong!")
      .setStyle(
        note.getStyle()
          + "-fx-border-width: 0 0 0 10px;"
          + "-fx-border-color: red;"
      );

    noteStack.notify(note);
  }

  public void addWarn( final Event event )
  {
    final Notification note = new Notification("Warning");

    note.setBodyText("Don't do that")
      .setStyle(
        note.getStyle()
          + "-fx-border-width: 0 0 0 10px;"
          + "-fx-border-color: gold;"
      );

    noteStack.notify(note);
  }
}
