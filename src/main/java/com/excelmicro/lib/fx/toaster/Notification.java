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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Animated Notification
 *
 * Styling can be altered per notification with {@link #setStyle(String)}.
 *
 * To establish a standard styled notification type, this class can be extended.
 * The extending class should override the {@link #buildRootStyle()} method to
 * provide alternate css rules.
 *
 * @author Elizabeth Harper [elliefops@gmail.com]
 * @version 1.0
 * @since 1.0.0, 2017-04-20
 */
public class Notification extends VBox
{
  private final Label header;

  private final HBox headerRow;

  private final Text body;

  private final ObjectProperty < Node > icon;

  public Notification( final String headerText )
  {
    super(5);
    header = new Label();
    headerRow = new HBox(5);
    body = new Text();
    icon = new SimpleObjectProperty <>();

    headerRow.getChildren().add(header);

    getChildren().addAll(headerRow, body);
    setStyle(buildRootStyle());
    setOpacity(0.0);

    HBox.setHgrow(header, Priority.ALWAYS);

    header.setStyle(buildHeaderStyle());
    header.setText(headerText);

    body.setStyle(buildBodyStyle());
    body.managedProperty()
      .bind(body.textProperty().isNotEmpty());
  }

  /**
   * Set Header Text Content
   *
   * @param text text to apply to the header
   *
   * @return the current instance of Notification
   */
  public Notification setHeaderText( final String text )
  {
    header.setText(text);

    return this;
  }

  /**
   * Set Header Icon
   *
   * @param icon icon to show in the header
   *
   * @return the current instance of Notification
   */
  public Notification setHeaderIcon( final Node icon )
  {
    if ( !this.icon.isNull().get() ) {
      headerRow.getChildren().remove(this.icon.get());
    }

    headerRow.getChildren().add(icon);
    this.icon.set(icon);

    return this;
  }

  /**
   * Sets the body content text
   *
   * @param text Text to show in the notification body
   *
   * @return the current instance of Notification
   */
  public Notification setBodyText( final String text )
  {
    this.body.setText(text);
    return this;
  }

  /**
   * Builds the default notification node css styling.
   *
   * @return A css rule set to apply to the root node of this notification.
   */
  protected String buildRootStyle()
  {
    return getStyle()
      + "-fx-padding: 10px;"
      + "-fx-effect: dropshadow("
      + "  gaussian,"
      + "  rgba(105, 105, 105, 0.3),"
      + "  10,"
      + "  0.2,"
      + "  4,"
      + "  4"
      + ");"
      + "-fx-background-color: whitesmoke;";
  }

  /**
   * Builds the css rule set to be applied to the header of this notification
   *
   * @return css rule set to be applied to the header of this notification
   */
  protected String buildHeaderStyle()
  {
    return header.getStyle()
      + "-fx-text-fill: #1d1d1d;"
      + "-fx-font-size: 1.5em;";
  }

  /**
   * Builds the css rule set to be applied to the body of this notification
   *
   * @return css rule set to be applied to the body of this notification
   */
  protected String buildBodyStyle()
  {
    return body.getStyle();
  }

  /**
   * Returns the header label
   *
   * @return header Label
   */
  public Label getHeader()
  {
    return header;
  }
}
