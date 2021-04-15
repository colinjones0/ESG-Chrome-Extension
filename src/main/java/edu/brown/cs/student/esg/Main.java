package edu.brown.cs.student.esg;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import org.json.JSONObject;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;
import freemarker.template.Configuration;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Map;

/**
 * The Main class of our project. This is where execution begins.
 *
 */
public final class Main {
  private static final int DEFAULT_PORT = 4567;
  private static final Gson GSON = new Gson();
  private static TopLevel tl;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
    new Main(args).run();
  }

  private final String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() throws SQLException, IOException, ClassNotFoundException {
    // Parse command line arguments
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
            .defaultsTo(DEFAULT_PORT);
    OptionSet options = parser.parse(args);

    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }
//    Scraper scraper = new Scraper();
//    scraper.getText("https://www.nike.com/");
    tl = new TopLevel();
    REPL repl = new REPL();
    repl.readUserInput();

  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration();
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n",
              templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  private void runSparkServer(int port) {
//    /* Taken largely from the React lab's server. */
//
//    Spark.port(port);
//    Spark.externalStaticFileLocation("src/main/resources/static");
//
//    /* Access control. */
//    Spark.options("/*", (request, response) -> {
//      String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
//      if (accessControlRequestHeaders != null) {
//        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
//      }
//      String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
//      if (accessControlRequestMethod != null) {
//        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
//      }
//      return "OK";
//    });
//    Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
//    Spark.exception(Exception.class, new ExceptionPrinter());
//    FreeMarkerEngine freeMarker = createEngine();
//    //Spark.post("/findCompany", new CompanyNameHandler());
    Spark.port(port);
    Spark.externalStaticFileLocation("src/main/resources/static");
    Spark.exception(Exception.class, new ExceptionPrinter());

    FreeMarkerEngine freeMarker = createEngine();
  }

  /**
   * Display an error page when an exception occurs in the server.
   *
   */
  private static class ExceptionPrinter implements ExceptionHandler {
    @Override
    public void handle(Exception e, Request req, Response res) {
      res.status(500);
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

  /**
   * Handles getting a company name from a website.
   */
  private static class CompanyNameHandler implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
      JSONObject data = new JSONObject(request.body());
      String url = data.getString("currPage");
      String [][] returnData = tl.createGraph(url);
     // String companyName = "hello";
      Map<String, Object> variables = ImmutableMap.of("recommendations", returnData);
      return GSON.toJson(variables);
    }
  }


}
