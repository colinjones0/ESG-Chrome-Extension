package edu.brown.cs.student.esg;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Company> {

  @Override
  public int compare(Company o1, Company o2) {
    double esgScore1 = o1.getWeight();
    double esgScore2 = o2.getWeight();
    if (esgScore1 > esgScore2) {
      return -1;
    } else {
      return 1;
    }
  }
}
