package com.google.codeu.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.codeu.data.Datastore;
import com.google.gson.JsonObject;

/**
 * Handles fetching site statistics.
 * 
 * This uses the init() function that creates and store a Datastore instance,
 * then doGet() calls the function getTotalMessageCount() in the Datastore.java
 * and create a JSON that countains the message count
 */
public class StatsPageServlet extends HttpServlet {
  private Datastore datastore;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    datastore = createDatastore();
  }

  /*
   * Instantiates a connection to a datastore. In tests, override this with a
   * method that returns a fake/mock datastore object.
   */
  protected Datastore createDatastore() {
    return new Datastore();
  }

  /**
   * Responds with site statistics in JSON.
   */

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    response.setContentType("application/json");

    int messageCount = datastore.getTotalMessageCount();

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("messageCount", messageCount);
    response.getOutputStream().println(jsonObject.toString());
  }
}