/*
 * A-maze-ing API
 * This document describes the API of the A-maze-ing evening server.    This API consists of three different endpoints, which are detailed below.   - To register yourself as a player use the Player endpoint.   - To get information about the available mazes and enter a specific maze use the Mazes endpoint.   - To navigate a maze use the Maze endpoint.
 *
 * OpenAPI spec version: v2
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.github.mostafaelsaghir.mazeclient.generated.invoker;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

import java.text.FieldPosition;
import java.util.Date;


public class RFC3339DateFormat extends ISO8601DateFormat {

  // Same as ISO8601DateFormat but serializing milliseconds.
  @Override
  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
    String value = ISO8601Utils.format(date, true);
    toAppendTo.append(value);
    return toAppendTo;
  }

}