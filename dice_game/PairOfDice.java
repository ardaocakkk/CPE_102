
package hw1_sample_solution;

import java.util.Objects;


public class PairOfDice {
    private Die die1;
    private Die die2;
    

    public PairOfDice() {
        die1=new Die();
        die2=new Die();
      
    }

    public Die getDie1() {
        return new Die(die1);
    }

    public void setDie1(Die die1) {
        this.die1 = new Die(die1);
    }

    public Die getDie2() {
        return new Die(die2);
    }

    public void setDie2(Die die2) {
        this.die2 = new Die(die2);
    }
    public void roll()
    {
        die1.roll();
        die2.roll();
    }

   public int getDiceSum()
   {
       return die1.getFaceValue()+die2.getFaceValue();
   }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PairOfDice other = (PairOfDice) obj;
        if (!(die1.equals(other.die1))) {
            return false;
        }
        return die2.equals(other.die2);
    }

    @Override
    public String toString() {
        return "PairOfDice{" + "die1= " + die1 + ", die2= " + die2 + '}';
    }
    
    

  
    
    

  
    
    
    
}
