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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
  private Scene primaryScene;

  @Override
  public void start( final Stage primaryStage ) throws Exception
  {
    final FXMLLoader loader;

    loader = new FXMLLoader(Main.class.getResource("/main.fxml"));

    loader.load();

    primaryScene = new Scene(loader.getRoot());

    // Window size
    primaryStage.setWidth(800);
    primaryStage.setHeight(600);

    primaryStage.setScene(primaryScene);
    primaryStage.show();
  }

}
