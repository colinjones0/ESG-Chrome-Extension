package edu.brown.cs.student.esg;

import java.util.Comparator;

public class ScoreComparator implements Comparator<NewCompany> {

  @Override
  public int compare(NewCompany o1, NewCompany o2) {
    double esgScore1 = Double.parseDouble(o1.getScore());
    double esgScore2 = Double.parseDouble(o2.getScore());
    if (esgScore1 < esgScore1) {
      return -1;
    } else {
      return 1;
    }
  }
}
