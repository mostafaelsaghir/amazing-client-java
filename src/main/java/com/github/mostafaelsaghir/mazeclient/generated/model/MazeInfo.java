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


package com.github.mostafaelsaghir.mazeclient.generated.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Information related to a Maze.
 */
@ApiModel(description = "Information related to a Maze.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-08-12T19:03:45.973+02:00")
public class MazeInfo {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("totalTiles")
  private Integer totalTiles = null;

  @JsonProperty("potentialReward")
  private Integer potentialReward = null;

   /**
   * The name of the maze. Might give you a hint regarding its structure.
   * @return name
  **/
  @ApiModelProperty(value = "The name of the maze. Might give you a hint regarding its structure.")
  public String getName() {
    return name;
  }

   /**
   * How many tiles exist in this maze.
   * @return totalTiles
  **/
  @ApiModelProperty(value = "How many tiles exist in this maze.")
  public Integer getTotalTiles() {
    return totalTiles;
  }

   /**
   * The total available reward in this maze.
   * @return potentialReward
  **/
  @ApiModelProperty(value = "The total available reward in this maze.")
  public Integer getPotentialReward() {
    return potentialReward;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MazeInfo mazeInfo = (MazeInfo) o;
    return Objects.equals(this.name, mazeInfo.name) &&
        Objects.equals(this.totalTiles, mazeInfo.totalTiles) &&
        Objects.equals(this.potentialReward, mazeInfo.potentialReward);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, totalTiles, potentialReward);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MazeInfo {\n");

    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    totalTiles: ").append(toIndentedString(totalTiles)).append("\n");
    sb.append("    potentialReward: ").append(toIndentedString(potentialReward)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

