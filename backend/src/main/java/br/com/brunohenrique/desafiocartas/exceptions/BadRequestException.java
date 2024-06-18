package br.com.brunohenrique.desafiocartas.exceptions;

public class BadRequestException extends RuntimeException {

  private final Issue issue;

  private BadRequestException(Issue issue) {
    super(issue.errorDetails());
    this.issue = issue;
  }

  public static BadRequestException from(String message) {
    return new BadRequestException(new Issue(message, "BAD_REQUEST"));
  }

  public Issue getIssue() {
    return issue;
  }

  public static BadRequestException notFoundException(String message) {
    return new BadRequestException(new Issue(message, "NOT_FOUND"));
  }
}
