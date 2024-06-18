package br.com.brunohenrique.desafiocartas.utils;

public final class LogConstants {

  private LogConstants() {
    throw new IllegalStateException("Utility class");
  }

  public static final String INFO_REQUEST_RECEIVED = "Received request: {} {} from {}";

  public static final String INFO_REQUEST_PROCESSED =
      "Sent response: {} {} with status {} and exception {}";
}
