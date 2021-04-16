package edu.brown.cs.student.esg;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Company> {

  @Override
  public int compare(Company o1, Company o2) {
    double esgScore1 = Double.parseDouble(o1.getScore());
    double esgScore2 = Double.parseDouble(o2.getScore());
    if (esgScore1 > esgScore2) {
      return -1;
    } else {
      return 1;
    }
  }
}
