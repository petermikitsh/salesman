class OptimalTSP {

	public static void main(String[] args) {
    try {
      checkErrorConditions(args);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
	}

  private static void checkErrorConditions(String[] args) throws IllegalArgumentException {
    if (args.length != 2) {
      throw new IllegalArgumentException("Usage: java OptimalTSP n seed");
    }

    for (int i = 0; i < 2; i++) {
      try {
        Integer.parseInt(args[i]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Command line args must be integers");
      }
    }

    Integer n = Integer.parseInt(args[0]);
    if (n < 1 || n > 13) {
      throw new IllegalArgumentException("Number of vertices must be between 1 and 13");
    }
  }
}