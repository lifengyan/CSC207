package phaseone;

public class Worker {

  // Worker Superclass
  private String name;
  private String status = "ready";

  public Worker(String name) {
    this.name = name;

  }

  public String getName() {
    return name;
  }

  public String getStatus() {
    return this.status;
  }

}
