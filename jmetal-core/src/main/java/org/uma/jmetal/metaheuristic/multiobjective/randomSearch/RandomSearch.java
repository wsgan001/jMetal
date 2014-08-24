//  RandomSearch.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.uma.jmetal.metaheuristic.multiobjective.randomSearch;

import org.uma.jmetal.core.Algorithm;
import org.uma.jmetal.core.Problem;
import org.uma.jmetal.core.Solution;
import org.uma.jmetal.core.SolutionSet;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.NonDominatedSolutionList;

/**
 * This class implements a simple random search algorithm.
 */
public class RandomSearch extends Algorithm {
  private static final long serialVersionUID = 7957970222128947424L;

  private int maxEvaluations ;

  /** Constructor */
  private RandomSearch(Builder builder) {
    this.problem = builder.problem ;
    this.maxEvaluations = builder.maxEvaluations ;
  }

  /* Getter */
  public int getMaxEvaluations() {
    return maxEvaluations;
  }

  /** Builder class */
  public static class Builder {
    Problem problem ;
    int maxEvaluations ;

    public Builder(Problem problem) {
      this.problem = problem ;
      maxEvaluations = 25000 ;
    }

    public Builder setMaxEvaluations(int maxEvaluations) {
      this.maxEvaluations = maxEvaluations ;

      return this ;
    }

    public RandomSearch build() {
      return new RandomSearch(this) ;
    }
  }

  /** Execute() method */
  public SolutionSet execute() throws JMetalException, ClassNotFoundException {
    int evaluations;
    NonDominatedSolutionList nonDominatedSet ;

    evaluations = 0;
    nonDominatedSet = new NonDominatedSolutionList();

    Solution newSolution;
    for (int i = 0; i < maxEvaluations; i++) {
      newSolution = new Solution(problem);
      problem.evaluate(newSolution);
      problem.evaluateConstraints(newSolution);
      evaluations++;
      nonDominatedSet.add(newSolution);
    }

    return nonDominatedSet;
  }
} 
