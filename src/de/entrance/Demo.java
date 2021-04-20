/*
 * Copyright 2021-present Entrance Robotics GmbH
 */
package de.entrance;

import de.entrance.model.DummyData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * demo how to read a json textfile and extract the objects of the json array
 */
public class Demo {
  Set<DummyData> dummyData = new HashSet<>();

  /**
   * read JSON from filesystem
   *
   * @return returns json as string or error message
   */
  String readFile() {
    String retVal;
    try {
      String rootDir = new File(".").getCanonicalPath();
      return Files.readString(Paths.get(rootDir + "/data.json"));
    } catch (IOException e) {
      e.printStackTrace();
      retVal = "error reading file";
    }
    return retVal;
  }

  /**
   * parse JSON file and return QI speech
   *
   * @param jsonString raw JSON input data as string
   * @return returns QU multiline string
   */
  String parseJSON(String jsonString) {
    int counter = 0;
    StringBuilder sb = new StringBuilder();
    sb.append("topic: ~TOPIC_").append(counter).append("()");
    try {
      JSONArray jsonArray = new JSONArray(jsonString);
      for (Object o : jsonArray) {
        JSONObject obj = (JSONObject) o;
        if (obj != null) {
          this.dummyData.add(new DummyData(obj.getString("input"), obj.getString("output")));
          sb.append("\n")
              .append("u: (")
              .append(obj.getString("input"))
              .append(") ")
              .append(obj.getString("output"));
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }

  /**
   * main function of demo application
   *
   * @param args no arguments needed to start this application
   */
  public static void main(String[] args) {
    Demo demo = new Demo();
    System.out.println(demo.parseJSON(demo.readFile()));
  }
}
